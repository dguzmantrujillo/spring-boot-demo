package com.example.demo.web.dto.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.example.demo.validator.OnCreate;
import com.example.demo.validator.OnUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	
	@NotNull(groups = {OnUpdate.class})
	@Null(groups = {OnCreate.class})
	@EqualsAndHashCode.Exclude
	private Long id;
	@NotBlank
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
	private List<AuthorDto> authors;
}
