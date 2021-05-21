package com.example.demo.web.dto.inout;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseResponseDto {

	protected String authorizationNumber;
	@EqualsAndHashCode.Exclude
	protected LocalDateTime createdAt;
	@EqualsAndHashCode.Exclude
	protected LocalDateTime modifiedAt;
}
