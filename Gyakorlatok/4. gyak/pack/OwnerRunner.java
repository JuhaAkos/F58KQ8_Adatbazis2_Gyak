package pack;

import java.sql.*;
import java.util.*;

import model.Car;


public class OwnerRunner {

	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	public static void main(String[] args) throws ClassNotFoundException {		
		try {
			Connection conn = connect("H22_F58KQ8","F58KQ8");
			
			//createOwnerTable(conn);
			//alterCarTable(conn);
			//addOwner(conn,new Owner(1, "János", new java.sql.Date(new java.util.Date().getTime())));
			//updateOwnerId(conn, 1, "Opel");           //Valami nem stimmelt
			//getAllData(conn);
			//addOwner2(conn,);
			//getCar(conn, "Opel");                      //HIBA
			
			Scanner sc = new Scanner (System.in);
			System.out.println("Ide kerek szamot: ");
			callableTest(conn, sc.nextDouble());
			closeDb(conn);
			System.out.println("Ezzel vege!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} 

	public static Connection connect(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL,
				username, password);
		return conn;
	}
		

	public static void createOwnerTable(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "CREATE TABLE owner("
				+ "id int primary key,"
				+ "name varchar2(200),"
				+ "birth date"
				+ ")");
		prstmt.executeUpdate();
	}
		
	public static void alterTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("ALTER TABLE car ADD owner_id int CONSTRAINT owner_car REFERENCES Owner(id)");
	} 
	
	public static void addOwner(Connection conn, Owner owner) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO Owner VALUES(?, ?, ?)");
		prstmt.setInt(1, owner.getId());
		prstmt.setString(2, owner.getName());
		prstmt.setDate(3, owner.getBirth());
		prstmt.executeUpdate();
		
		System.out.println("AddOwner vege!");
		
	}
	
	public static void updateOwnerId(Connection conn, int id, String manufactrer) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+"UPDATE Car SET owner_id WHERE manufacturer = ?");
		prstmt.setInt(1, id);
		prstmt.setString(2, manufactrer);
		
		System.out.println("updated: " + prstmt.executeUpdate());
		prstmt.close();
	}
	
	public static void closeDb(Connection conn) throws SQLException {
		conn.close();
	}
	
	public static void getAllData(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+"SELECT * FROM car INNER JOIN Owner ON(car.id = owner.id)"); //nem fix h jó az SQL
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString("name")+"\n"+rs.getString("manufacturer"));
		}
		
		rs.next();
		System.out.println(rs.getInt(1));
		rs.close();
		prstmt.close();
	}
	
	//alternatív hozzáadás:
	public static void addOwner2(Connection conn, int id, String birth) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+"INSERT INTO Owner VALUES(?, ?, {d ?})"); //"yyyy-mm-dd" formátum
		prstmt.setInt(1, id);
		prstmt.setString(2, "Hello");
		prstmt.setString(3, birth);
		
		System.out.println(prstmt.executeUpdate());
		prstmt.close();
	}
	

	public static void getCar(Connection conn, String manufacturer) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM Car WHERE manufacturer = ?", 
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		prstmt.setString(1, manufacturer);
		ResultSet rs = prstmt.executeQuery();
		
		List<Car> carList = new ArrayList<Car>();
		
		while (rs.next()) {
			rs.updateString("color", "hupilila");
			rs.updateRow();
			carList.add( new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		
		for(Car car : carList) {
			System.out.println(car);
		}
	}
		
	public static void callableTest(Connection conn, double alpha) throws SQLException {
		CallableStatement cstmt = conn.prepareCall(
				"{?=call cos(?)}");
		cstmt.registerOutParameter(1, Types.NUMERIC);
		cstmt.setDouble(2, alpha);
		cstmt.execute();
		System.out.println(cstmt.getDouble(1));
	}
	
}
