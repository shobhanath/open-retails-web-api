package com.openretails.data;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsernameAndPasswordDTO implements Serializable {
	private static final long serialVersionUID = 8049242054559854037L;
	private String username;
	private String password;
}
