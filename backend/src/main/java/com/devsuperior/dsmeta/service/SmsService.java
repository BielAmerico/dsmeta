package com.devsuperior.dsmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.exceptions.SmsException;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;
	
	public void sendSms(Long saleId) {
		
		try {
			
			Sale sale = this.saleRepository.findById(saleId).get();
			
			Twilio.init(this.twilioSid, this.twilioKey);

			PhoneNumber to = new PhoneNumber(this.twilioPhoneTo);
			PhoneNumber from = new PhoneNumber(this.twilioPhoneFrom);

			String msgToSend = this.generateMessageToSend(sale);
		
			Message message = Message.creator(to, from, msgToSend).create();

			// TODO - Adicionar LOG
			System.out.println(message.getSid());
			
		} catch (Exception exception) {
			throw new SmsException("Erro ao enviar sms para o vendedor!");
		}
	}
	
	private String generateMessageToSend(Sale sale) {
		
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		String msg = String.format("O vendedor %s foi desta em %s com um total de R$ %.0f.", 
									sale.getSellerName(), date, sale.getAmount());
		return msg;
	}
}
