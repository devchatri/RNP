/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */
public class EmailSender {
    public static void sendEmail(String recipientEmail, String code) {
        // Paramètres SMTP du serveur de messagerie
        String host = "smtp.gmail.com";
        String port = "587";
        String username = ""; // Modifier avec votre adresse e-mail
        String password = ""; // Modifier avec votre mot de passe

        // Propriétés pour la session de messagerie
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Création de la session de messagerie
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Création du message e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Votre code de confirmation");
            message.setText("Votre code de confirmation est : " + code);

            // Envoi du message e-mail
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès à : " + recipientEmail);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    
    public static void sendEmailComplet(String recipientEmail) {
        // Paramètres SMTP du serveur de messagerie
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "chatritahaa@gmail.com"; // Modifier avec votre adresse e-mail
        String password = "nwsi znnz ubft wyrw"; // Modifier avec votre mot de passe

        // Propriétés pour la session de messagerie
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Création de la session de messagerie
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Création du message e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Votre code de confirmation");
            message.setText("Votre Dande a bien été effectuer en la registre national de la population ");

            // Envoi du message e-mail
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès à : " + recipientEmail);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
