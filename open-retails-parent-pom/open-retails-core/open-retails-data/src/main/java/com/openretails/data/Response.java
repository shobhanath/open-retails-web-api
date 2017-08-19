package com.openretails.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4341921135909126443L;
	private String response;

}
