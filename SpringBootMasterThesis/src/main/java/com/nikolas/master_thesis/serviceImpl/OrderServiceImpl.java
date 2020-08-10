package com.nikolas.master_thesis.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AddOrderDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.OrderDTO;
import com.nikolas.master_thesis.dto.OrderItemDTO;
import com.nikolas.master_thesis.dto.OrderListDTO;
import com.nikolas.master_thesis.dto.OrderReportDTO;
import com.nikolas.master_thesis.dto.OrderResponseDTO;
import com.nikolas.master_thesis.dto.UserDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.BookMapper;
import com.nikolas.master_thesis.model.Book;
import com.nikolas.master_thesis.model.Order;
import com.nikolas.master_thesis.model.OrderItem;
import com.nikolas.master_thesis.model.User;
import com.nikolas.master_thesis.repository.BookRepository;
import com.nikolas.master_thesis.repository.OrderRepository;
import com.nikolas.master_thesis.repository.UserRepository;
import com.nikolas.master_thesis.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookMapper bookMapper;

	@Override
	public OrderResponseDTO addOrder(OrderListDTO orderRequest, String username) {
		Set<OrderItem> orderItems = new HashSet<>();
		Order order = new Order();
		if (orderRequest != null) {
			for (AddOrderDTO addOrder : orderRequest.getOrders()) {
				Book book = bookRepository.getOne(addOrder.getBookId());
				
				if (book == null) {
					System.out.println("Book to be ordered is NULL!!!");
					throw new StoreException("Book doesn't exist", HttpStatus.BAD_REQUEST);
				} else if (addOrder.getAmount() > book.getAmount()) {
					throw new StoreException(
							"Amount for book with title: '" + book.getTitle()
									+ "' is more than on the stock!\nCurrent amount on stock is: " + book.getAmount(),
							HttpStatus.BAD_REQUEST);
				} else {
					System.out.println("Book to be ordered:\n" + book.toString());
					User user = userRepository.findByUsername(username);
					if (user == null) {
						throw new StoreException("User not found", HttpStatus.NOT_FOUND);
					}

					book.setAmount(book.getAmount() - addOrder.getAmount());
					order.setTotal(orderRequest.getTotalPrice());
					order.setOrderDate(new Timestamp(System.currentTimeMillis())); // DEBUG this if it WON'T persist to
																					// DB!
					order.setUser(user);

					OrderItem orderItem = new OrderItem();
					orderItem.setAmount(addOrder.getAmount());
					orderItem.setBook(book);
					orderItem.setOrder(order);

					order.setOrderItems(orderItems);
					orderItems.add(orderItem);
					order = orderRepository.save(order);
				}
			}
		} else {
			throw new StoreException("Error, request is empty", HttpStatus.BAD_REQUEST);
		}
		System.out.println("Saved order_id = " + order.getOrderId());
		return new OrderResponseDTO(order.getOrderId());
	}	

	@Override
	public OrderReportDTO getAllOrders() {
		List<OrderItemDTO> orderItemDTOList = new LinkedList<OrderItemDTO>();
		List<OrderDTO> orderDTOList = new LinkedList<OrderDTO>();

		List<Order> orders = orderRepository.findAllByOrderByOrderIdAsc();
		OrderDTO orderDTO = new OrderDTO();
		if (orders != null) {
			double orderPrice;
			for (Order order : orders) {
				orderDTO = new OrderDTO();
				orderDTO.setOrderId(order.getOrderId());
				orderDTO.setUser(new UserDTO(order.getUser()));
				Date orderDate = order.getOrderDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dateString = null;

				if (orderDate == null) {
					throw new StoreException("Error, date formatting failed!", HttpStatus.BAD_REQUEST);
				}
				dateString = sdf.format(orderDate);
				orderDTO.setOrderDate(dateString);

				orderPrice = 0.0;
				for (OrderItem oi : order.getOrderItems()) {
					OrderItemDTO oiDTO = new OrderItemDTO();
					BookDTO bookDTO = bookMapper.mapBookToBookDTO(oi.getBook());

					oiDTO.setOrderItemId(oi.getOrderItemId());
					oiDTO.setOrderId(order.getOrderId());
					oiDTO.setBook(bookDTO);
					oiDTO.setOrderedAmount(oi.getAmount());

					BigDecimal bd = new BigDecimal((double) (oi.getBook().getPrice() * oi.getAmount())).setScale(2,
							RoundingMode.HALF_UP);
					double booksPriceNew = bd.doubleValue();
					oiDTO.setTotalOrderItemPrice(booksPriceNew);
					orderPrice += booksPriceNew;
					orderItemDTOList.add(oiDTO);
				}
				BigDecimal bd2 = new BigDecimal(orderPrice).setScale(2, RoundingMode.HALF_UP);
				double orderPriceNew = bd2.doubleValue();
				orderDTO.setOrderPrice(orderPriceNew);
				orderDTO.setOrderItemDTOList(orderItemDTOList);
				orderDTOList.add(orderDTO);
				orderItemDTOList = new LinkedList<OrderItemDTO>();
			}
			OrderReportDTO orderReportDTO = new OrderReportDTO();
			orderReportDTO.setOrders(orderDTOList);
			return orderReportDTO;
		} else {
			throw new StoreException("Error, there are no orders in database!", HttpStatus.NOT_FOUND);
		}
	}

}
