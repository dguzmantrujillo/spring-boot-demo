package com.example.demo.web.dto.inout;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSingleObjectRequest<T extends Serializable> {
	
	@NotBlank
	protected String transactionId;
	@EqualsAndHashCode.Exclude
	protected String channel;
	@EqualsAndHashCode.Exclude
	protected String remarks;
	@NotNull
	@Valid
	private T data;
}
