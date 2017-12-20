package com.openretails.stock.web.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openretails.common.constant.ApplicationConstants;
import com.openretails.common.exception.format.ExceptionMessage;
import com.openretails.data.Collections;
import com.openretails.data.ProductDTO;
import com.openretails.data.Single;
import com.openretails.stock.manager.ProductManager;
import com.openretails.stock.web.exception.handler.GenericExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/products")
public class ProductServices extends GenericExceptionHandler {
	@Autowired
	private ProductManager productManager;

	@ApiOperation(value = "${ProductServices.create.value}", notes = "${ProductServices.create.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> create(

	@ApiParam(value = "products", required = true) @RequestBody Collections<ProductDTO> products) {
		return new ResponseEntity<>(productManager.create(products), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.doSearch.value}", notes = "${ProductServices.doSearch.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })
	@GetMapping(value = "/products/search", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> doSearch(
			@ApiParam(value = "key", required = false) @RequestParam(value = "key", required = false, defaultValue = ApplicationConstants.EMPTY) String key) {

		if (key.equalsIgnoreCase(ApplicationConstants.EMPTY)) {
			return new ResponseEntity<>(productManager.findAll(null), HttpStatus.OK);
		}
		return new ResponseEntity<>(StringUtils.isNumeric(key)
				? productManager.findByNameContainingOrIdentityObseleteTrue(key, Long.valueOf(key))
				: productManager.findByNameContainingOrIdentityObseleteTrue(key, null), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.enableOrDisable.value}", notes = "${ProductServices.enableOrDisable.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/products/{enable}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> enableOrDisable(
			@ApiParam(value = "products", required = true) @RequestBody Collections<String> products,
			@ApiParam(value = "enable", required = true) @PathVariable("enable") boolean enable) {
		return new ResponseEntity<>(productManager.enableOrDisable(products, enable), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findAll.value}", notes = "${ProductServices.findAll.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })

	@PreAuthorize("hasAnyRole('ADMIN')")

	public ResponseEntity<Collections<ProductDTO>> findAll(
			@ApiParam(value = "active", required = false) @RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager
				.findAll(active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findByProduct.value}", notes = "${ProductServices.findByProduct.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/products/{name:.+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ProductDTO> findByProduct(
			@ApiParam(value = "${ProductServices.findByProduct.param1}", required = false) @RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active,
			@PathVariable("name") @ApiParam(value = "product", required = true) String name) {
		final Boolean isActive = active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active);
		final boolean isNumberic = StringUtils.isNumeric(name);
		return new ResponseEntity<>(isNumberic ? productManager.findById(Long.valueOf(name), isActive)
				: productManager.findByName(name, isActive), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findIdentitiesByProduct.value}", notes = "${ProductServices.findIdentitiesByProduct.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/products/get-ids-by-names", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<Long>> findIdentitiesByProduct(
			@ApiParam(value = "products", required = true) @RequestBody Collections<String> products,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findIdByName(products,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findIdentityByProduct.value}", notes = "${ProductServices.findIdentityByProduct.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/products/{name:.+}/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<Long>> findIdentityByProduct(
			@PathVariable("name") @ApiParam(value = "name", required = true) String name,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findIdByName(name,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findProductNameById.value}", notes = "${ProductServices.findProductNameById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/products/{identity}/name", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<String>> findProductNameById(
			@PathVariable("identity") @ApiParam(value = "identity", required = true) long identity,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findNameById(identity,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findProductNamesByIdentity.value}", notes = "${ProductServices.findProductNamesByIdentity.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/products/get-names-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<String>> findProductNamesByIdentity(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findNameById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findProductsById.value}", notes = "${ProductServices.findProductsById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/products/get-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> findProductsById(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.findProductsByName.value}", notes = "${ProductServices.findProductsByName.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/products/get-by-names", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> findProductsByName(
			@ApiParam(value = "products", required = true) @RequestBody Collections<String> products,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(productManager.findByName(products,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductServices.update.value}", notes = "${ProductServices.update.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductDTO>> update(

	@ApiParam(value = "products", required = true) @RequestBody Collections<ProductDTO> products) {
		return new ResponseEntity<>(productManager.update(products), HttpStatus.OK);
	}
}
