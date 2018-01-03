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
import com.openretails.data.StockDTO;
import com.openretails.stock.manager.StockManager;
import com.openretails.stock.web.exception.handler.GenericExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/stocks")
public class StockServices extends GenericExceptionHandler {

	@Autowired
	private StockManager stockManager;

	@ApiOperation(value = "${StockServices.create.value}", notes = "${StockServices.create.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/stocks", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> create(

	@ApiParam(value = "stocks", required = true) @RequestBody Collections<StockDTO> stocks) {
		return new ResponseEntity<>(stockManager.create(stocks), HttpStatus.OK);
	}

	@ApiOperation(value = "${StockServices.doSearch.value}", notes = "${StockServices.doSearch.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })
	@GetMapping(value = "/stocks/search", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> doSearch(
			@ApiParam(value = "key", required = false) @RequestParam(value = "key", required = false, defaultValue = ApplicationConstants.EMPTY) String key) {

		if (key.equalsIgnoreCase(ApplicationConstants.EMPTY)) {
			return new ResponseEntity<>(
					stockManager
					.findAll(null), HttpStatus.OK);
		}
		return new ResponseEntity<>(StringUtils.isNumeric(key)
				? stockManager.findByProductNameContainingOrIdentityObseleteTrue(key, Long.valueOf(key))
				: stockManager.findByProductNameContainingOrIdentityObseleteTrue(key, null), HttpStatus.OK);
	}


	@ApiOperation(value = "${StockServices.enableOrDisable.value}", notes = "${StockServices.enableOrDisable.note}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/stocks/{enable}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> enableOrDisable(
			@ApiParam(value = "productIds", required = true) @RequestBody Collections<Long> productIds,
			@ApiParam(value = "enable", required = true) @PathVariable("enable") boolean enable) {
		return new ResponseEntity<>(stockManager.enableOrDisable(productIds, enable), HttpStatus.OK);
	}

	@ApiOperation(value = "${StockServices.findAll.value}", notes = "${StockServices.findAll.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/stocks", produces = { MediaType.APPLICATION_JSON_VALUE })

	@PreAuthorize("hasAnyRole('ADMIN')")

	public ResponseEntity<Collections<StockDTO>> findAll(
			@ApiParam(value = "active", required = false) @RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(
				stockManager
				.findAll(active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${StockServices.findById.value}", notes = "${StockServices.findById.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@GetMapping(value = "/stocks/{identity}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<StockDTO> findById(
			@PathVariable("identity") @ApiParam(value = "identity", required = true) long identity,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(stockManager.findById(identity,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${StockServices.findByIds.value}", notes = "${StockServices.findByIds.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/stocks/get-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> findByIds(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(stockManager.findById(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}


	@ApiOperation(value = "${StockServices.findByProductIds.value}", notes = "${StockServices.findByProductIds.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/stocks/get-by-product-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> findByProductIds(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
		return new ResponseEntity<>(stockManager.findByProductId(identities,
				active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}

	@ApiOperation(value = "${StockServices.findProductNameByStockId.value}", notes = "${StockServices.findProductNameByStockId.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/stocks/get-name-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<String>> findProductNameByStockId(
			@ApiParam(value = "identities", required = true) @RequestBody Collections<Long> identities,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
			return new ResponseEntity<>(stockManager.findProductNameById(identities,
					active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);
	}


	@ApiOperation(value = "${StockServices.findStockIdByProductName.value}", notes = "${StockServices.findStockIdByProductName.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PostMapping(value = "/stocks/get-id-by-names", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<Long>> findStockIdByProductName(
			@ApiParam(value = "names", required = true) @RequestBody Collections<String> names,
			@RequestParam(value = "active", required = false, defaultValue = ApplicationConstants.EMPTY) String active) {
			return new ResponseEntity<>(stockManager.findIdByProductName(names,
					active.equals(ApplicationConstants.EMPTY) ? null : Boolean.valueOf(active)), HttpStatus.OK);

	}

	@ApiOperation(value = "${StockServices.update.value}", notes = "${StockServices.update.note}")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = ExceptionMessage.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ExceptionMessage.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ExceptionMessage.class),
			@ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class),
			@ApiResponse(code = 500, message = "Something wrong in Server", response = ExceptionMessage.class) })

	@PutMapping(value = "/stocks", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Collections<StockDTO>> update(

	@ApiParam(value = "stocks", required = true) @RequestBody Collections<StockDTO> stocks) {
		return new ResponseEntity<>(stockManager.update(stocks), HttpStatus.OK);
	}

}
