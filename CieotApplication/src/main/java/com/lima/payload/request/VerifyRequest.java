package com.lima.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyRequest {
	private String radomCode;

	public String getRadomCode() {
		return radomCode;
	}

	public void setRadomCode(String radomCode) {
		this.radomCode = radomCode;
	}

}
