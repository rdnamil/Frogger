import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
    private Connection conn = null;
    private Statement stmt = null;

    public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public Database() {
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

    public void insertHighscore(String name, int score) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver loaded");

            String db_url = "jdbc:sqlite:highscores.db";
			conn = DriverManager.getConnection(db_url);
			if (conn != null) {
                System.out.println("Connected succesfully!");

                String sqlInsert = "INSERT INTO highscores (name, score) VALUES (?, ?)";
                try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
                    pstmtInsert.setString(1, name);
                    pstmtInsert.setInt(2, score);
                    pstmtInsert.executeUpdate();
                    System.out.println("Score Successfully Inserted");
                }

                conn.close();
			}

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
    }

    public void displayHighscores() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver loaded");

            String db_url = "jdbc:sqlite:highscores.db";
			conn = DriverManager.getConnection(db_url);
			if (conn != null) {
                System.out.println("Connected succesfully!");

                String sqlSelect = "SELECT * FROM highscores ORDER BY score DESC";
                try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
                    ResultSet rs = pstmtSelect.executeQuery();
                    System.out.println("Displaying Highscores");

                    String highscores = "";
                    int count = 0;
                    while (rs.next()) {
                        if (count != 0) highscores += "\n";
                        count++;
                        highscores += String.format("%d. %s...%04d", count, rs.getString("name"), rs.getInt("score"));
                    }
                    if (count == 0)  highscores = "No Scores Submitted";

                    JOptionPane.showMessageDialog(null, highscores, "test", JOptionPane.DEFAULT_OPTION);

                    rs.close();
                }

                conn.close();
			}

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
    }
}
