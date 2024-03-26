package Controller;

import Model.BDD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "preform", urlPatterns = {"/preform"})
public class preform extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    int row;

    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {

        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        try {
            con = BDD.getConnection();
            String email = req.getParameter("email");

            // Générer un code aléatoire
            String code = generateRandomCode();

            // Insérer l'e-mail et le code dans la table "user" avec le statut "nothing"
            pst = con.prepareStatement("INSERT INTO infos(email, code, status, country, fichier, date_rdv) VALUES (?, ?, 'nothing', 'Morocco', 'vide', '0000-00-00')");
            pst.setString(1, email);
            pst.setString(2, code);
            row = pst.executeUpdate();
            
            req.getSession().setAttribute("email", email);
            rsp.sendRedirect("/RNP/codeconf.jsp");


            // Envoyer le code par e-mail (vous devez implémenter cette fonctionnalité)
            
            // Envoyer le code par e-mail
            EmailSender.sendEmail(email, code);

        } catch (SQLException ex) {
            out.println("<font color='red'>  Record Failed: " + ex.getMessage() + "   </font>");
            ex.printStackTrace();
        } finally {
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
    }

    // Méthode pour générer un code aléatoire
    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
