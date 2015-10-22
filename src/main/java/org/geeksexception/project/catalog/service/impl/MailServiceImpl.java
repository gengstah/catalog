package org.geeksexception.project.catalog.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.geeksexception.project.catalog.service.MailService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
@Transactional(readOnly = true)
@PropertySource(value = {"classpath:/user-mail.properties", "classpath:/mail.properties"})
public class MailServiceImpl implements MailService {
	
	private @Inject JavaMailSender mailSender;
	
	@SuppressWarnings("unused")
	private @Inject SimpleMailMessage template;
	
	private @Inject Environment env;
	
	private @Inject VelocityEngine velocityEngine;
	
	@Override
	public void sendTestMail() {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo("gerardpdelasarmas@gmail.com");
				message.setSubject("Catalog");
				message.setFrom(env.getProperty("mail.username"));
				Map<String, Object> model = new HashMap<String, Object>();
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "mail-templates/catalog-email.html", "UTF-8",
						model);
				message.setText(text, true);
			}
		};
		
		mailSender.send(preparator);
		
	}

}