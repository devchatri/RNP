/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.BDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author dell
 */
@WebServlet(name = "LoginAdmin", urlPatterns = {"/LoginAdmin"})
public class LoginAdmin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Vérifier l'authentification
        if (authenticate(username, password)) {
            // Si l'authentification réussit, ouvrir une session et y mettre le nom d'utilisateur
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            // Rediriger vers la page des citoyens
            response.sendRedirect(request.getContextPath() + "/citoyens");
        } else {
            // Si l'authentification échoue, rediriger vers la page de connexion avec un message d'erreur
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=1");
        }
    }

    private boolean authenticate(String username, String password) {
        try {
            Connection con = BDD.getConnection();
            String query = "SELECT id FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            // Si une ligne est retournée, l'utilisateur est authentifié
            if (rs.next()) {
                return true;
            }
            
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
