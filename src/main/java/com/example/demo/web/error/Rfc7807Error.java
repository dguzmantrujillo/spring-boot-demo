package com.example.demo.web.error;

import java.net.URI;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rfc7807Error {

	private URI type;
	private String title;
	private String detail;
	private Integer status;
	private URI instance;
}
