<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Réponse</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h2 {
            text-align: center;
            margin-top: 50px;
        }

        .message {
            margin: 20px auto;
            padding: 10px;
            max-width: 400px;
            background-color: #f2f2f2;
            border-left: 4px solid #007bff;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2>Réponse</h2>
    <% 
        String status = (String)request.getAttribute("status");
        if(status.equals("nothing")) {
    %>
        <div class="message">Votre profil n'a pas encore été confirmé.</div>
    <% } else if(status.equals("complet")) { %>
        <div class="message">Votre profil a été confirmé.</div>
    <% } else { %>
        <div class="message">Erreur lors de la récupération du statut.</div>
    <% } %>
</body>
</html>
