package org.colapietro.mail;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Pete Colapietro
 */
public class SimplerMailMessageClient {

  public static void main(String args[]) {
    final String simpleMailConfigurationFile = "mail-bean.xml";
    final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(simpleMailConfigurationFile);
    final SimplerMailMessage peteEmailAPI = (SimplerMailMessage) context.getBean("peteEmail");
    peteEmailAPI.sendEmail();
  }
}
