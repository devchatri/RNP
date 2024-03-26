/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.UserModel;

/**
 *
 * @author dell
 */
@WebServlet(name = "CheckStatusServlet", urlPatterns = {"/CheckStatusServlet"})
public class CheckStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'email depuis la requête
        String email = request.getParameter("email");
        
        // Vérification du statut de l'utilisateur
        String status = UserModel.getUserStatus(email);
        
        // Redirection vers reponse.jsp avec le statut récupéré
        request.setAttribute("status", status);
        request.getRequestDispatcher("reponse.jsp").forward(request, response);
    }

}
