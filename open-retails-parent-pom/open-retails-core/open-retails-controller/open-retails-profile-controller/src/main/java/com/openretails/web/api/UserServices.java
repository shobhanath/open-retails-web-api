package com.openretails.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openretails.common.exception.format.ExceptionMessage;
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

	@ApiOperation(value = "${UserServices.create.value}", notes = "${UserServices.create.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseCollection<UserDTO>> create(

	@ApiParam(value = "create", required = true) @RequestBody Collection<UserDTO> users) {
		return new ResponseEntity<ResponseCollection<UserDTO>>(userManager.create(users), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findAllActive.value}", notes = "${UserServices.findAllActive.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/secured/all", produces = { MediaType.APPLICATION_JSON_VALUE })

	@PreAuthorize("hasAnyRole('ADMIN')")

	public ResponseEntity<ResponseCollection<UserDTO>> findAllActive() {
		return new ResponseEntity<ResponseCollection<UserDTO>>(userManager.findAll(), HttpStatus.OK);

	}

	@ApiOperation(value = "${UserServices.findByName.value}", notes = "${UserServices.findByName.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/users/{user:.+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserDTO> findByName(
			@ApiParam(value = "${UserServices.findByName.user}", required = true) @PathVariable("user") String user) {
		return new ResponseEntity<UserDTO>(userManager.getActiveUserByUsernameOrPrimaryEmailId(user), HttpStatus.OK);

	}

}
