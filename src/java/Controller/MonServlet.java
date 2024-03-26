import Model.BDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

@WebServlet(name = "MonServlet", urlPatterns = {"/MonServlet"})
@MultipartConfig
public class MonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email"); // Récupérer l'email de l'utilisateur depuis la session

        // Vérifier si l'email est null ou vide
        if (email == null || email.isEmpty()) {
            // Gérer le cas où l'email n'est pas défini dans la session
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email non trouvé dans la session");
            return;
        }

        // Récupérer les données du formulaire
        String nom = request.getParameter("firstName");
        String prenom = request.getParameter("lastName");
        String country = request.getParameter("country");
        String date = request.getParameter("dob");

        // Récupérer le fichier importé
        Part filePart = request.getPart("file");
        String fileName = null;
        if (filePart != null) {
            fileName = getFileName(filePart);
            // Vous pouvez sauvegarder le fichier sur le serveur ici
            // Exemple : saveFile(filePart);
        }

        // Récupérer la valeur de l'âge à partir du formulaire
        String ageString = request.getParameter("age");
        int age = 0; // Valeur par défaut

        // Vérifier si la valeur de l'âge n'est pas null et n'est pas une chaîne vide
        if (ageString != null && !ageString.isEmpty()) {
            // Convertir la chaîne de caractères en entier
            age = Integer.parseInt(ageString);
        }

        // Enregistrer les données dans la base de données
        try {
            Connection con = BDD.getConnection();
            String query = "UPDATE infos SET nom = ?, prenom = ?, age = ?, country = ?, date_rdv = ?, fichier = ? WHERE email = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setInt(3, age);
            pst.setString(4, country);
            pst.setString(5, date);
            pst.setString(6, fileName); // Nom du fichier, ou null si aucun fichier n'est téléchargé
            pst.setString(7, email); // Mettre à jour uniquement les enregistrements avec l'email de l'utilisateur actuel
            
            // Vérifier que le champ "country" n'est pas nul
            if (country != null && !country.isEmpty()) {
                // Exécuter la requête de mise à jour
                pst.executeUpdate();
                con.close();

                // Ajouter les données comme attributs de la requête
                request.setAttribute("firstName", nom);
                request.setAttribute("lastName", prenom); // Notez que le prénom est utilisé comme nom de famille
                request.setAttribute("age", age);
                request.setAttribute("country", country);
                request.setAttribute("dob", date); // "dob" correspond à la date de naissance
                request.setAttribute("fileName", fileName);

                // Rediriger vers une page de confirmation ou une autre page appropriée
                response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
            } else {
                // Le champ "country" est null ou vide, afficher une erreur
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Le champ \"country\" ne peut pas être vide.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Gérer les exceptions liées à la base de données
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erreur lors de la mise à jour des données dans la base de données : " + ex.getMessage());
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void saveFile(Part part) throws IOException {
        String fileName = getFileName(part);
        if (fileName != null && !fileName.isEmpty()) {
            String filePath = "/path/to/save/" + fileName; // Chemin où sauvegarder le fichier sur le serveur
            try (InputStream input = part.getInputStream();
                 OutputStream output = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
