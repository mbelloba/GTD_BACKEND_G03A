package com.capgemini.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorObject {

	@Schema( description = "HTTP status error code",
			 example = "404")
	private String errorCode;
	
	@Schema( description = "Error description and details",
			 example = "Member not exists in BBDD")
	private String errorDescription;
	

}
