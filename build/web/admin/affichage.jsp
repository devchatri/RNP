<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %> <!-- Ajout de l'importation -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des citoyens</title>
</head>
<body>
    <h2>Liste des citoyens</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
                if(resultSet != null) {
                    while(resultSet.next()) {
            %>
            <tr>
                <td><%= resultSet.getInt("info_id") %></td>
                <td><%= resultSet.getString("nom") %></td>
                <td><%= resultSet.getString("prenom") %></td>
            </tr>
            <% 
                    }
                    resultSet.close();
                } else {
            %>
            <tr>
                <td colspan="3">Aucun citoyen trouvé.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
