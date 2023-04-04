package be.vdab.mail.mailing;

import be.vdab.mail.domain.Lid;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LidMailing {
    private final JavaMailSender sender;
    private final String userName;
    private final String emailAdresWebMaster;
    public LidMailing(JavaMailSender sender,
                      @Value("${spring.mail.username}") String userName,
                      @Value("${emailAdresWebMaster}") String emailAdresWebMaster) {
        this.sender = sender;
        this.userName = userName;
        this.emailAdresWebMaster = emailAdresWebMaster;
    }
    @Async
    public void stuurMailNaRegistratie(Lid lid) throws MessagingException {
        var message = sender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        helper.setFrom(userName);
        helper.setTo(lid.getEmailAdres());
        helper.setSubject("Geregistreerd");
        helper.setText("<h1>Je bent nu lid.</h1>Je nummer is:" + lid.getId(), true);
        sender.send(message);
    }
    public void stuurMailMetAantalLeden(long aantalLeden) throws MessagingException {
        var message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(emailAdresWebMaster);
        message.setSubject("Aantal Leden");
        message.setText(aantalLeden + " leden");
        sender.send(message);
    }
}
