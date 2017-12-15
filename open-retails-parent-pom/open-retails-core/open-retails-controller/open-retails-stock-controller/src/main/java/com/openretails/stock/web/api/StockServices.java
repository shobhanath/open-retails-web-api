package com.openretails.stock.web.api;

import org.springframework.web.bind.annotation.RestController;

import com.openretails.stock.web.exception.handler.GenericExceptionHandler;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/stocks")
public class StockServices extends GenericExceptionHandler {

}
