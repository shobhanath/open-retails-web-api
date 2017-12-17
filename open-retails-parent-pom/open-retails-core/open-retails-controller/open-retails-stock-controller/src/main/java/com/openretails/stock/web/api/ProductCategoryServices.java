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
import com.openretails.data.ProductCategoryDTO;
import com.openretails.data.Single;
import com.openretails.stock.manager.ProductCategoryManager;
import com.openretails.stock.web.exception.handler.GenericExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/product-categories")
public class ProductCategoryServices extends GenericExceptionHandler {
	@Autowired
	private ProductCategoryManager productCategoryManager;

	@ApiOperation(value = "${ProductCategoryServices.create.value}", notes = "${ProductCategoryServices.create.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/product-categories", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductCategoryDTO>> create(

	@ApiParam(value = "productCategories", required = true) @RequestBody Collections<ProductCategoryDTO> productCategories) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(productCategoryManager.create(productCategories),
				HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.enableOrDisable.value}", notes = "${ProductCategoryServices.enableOrDisable.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/product-categories/{enable}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductCategoryDTO>> enableOrDisable(
			@ApiParam(value = "productCategories", required = true) @RequestBody Collections<String> productCategories,
			@ApiParam(value = "enable", required = true) @PathVariable("enable") boolean enable) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(
				productCategoryManager.enableOrDisable(productCategories, enable), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findAll.value}", notes = "${ProductCategoryServices.findAll.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/product-categories", produces = { MediaType.APPLICATION_JSON_VALUE })

	@PreAuthorize("hasAnyRole('ADMIN')")

	public ResponseEntity<Collections<ProductCategoryDTO>> findAll(
			@ApiParam(value = "active", required = false) @RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(productCategoryManager
				.findAll(active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)),
				HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findByProductCategory.value}", notes = "${ProductCategoryServices.findByProductCategory.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/product-categories/{user:.+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ProductCategoryDTO> findByProductCategory(
			@ApiParam(value = "${ProductCategoryServices.findByProductCategory.param1}", required = false) @RequestParam(value = "active", required = false, defaultValue = "") String active,
			@PathVariable("productCategoryuctCategory") @ApiParam(value = "productCategory", required = true) String productCategory) {
		final Boolean isActive = active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active);
		final boolean isNumberic = StringUtils.isNumeric(productCategory);
		return new ResponseEntity<ProductCategoryDTO>(
				isNumberic ? productCategoryManager.findById(Long.valueOf(productCategory), isActive)
						: productCategoryManager.findByName(productCategory, isActive),
				HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findIdentitiesByProductCategory.value}", notes = "${ProductCategoryServices.findIdentitiesByProductCategory.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/product-categories/get-ids-by-names", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<Long>> findIdentitiesByProductCategory(
			@ApiParam(value = "productCategories", required = true) @RequestBody Collections<String> productCategories,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Collections<Long>>(productCategoryManager.findIdByName(productCategories,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findIdentityByProductCategory.value}", notes = "${ProductCategoryServices.findIdentityByProductCategory.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/product-categories/{name:.+}/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<Long>> findIdentityByProductCategory(
			@PathVariable("name") @ApiParam(value = "name", required = true) String name,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Single<Long>>(productCategoryManager.findIdByName(name,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findProductCategoriesByName.value}", notes = "${ProductCategoryServices.findProductCategoriesByName.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/product-categories/get-by-names", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductCategoryDTO>> findProductCategoriesByName(
			@ApiParam(value = "productCategories", required = true) @RequestBody Collections<String> productCategories,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(productCategoryManager.findByName(productCategories,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findProductCategoryiesById.value}", notes = "${ProductCategoryServices.findProductCategoryiesById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/product-categories/get-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductCategoryDTO>> findProductCategoryiesById(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(productCategoryManager.findById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findProductCategoryNameById.value}", notes = "${ProductCategoryServices.findProductCategoryNameById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/product-categories/{identity}/name", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Single<String>> findProductCategoryNameById(
			@PathVariable("identity") @ApiParam(value = "identity", required = true) long identity,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Single<String>>(productCategoryManager.findNameById(identity,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.findProductCategoryNamesByIdentity.value}", notes = "${ProductCategoryServices.findProductCategoryNamesByIdentity.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/product-categories/get-names-by-ids", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<String>> findProductCategoryNamesByIdentity(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = "") String active) {
		return new ResponseEntity<Collections<String>>(productCategoryManager.findNameById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${ProductCategoryServices.update.value}", notes = "${ProductCategoryServices.update.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/product-categories", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<ProductCategoryDTO>> update(

	@ApiParam(value = "productCategories", required = true) @RequestBody Collections<ProductCategoryDTO> productCategories) {
		return new ResponseEntity<Collections<ProductCategoryDTO>>(productCategoryManager.update(productCategories), HttpStatus.OK);
	}
}
