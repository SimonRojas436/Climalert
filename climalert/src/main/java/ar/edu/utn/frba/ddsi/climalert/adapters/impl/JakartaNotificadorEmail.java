package ar.edu.utn.frba.ddsi.climalert.adapters.impl;

import ar.edu.utn.frba.ddsi.climalert.adapters.NotificadorEmailAdapter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JakartaNotificadorEmail implements NotificadorEmailAdapter {
  private final JavaMailSender mailSender;

  public JakartaNotificadorEmail(
      JavaMailSender mailSender
  ) {
    this.mailSender = mailSender;
  }

  /*<---------------------------------------------------------------------------------------------------------------->*/

  @Override
  public void enviarEmail(String email, String asunto, String cuerpo) {
    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setTo(email);
    mensaje.setSubject(asunto);
    mensaje.setText(cuerpo);
    mailSender.send(mensaje);
  }
}
