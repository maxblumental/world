import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDerbyDatabase {

  public static void main(String[] args) throws Exception {
    //foo();
    String url = "jdbc:derby:zoo";
    try (Connection connection = DriverManager.getConnection(url);
         Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery("select name from animals order by id");
      rs.absolute(0);
      rs.next();
      System.out.println(rs.getString(1));
    }
  }

  private static void foo() throws SQLException {
    String url = "jdbc:derby:zoo;create=true";

    try (Connection connection = DriverManager.getConnection(url);
         Statement statement = connection.createStatement()) {

      statement.executeUpdate("CREATE TABLE species ("
          + "id INTEGER PRIMARY KEY, "
          + "name VARCHAR(255), "
          + "num_acres DECIMAL)");
      statement.executeUpdate("CREATE TABLE animals("
          + "id INTEGER PRIMARY KEY, "
          + "species_id integer, "
          + "name VARCHAR(255), "
          + "date_born TIMESTAMP)");

      statement.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
      statement.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

      statement.executeUpdate("INSERT INTO animals VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
      statement.executeUpdate("INSERT INTO animals VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
      statement.executeUpdate("INSERT INTO animals VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
      statement.executeUpdate("INSERT INTO animals VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
      statement.executeUpdate("INSERT INTO animals VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");
    }
  }
}
