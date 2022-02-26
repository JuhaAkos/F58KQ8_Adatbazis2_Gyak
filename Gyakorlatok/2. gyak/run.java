package runner;

//import java.sql.DriverManager;
import java.sql.*;

public class run {

	private static final String URL="jdbc:oracle:thin:@193.6.5.58:1521:XE";
	public static void main(String[] args) {
		try {
			Connection conn = connect("H22_F58KQ8","F58KQ8");
			//createTable(conn); //csak egyszer!
			//insertCar(conn);
			//setPriceOfCarByColor(conn,"zöld",100);
			//setPriceOfCarByColorPrep(conn,"kék",100);
			String[] sqlString = {"insert into car value(10,'Opel','fehér',300",
			                      "insert into car value(11,'Opel','szürke',300",
			                      "insert into car value(12,'Opel','fehér',300"};
			insertMultipleCar(conn, sqlString);
			
			System.out.println("Ez itten a vege!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connect(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, username, password);
		return conn;
	}
	
	public static void createTable (Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE CAR ( "
				+"id number(4) primary key, "
				+"manufacturer varchar2(200) not null, "
				+"color varchar2(20) not null, "
				+"price number(5) not null "
				+")");
	}
	
	public static void insertCar(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		System.out.println("Insert returned: " + stmt.executeUpdate(""
		+ "insert into car values(2, 'Skoda','kék',6000) "));
	}
	
	public static void setPriceOfCarByColor(Connection conn, String color, int price) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update car set price"+price+" where color='"+color+"'");
	}
	//ez nem biztonságos! 
	
	public static void setPriceOfCarByColorPrep(Connection conn, String color, int price) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement(
		"update car set price=? where color=?");
		prstmt.setInt(1,  price);
		prstmt.setString(2,  color);
		prstmt.execute();
	}
	
	public static void insertMultipleCar(Connection conn, String[] insertSql) throws SQLException{
		Statement stmt = conn.createStatement();
		for (String sql:insertSql) {
			stmt.addBatch(sql);
		}
		System.out.println(stmt.executeBatch());
	}
	
}
