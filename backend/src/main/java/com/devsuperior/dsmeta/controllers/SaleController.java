package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.exceptions.SalesNotFoundException;
import com.devsuperior.dsmeta.service.SaleServices;


@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleServices service;
	
	@GetMapping
	public ResponseEntity< Page<Sale> > findSales(
			@RequestParam(value="minDate", defaultValue="") String minDate, 
			@RequestParam(value="maxDate", defaultValue="") String maxDate, 
			Pageable pageable) {
		
		try {
			
			return ResponseEntity.ok(service.findSales(minDate, maxDate, pageable));
			
		} catch (SalesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
