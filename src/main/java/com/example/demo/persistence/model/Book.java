package com.example.demo.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = @Index(columnList = "isbn"))
public class Book {

	@EqualsAndHashCode.Exclude
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	@Column(unique = true, insertable = true, updatable = false)
	private String isbn;
	@NotBlank
	@EqualsAndHashCode.Exclude
	private String title;
	@NotBlank
	@EqualsAndHashCode.Exclude
	private String edition;
	@NotEmpty
	@Valid
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true)
	private List<Author> authors;
}
