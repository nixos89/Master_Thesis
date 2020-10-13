package com.nikolas.master_thesis.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"books"})
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@SequenceGenerator(name="category_generator", sequenceName="category_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long categoryId;

	private String name;

	private boolean isDeleted;
 
	@ManyToMany(fetch =FetchType.LAZY, mappedBy = "categories")
	private Set<Book> books = new HashSet<>();

	public Category(String name) {
		this.name = name;
	}

	public Category(Long categoryId, String name , boolean isDeleted){
		this.categoryId = categoryId;
		this.name = name;
		this.isDeleted = isDeleted;
	}
}
