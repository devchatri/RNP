package Controller;

import Model.BDD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "citoyens", urlPatterns = {"/citoyens"})
public class citoyens extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session existante ou créer une nouvelle si elle n'existe pas
        HttpSession session = request.getSession(false);
        if (session != null) {
            // La session existe, vérifier si l'utilisateur est authentifié
            String username = (String) session.getAttribute("username");
            if (username != null) {
                // L'utilisateur est authentifié, continuer avec la logique de la servlet
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet resultSet = null;
                try {
                    con = BDD.getConnection();
                    String query = "SELECT `info_id`, `nom`, `prenom`, `date_rdv`, `status` FROM `infos`";
                    pst = con.prepareStatement(query);
                    resultSet = pst.executeQuery();

                    // Transférer le ResultSet à la page JSP pour afficher les données
                    if (resultSet != null) {
                        request.setAttribute("resultSet", resultSet);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/test.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Gérer le cas où le ResultSet est null
                        // Redirection vers une page d'erreur ou autre traitement nécessaire
                        response.sendRedirect("/error.jsp");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer l'exception SQL
                    // Redirection vers une page d'erreur ou autre traitement nécessaire
                    response.sendRedirect("/error.jsp");
                } finally {
                    // Fermeture des ressources
                    try {
                        if (pst != null) {
                            pst.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
                response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            }
        } else {
            // La session n'existe pas, rediriger vers la page de connexion
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }
    }
}
