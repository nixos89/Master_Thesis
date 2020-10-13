package com.nikolas.master_thesis.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_generator")
	@SequenceGenerator(name="roles_generator", sequenceName="roles_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long roleId;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<User> users;
	
}
