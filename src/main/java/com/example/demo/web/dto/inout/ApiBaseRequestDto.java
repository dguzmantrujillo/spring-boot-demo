package com.example.demo.web.dto.inout;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseRequestDto {
	
	@NotBlank
	protected String transactionId;
	@EqualsAndHashCode.Exclude
	protected String channel;
	@EqualsAndHashCode.Exclude
	protected String remarks;
}
