package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.exceptions.SmsException;
import com.devsuperior.dsmeta.service.SmsService;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

	@Autowired
	private SmsService smsService;
	
	@GetMapping("/{id}/notification")
	public ResponseEntity<?> notifySms(@PathVariable Long id) {
		try {
			
			this.smsService.sendSms(id);
			return ResponseEntity.ok().build();
			
		} catch (SmsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
