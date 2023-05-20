package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connexion {
    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost/java";
    private static Connection cn = null;
    static { try {

        Class.forName("com.mysql.cj.jdbc.Driver");

//Étape 2 : Authentification auprèsdela base dedonnéesetsélectionnerleschéma
        cn = DriverManager.getConnection(url, login, password);
    }
    catch (ClassNotFoundException ex) {
        System.out.println("Impossible de charger le driver");
        ex.printStackTrace();
    }
    catch (SQLException ex) {
        System.out.println("Erreur de connexion");
    }
    }
    public static Connection getCn() {
        return cn;
    }}