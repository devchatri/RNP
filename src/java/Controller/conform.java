/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.BDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
@WebServlet(name = "conform", urlPatterns = {"/conform"})
public class conform extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userCode = request.getParameter("codeconf");
        String email = request.getSession().getAttribute("email").toString(); // Récupérer l'email de la session
        if (email == null) {
            response.sendRedirect("/RNP/form.jsp");
        }
        // Comparez le code saisi par l'utilisateur avec le code stocké dans la base de données
        if (validateCode(email, userCode)) {
            // Rediriger vers la page d'accueil si le code est valide
            response.sendRedirect("/RNP/remplissage.jsp");
        } else {
            // Rediriger vers une page d'erreur si le code est invalide
            response.sendRedirect("/RNP/error.jsp");
        }
    }

    private boolean validateCode(String email, String userCode) {
    try {
        // Connectez-vous à la base de données
        Connection con = BDD.getConnection();
        
        // Préparez la requête pour récupérer le code associé à l'email donné
        PreparedStatement pst = con.prepareStatement("SELECT code FROM infos WHERE email = ?");
        pst.setString(1, email);
        
        // Exécutez la requête
        ResultSet rs = pst.executeQuery();
        
        // Vérifiez si un enregistrement correspondant a été trouvé
        if (rs.next()) {
            // Récupérez le code de la base de données
            String dbCode = rs.getString("code");
            
            // Comparez le code de la base de données avec le code fourni par l'utilisateur
            if (dbCode.equals(userCode)) {
                // Le code est valide, retournez true
                return true;
            }
        }
    } catch (SQLException ex) {
        // Gérez les exceptions liées à la base de données
        ex.printStackTrace();
    }
    
    // Le code n'est pas valide, retournez false
    return false;
}

}
