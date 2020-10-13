package com.nikolas.master_thesis.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"books"})
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "author_generator")
	@SequenceGenerator(name="author_generator", sequenceName="author_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long authorId;

	private String firstName;

	private String lastName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
	private Set<Book> books;

	public Author(String firstName, String lastName) {
		this.firstName = firstName; 
		this.lastName = lastName;
	}

}
