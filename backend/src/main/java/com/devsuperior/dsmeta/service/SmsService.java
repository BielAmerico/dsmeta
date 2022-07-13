package com.devsuperior.dsmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	/* Parâmetro onde o valor do "twilio.sid" será atribuído para o "String twilioSid". Dessa forma
	 * eu escondo informações frágeis */
	 
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
		
		/* função para trazer o id */
		Sale sale = saleRepository.findById(saleId).get();
		
		/* Está função irá receber a minha data / mês e / ano*/
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
 	
		/* Mensagem que irá como notificação para o número */
		
		String msg = " O Vendedor" + sale.getSellerName() +" foi destaque em " + date
				+ "com um total de R$ "+ String.format("%.2f", sale.getAmount());
											/*forma de formatar um número com duas casas decimais no java*/
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, "msg").create();

		System.out.println(message.getSid());
	}
}
