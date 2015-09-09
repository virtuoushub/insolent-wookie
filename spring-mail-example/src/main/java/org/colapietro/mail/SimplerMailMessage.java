package org.colapietro.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Pete Colapietro
 * @see SimpleMailMessage
 */
@Service("peteEmail")
public class SimplerMailMessage {

  private final String toAddress;
  private final String subject;
  private final String msgBody;

  public SimplerMailMessage (final String toAddress, final String subject, final String msgBody) {
    this.toAddress  = toAddress;
    this.subject    = subject;
    this.msgBody    = msgBody;
  }
  /**
   * MailSender interface defines a strategy for sending simple mails
   */
  @Autowired
  private JavaMailSender peteEmail;

  public void sendEmail() {
    final SimpleMailMessage peteMessage = new SimpleMailMessage();
    peteMessage.setTo(toAddress);
    peteMessage.setSubject(subject);
    peteMessage.setText(msgBody);
    peteEmail.send(peteMessage);
  }
}
