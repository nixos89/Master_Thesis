package com.nikolas.master_thesis.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	// private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
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
		if (orderRequest != null) {
			User user = userRepository.findByUsername(username);
			if (user == null) {
				throw new StoreException("User '" + username + "' does NOT exist in database!", HttpStatus.NOT_FOUND);
			}
			Set<OrderItem> orderItems = new HashSet<>();
			Order order = new Order();
			List<Long> orderBookIds = orderRequest.getOrders().stream().map(AddOrderDTO::getBookId).collect(Collectors.toList());

			List<Book> booksFromOrder = bookRepository.getAllBooksFromOrder(orderBookIds);
			Map<Long, Book> booksById = new HashMap<Long, Book>();
			booksFromOrder.forEach(b -> booksById.put(b.getBookId(), b));

			for (AddOrderDTO addOrder : orderRequest.getOrders()) {
				Book book = booksById.get(addOrder.getBookId());				
				if (book == null) {
					throw new StoreException("Error, book with id = " + addOrder.getBookId() + "  doesn't exist in database!",
							HttpStatus.BAD_REQUEST);
				} else if (addOrder.getAmount() > book.getAmount()) {
					throw new StoreException(
							"Amount for book with title: '" + book.getTitle()
									+ "' is more than on the stock!\nCurrent amount on stock is: " + book.getAmount(),
							HttpStatus.BAD_REQUEST);
				} else {										
					book.setAmount(book.getAmount() - addOrder.getAmount());
					order.setTotal(orderRequest.getTotalPrice());
					order.setOrderDate(new Timestamp(System.currentTimeMillis())); 
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
			return new OrderResponseDTO(order.getOrderId());
		} else {
			throw new StoreException("Error, request body is empty!", HttpStatus.BAD_REQUEST);
		}		
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
