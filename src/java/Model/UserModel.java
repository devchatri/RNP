package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    // Méthode pour vérifier si l'utilisateur existe dans la base de données
    public static boolean userExists(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BDD.getConnection();
            String sql = "SELECT * FROM infos WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            return rs.next(); // Retourne vrai si l'utilisateur existe, sinon faux
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour obtenir le statut de l'utilisateur à partir de l'email
    public static String getUserStatus(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BDD.getConnection();
            String sql = "SELECT status FROM infos WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("status"); // Retourne le statut de l'utilisateur
            } else {
                return "Non trouvé"; // Si l'utilisateur n'existe pas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
