package com.openretails.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openretails.data.ResponseCollection;
import com.openretails.data.UserDTO;
import com.openretails.profile.manager.UserManager;
import com.openretails.web.exception.handler.GenericExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/users")
public class UserServices extends GenericExceptionHandler {

	@Autowired
	private UserManager userManager;

	@ApiOperation(value = "create", notes = "create")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),

			@ApiResponse(code = 500, message = "Something wrong in Server") })

	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseCollection<UserDTO>> create(

	@ApiParam(value = "create", required = true) @RequestBody Collection<UserDTO> users) {
		return new ResponseEntity<ResponseCollection<UserDTO>>(userManager.create(users), HttpStatus.OK);

	}

	@ApiOperation(value = "Find pet by Status", notes = "${UserServices.findUsersByName.notes}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })

	@GetMapping(value = "/users/{user}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> findByName(
			@ApiParam(value = "${UserServices.findUsersByName.user}", required = true) @PathVariable("user") String user) {
		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);

	}

}
