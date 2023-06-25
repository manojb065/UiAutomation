package com.fampay.automation.util.email;

import com.fampay.automation.util.helper.FileUtil;
import com.fampay.automation.util.logging.LogUtil;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ReportEmailSender {
    public boolean sendEmail(String from,String testSuite,String reportName) throws Exception{
        boolean flag = false;


        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.debug", "true");

        String username = "manoj.b@fampay.in";
        String password = "phnqekokiodaiwzr";
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("rahul.sharma@fampay.in"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress("manoj.b@fampay.in"));
            message.setFrom(new InternetAddress(from));
            message.setSubject(String.format("Execution Report of %s", testSuite));
            MimeBodyPart info = new MimeBodyPart();
            info.setText(String.format("%s have executed %s suite on %s",from.split("@")[0],testSuite,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(new File(FileUtil.getAppAbsolutePath(String.format("reports/%s",reportName))));
            MimeMultipart muilt = new MimeMultipart();
            muilt.addBodyPart(info);
            muilt.addBodyPart(attachment);
            message.setContent(muilt);
            Transport.send(message);
          flag = true;
        return flag;
    }
}
