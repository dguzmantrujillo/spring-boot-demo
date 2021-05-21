package com.example.demo.web.dto.inout;

import com.example.demo.web.dto.model.BookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetBookResponseDto extends ApiBaseResponseDto {
	
	private BookDto book;
}
