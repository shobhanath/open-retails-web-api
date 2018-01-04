package com.openretails.web.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openretails.common.constant.ApplicationConstants;
import com.openretails.common.exception.format.ExceptionMessage;
import com.openretails.data.Collections;
import com.openretails.data.Single;
import com.openretails.data.UserDTO;
import com.openretails.data.UsernameAndPasswordDTO;
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

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> create(

	@ApiParam(value = "users", required = true) @RequestBody Collections<UserDTO> users) {
		return new ResponseEntity<>(userManager.create(users), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.enableOrDisable.value}", notes = "${UserServices.enableOrDisable.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PutMapping(value = "/users/{enable}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> enableOrDisable(
			@ApiParam(value = "users", required = true) @RequestBody Collections<String> users,
			@ApiParam(value = "enable", required = true) @PathVariable("enable") boolean enable) {
		return new ResponseEntity<>(userManager.enableOrDisable(users, enable), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findAll.value}", notes = "${UserServices.findAll.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })

	@PreAuthorize("hasAnyRole('ADMIN')")

	public ResponseEntity<Collections<UserDTO>> findAll(
			@ApiParam(value = "active", required = false) @RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(
				userManager.findAll(active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findByUser.value}", notes = "${UserServices.findByUser.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@GetMapping(value = "/users/{user:.+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<UserDTO> findByUser(
			@ApiParam(value = "${UserServices.findByUser.param1}", required = false) @RequestParam(value = "active", required = false, defaultValue = "") String active,
			@PathVariable("user") @ApiParam(value = "user", required = true) String user) {
		final Boolean isActive = active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active);
		final boolean isNumberic = StringUtils.isNumeric(user);
		return new ResponseEntity<>(isNumberic ? userManager.findById(Long.valueOf(user), isActive)
				: userManager.findByUser(user, isActive),
				HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findIdentitiesByUsernameOrPrimayEmailAddress.value}", notes = "${UserServices.findIdentitiesByUsernameOrPrimayEmailAddress.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users/get-ids-by-emails", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<Long>> findIdentitiesByUsernameOrPrimayEmailAddress(
			@ApiParam(value = "users", required = true) @RequestBody Collections<String> users,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findIdByUser(users,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findIdentityByUsernameOrPrimayEmailAddress.value}", notes = "${UserServices.findIdentityByUsernameOrPrimayEmailAddress.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@GetMapping(value = "/users/{user:.+}/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<Long>> findIdentityByUsernameOrPrimayEmailAddress(
			@PathVariable("user") @ApiParam(value = "user", required = true) String user,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findIdByUser(user,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findUsernameOrPrimaryEmailByIdentity.value}", notes = "${UserServices.findUsernameOrPrimaryEmailByIdentity.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users/get-emails-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<String>> findUsernameOrPrimaryEmailByIdentity(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findUsernameById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findUsernameOrPrimayEmailAddressById.value}", notes = "${UserServices.findUsernameOrPrimayEmailAddressById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@GetMapping(value = "/users/{user}/email", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<String>> findUsernameOrPrimayEmailAddressById(
			@PathVariable("user") @ApiParam(value = "user", required = true) long user,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findUsernameById(user,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findUsersById.value}", notes = "${UserServices.findUsersById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users/get-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> findUsersById(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.findUsersByUsernameOrEmail.value}", notes = "${UserServices.findUsersByUsernameOrEmail.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users/get-by-emails", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> findUsersByUsernameOrEmail(
			@ApiParam(value = "users", required = true) @RequestBody Collections<String> users,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<>(userManager.findByUser(users,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.partialUpdate.value}", notes = "${UserServices.partialUpdate.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PatchMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> partialUpdate(

	@ApiParam(value = "users", required = true) @RequestBody Collections<UserDTO> users) {
		return new ResponseEntity<>(userManager.partialUpdate(users), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.update.value}", notes = "${UserServices.update.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PutMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<UserDTO>> update(

	@ApiParam(value = "users", required = true) @RequestBody Collections<UserDTO> users) {
		return new ResponseEntity<>(userManager.update(users), HttpStatus.OK);
	}

	@ApiOperation(value = "${UserServices.validate.value}", notes = "${UserServices.validate.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.OK),
			@ApiResponse(code = 400, message = ApplicationConstants.BAD_REQUEST, response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = ApplicationConstants.UNAUTHORIZED, response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN, response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND, response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = ApplicationConstants.INTERNAL_SERVER_ERROR, response = ExceptionMessage.class) })

	@PostMapping(value = "/users/authenticate", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<UserDTO> validate(

	@ApiParam(value = "credential", required = true) @RequestBody Single<UsernameAndPasswordDTO> credential) {
		return new ResponseEntity<>(
				userManager.validate(credential.getData().getUsername(), credential.getData().getPassword()),
				HttpStatus.OK);
	}


}
