/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/conform.jsp", "/remplissage.jsp", "/accueil.jsp", })
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            // Utilisateur authentifié, laissez la requête passer
            chain.doFilter(request, response);
        } else {
            // Utilisateur non authentifié, rediriger vers index.jsp
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        }
    }

    // Autres méthodes de l'interface Filter (init, destroy) à implémenter si nécessaire
}
