import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {


    public Database() {
        Connection conn = null;
        Statement stmt = null;


        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver loaded");

            String db_url = "jdbc:sqlite:highscores.db";
			conn = DriverManager.getConnection(db_url);
			if (conn != null) {
                System.out.println("Connected succesfully!");

                String sqlCreateTable = "CREATE TABLE IF NOT EXISTS highscores " +
                                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        " name TEXT NOT NULL, " +
                                        " score INTEGER NOT NULL)";
                try (PreparedStatement pstmtCreateTable = conn.prepareStatement(sqlCreateTable)) {
                    pstmtCreateTable.executeUpdate();
                    System.out.println("Table Successfully Created");
                }

                conn.close();
			}

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
    }
}
