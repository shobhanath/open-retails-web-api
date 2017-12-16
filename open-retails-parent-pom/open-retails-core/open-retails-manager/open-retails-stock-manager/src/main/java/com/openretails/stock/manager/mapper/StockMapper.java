package com.openretails.stock.manager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.common.constant.SpringBeanIds;

@Component(SpringBeanIds.STOCK_MAPPER)
public class StockMapper {

	@Autowired
	private ModelMapper modelMapper;



}
