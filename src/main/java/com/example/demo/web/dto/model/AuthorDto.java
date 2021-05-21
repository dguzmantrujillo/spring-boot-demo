package com.example.demo.web.dto.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import com.example.demo.validator.OnCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
	
	@Null(groups = {OnCreate.class})
	private Long id;
	@NotBlank
	private String name;
}
