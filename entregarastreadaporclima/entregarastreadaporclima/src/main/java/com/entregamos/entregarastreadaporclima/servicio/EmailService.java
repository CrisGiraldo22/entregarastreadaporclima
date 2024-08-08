package com.entregamos.entregarastreadaporclima.servicio;

import com.entregamos.entregarastreadaporclima.excepciones.EmailSendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendDelayNotification(String email, String forecastDescription) {
        String message = String.format("Hola! Tenemos programada la entrega de tu paquete para mañana, en la dirección de entrega esperamos un día con %s y por esta razón es posible que tengamos retrasos. Haremos todo a nuestro alcance para cumplir con tu entrega.", forecastDescription);

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("Notificación de Retraso en la Entrega");
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendException("Error al enviar el correo electrónico", e);
        }
    }
}
