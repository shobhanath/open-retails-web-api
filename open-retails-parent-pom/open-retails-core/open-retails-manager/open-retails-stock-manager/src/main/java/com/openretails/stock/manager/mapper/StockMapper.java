package com.openretails.stock.manager.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openretails.common.constant.SpringBeanIds;
import com.openretails.data.StockDTO;
import com.openretails.stock.model.Stock;

@Component(SpringBeanIds.STOCK_MAPPER)
public class StockMapper {

	@Autowired
	private ModelMapper modelMapper;

	public StockDTO map(Stock stock) {
		return modelMapper.map(stock, StockDTO.class);
	}

	public Stock map(StockDTO stockDTO) {
		stockDTO.getProduct().setName(stockDTO.getProduct().getName().trim().toLowerCase());
		return modelMapper.map(stockDTO, Stock.class);
	}

	public Collection<StockDTO> mapDTO(Collection<Stock> stocks) {
		return stocks.stream().map(product -> map(product)).collect(Collectors.toList());
	}

	public Collection<Stock> mapEntity(Collection<StockDTO> stocksDTO) {
		return stocksDTO.stream().map(product -> map(product)).collect(Collectors.toList());
	}

}
