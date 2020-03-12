package com.test.deloitte.enums;

import lombok.Getter;

public enum Status {

	CREATED("C"), UPDATED("U");
	
	@Getter
	private String status;
	
	Status(String status) {
		this.status = status;
	}
}
