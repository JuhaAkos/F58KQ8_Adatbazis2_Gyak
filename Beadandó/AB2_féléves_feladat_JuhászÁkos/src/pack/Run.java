package pack;

import pack.Akciok;
import pack.Etel;
import pack.Megrendeles;
import pack.ErKapcsolo;

import java.sql.*;
import java.sql.Date;
import java.util.*;

//fájlbaíráshoz:
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Run {
	
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	public static void main(String[] args) {
		try {		
			
			//bejelentkezes
			Scanner scmain = new Scanner(System.in);
			System.out.println("Kerem a bejelentkezeshez a nevet: ");
			String name=scmain.nextLine();
			System.out.println("Kerem a jelszavat: ");
			String password=scmain.nextLine();
			Connection conn = connect(name,password);

			menu(conn);							
			
			conn.close();
			System.out.println("Futás vége!");
			
			}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	//felcsatlakozás
	public static Connection connect(
		String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, username, password);
		return conn;		
	}
		
	//create Table 1.
	public static void createÉtelTable(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "CREATE TABLE etel("
				+ "eid int primary key,"
				+ "kisar int,"
				+ "nagyar int,"
				+ "tipus varchar2(200),"
				+ "nev varchar2(200)"
				+ ")");
		prstmt.executeUpdate();
		
		System.out.println("Etel tabla letrehozva!");
	}
	
	//create Table 2.
	public static void createAkciokTable(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "CREATE TABLE akciok("
				+ "eid int,"
				+ "foreign key(eid) references etel(eid),"
				+ "learazas int,"
				+ "honap int"
				+ ")");
		prstmt.executeUpdate();
		
		System.out.println("Akciok tabla letrehozva!");
	}
	
	//create Table 3.
	public static void createMegrendelesTable(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "CREATE TABLE megrendeles("
				+ "mid int primary key,"
				+ "datum date,"
				+ "megrendelo varchar2(200),"
				+ "cim varchar2(200),"
				+ "telefon varchar2(200)"
				+ ")");
		prstmt.executeUpdate();
		
		System.out.println("Megrendeles tabla letrehozva!");
	}		
		
	//create Table 4.
	public static void createErKapcsoloTable(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "CREATE TABLE er_kapcsolo("
				+ "eid int,"
				+ "foreign key(eid) references etel(eid),"
				+ "mid int,"
				+ "foreign key(mid) references megrendeles(mid)"
				+ ")");
		prstmt.executeUpdate();
		
		System.out.println("E-R kapcsolo tabla letrehozva!");
	}		
		
	//tabla feltoltes 1.
	public static void addEtel (Connection conn, Etel etel) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO etel VALUES(?, ?, ?, ?, ?)");
		prstmt.setInt(1, etel.getEid());
		prstmt.setInt(2, etel.getKisar());
		prstmt.setInt(3, etel.getNagyar());
		prstmt.setString(4, etel.getTipus());
		prstmt.setString(5, etel.getNev());
		prstmt.executeUpdate();
		
		System.out.println("AddEtel feltoltve!");
	}
	
	//tabla feltoltes 2.
	public static void addAkciok (Connection conn, Akciok akciok) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO akciok VALUES( ?, ?, ?)"); 
		prstmt.setInt(1, akciok.getEid());
		prstmt.setInt(2, akciok.getLearazas());
		prstmt.setInt(3, akciok.getHonap());
		prstmt.executeUpdate();
			
		System.out.println("AddAkciokfeltoltve!");
	}
	
	//tabla feltoltes 3.
	public static void addMegrendeles (Connection conn, Megrendeles megrendeles) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO megrendeles VALUES( ?, {d ?}, ?, ?, ?)");
		prstmt.setInt(1, megrendeles.getMid());
		prstmt.setDate(2, megrendeles.getDatum()); //DATE: "yyyy-mm-dd" formátum
		prstmt.setString(3, megrendeles.getMegrendelo());
		prstmt.setString(4, megrendeles.getCim());
		prstmt.setString(5, megrendeles.getTelefon());
		prstmt.executeUpdate();
				
		System.out.println("AddMegrendeles feltoltve!");
	}
	
	//tabla feltoltes 4.
	public static void addErKapcsolo (Connection conn, ErKapcsolo erKapcsolo) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO er_kapcsolo VALUES( ?, ?)");
		prstmt.setInt(1, erKapcsolo.getEid());
		prstmt.setInt(2, erKapcsolo.getMid());
		prstmt.executeUpdate();
					
		System.out.println("AddErKapcsolo feltoltve!");
	}

	//lekérdezések:
	//lekérdezés 1. - Bizonyos típusú ételek kiírása:
	public static void whereEtel(Connection conn, String melyTipus) throws SQLException {
		System.out.println("" + melyTipus + " típusú ételek kiírása:");
		System.out.println("----------------------------------------");
		PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM etel WHERE tipus =?");
		prstmt.setString(1, melyTipus);
		ResultSet rs = prstmt.executeQuery();
		while(rs.next()) {
		
			int eid = rs.getInt("eid");
			int kisar = rs.getInt("kisar");
			int nagyar = rs.getInt("nagyar");
			String tipus = rs.getString("tipus");		
			String nev = rs.getString("nev");
			
			System.out.println("" + eid + " - " + kisar + " - " + nagyar +  " - " + tipus + " - " + nev);
		}
		System.out.println("");
		rs.close();
		prstmt.close();
	}

	//lekérdezés 2. - Az év elsõ felében leárazáson lévõ ételek közül, a 2000-nál drágább kisárú ételek kiírása:
	//két mezõ szerinti szûrés, két tábla között
	public static void whereElsoDraga(Connection conn) throws SQLException {
		System.out.println("Az év elsõ felében leárazáson lévõ ételek közül, a 2000-nál drágább kisárú ételek kiírása:");
		System.out.println("------------------------------------------------------------------------------------------");
		PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM etel INNER JOIN akciok ON etel.eid = akciok.eid WHERE kisar>2000 AND honap<7");
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			int eid = rs.getInt("eid");
			int kisar = rs.getInt("kisar");
			String tipus = rs.getString("tipus");		
			String nev = rs.getString("nev");
			int honap = rs.getInt("honap");
			
			System.out.println("" + eid + " - " + kisar + " - " + nev + " - " + tipus + " - " + honap + ". honapban");
		}
		System.out.println("");
		rs.close();
		prstmt.close();
	}

	//lekérdezés 3. - Adott megrendelo által rendelt etelek:
	//két tábla közötti szûrés
	public static void whereMegrendelo(Connection conn, String megrendelo2) throws SQLException {
		System.out.println("" + megrendelo2 + "  által rendelt etelek:");
		System.out.println("-----------------------------------------------------------------------");
		
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "SELECT * FROM etel INNER JOIN er_kapcsolo ON etel.eid = er_kapcsolo.eid INNER JOIN megrendeles ON megrendeles.mid=er_kapcsolo.mid WHERE megrendelo=?");
		prstmt.setString(1, megrendelo2);		
		ResultSet rs = prstmt.executeQuery();
		
		while(rs.next()) {
			int eid = rs.getInt("eid");
			int kisar = rs.getInt("kisar");
			int nagyar = rs.getInt("nagyar");
			String tipus = rs.getString("tipus");			
			String nev = rs.getString("nev");
			int mid = rs.getInt("mid");
			
				
			System.out.println(""+ mid + ". azonositoju megrendeles: " + eid + " - " + kisar + " - " + nagyar +  " - " + tipus + " - " + nev);
		}
		
		System.out.println("");
		rs.close();
		prstmt.close();
	}

	
	//adatok módosítása 1. - Etelek
	public static void updateEtelAr(Connection conn, int eid, int kisar, int nagyar, String tipus, String nev) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("UPDATE etel SET kisar=?, nagyar=? , tipus=?, nev=? where eid=?");
				
		prstmt.setInt(1, kisar);
		prstmt.setInt(2, nagyar);
		prstmt.setString(3, tipus);
		prstmt.setString(4, nev);
		prstmt.setInt(5, eid);
		
		prstmt.executeUpdate();  
		
		System.out.println("\n-------------------------------------------------------------------");
		System.out.println("" + eid + ". etel modositva: " + kisar + " - " + nagyar + " - " + tipus + " - " + nev);
		prstmt.close();
	}
	
	//adatok módosítása 2. - Akciok
	public static void updateAkciok(Connection conn, int eid, int learazas, int honap) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("UPDATE akciok SET learazas=?, honap=? where eid=?");
		
		prstmt.setInt(1, learazas);
		prstmt.setInt(2, honap);
		prstmt.setInt(3, eid);
		
		prstmt.executeUpdate();  
			
		System.out.println("" + eid + ". etelhez tartozo akcio modositva: uj learazas - " + learazas + "% - " + honap + ". honapban");
		prstmt.close();
	}
	
	//adatok törlése 1.
	public static void deleteMegrendeles(Connection conn, int mid) throws SQLException {
		PreparedStatement prstmt2 = conn.prepareStatement("Delete er_kapcsolo where mid=?");
		PreparedStatement prstmt = conn.prepareStatement("Delete megrendeles where mid=?");
		prstmt2.setInt(1, mid);
		prstmt.setInt(1, mid);
		
		prstmt2.executeUpdate();  
		prstmt.executeUpdate();
		
		System.out.println("Megrendeles torolve: " + mid);
	}
	
	//adatok törlése 2.
	public static void deleteEtel(Connection conn, int eid) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("Delete etel where eid=?");
		PreparedStatement prstmt2 = conn.prepareStatement("Delete akciok where eid=?");
		PreparedStatement prstmt3 = conn.prepareStatement("Delete er_kapcsolo where eid=?");
		prstmt.setInt(1, eid);
		prstmt2.setInt(1, eid);
		prstmt3.setInt(1, eid);
		
		prstmt3.executeUpdate();
		prstmt2.executeUpdate(); 
		prstmt.executeUpdate();		  
		
		System.out.println("Etel torolve: " + eid);
	}

	//adatok kiírása fájlba:
	public static void etelToFile(Connection conn) throws SQLException{ 
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from etel");  
		
		try {  
			PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("EtelMenu.txt"), false )); 

			outputStream.write("Etelek tabla:\n");  
			outputStream.write("Azonosito: \t Kis ar: \t Nagy ar: \t Tipus: \t Nev:\n");  
			outputStream.write("-------------------------------------------------------------------------------------------------------------------");  
			outputStream.write("\n"); 
			
			while (rs.next()) {  
				int eid = rs.getInt("eid");  
				int kisar = rs.getInt("kisar");  
				int nagyar = rs.getInt("nagyar");  
				String tipus = rs.getString("tipus");  
				String nev = rs.getString("nev");  
				outputStream.write(eid + "\t\t" + kisar + "\t\t" + nagyar + "\t\t"+ tipus +"\t\t" + nev + "\n"); 
				
			}
			
			rs = stmt.executeQuery("Select * from akciok"); 
			
			outputStream.write("\nAkciok tabla:\n");  
			outputStream.write("Azonosito: \t Learazas: \t Honap: \n");  
			outputStream.write("-------------------------------------------------------------------------------------------------------------------");  
			outputStream.write("\n"); 
			
			while (rs.next()) {  
				int eid2 = rs.getInt("eid");  
				int learazas = rs.getInt("learazas");  
				int honap = rs.getInt("honap");  
				outputStream.write(eid2 + "\t\t" + learazas + "% \t\t" + honap + "\n"); 
				
			}
			
			rs = stmt.executeQuery("Select * from megrendeles"); 
			
			outputStream.write("\nMegrendeles tabla:\n");  
			outputStream.write("Azonosito: \t Datum: \t\t Megrendelo: \t\t Cim: \t\t Telefon:\n");  
			outputStream.write("-------------------------------------------------------------------------------------------------------------------");  
			outputStream.write("\n"); 
			
			while (rs.next()) {  
				int mid = rs.getInt("mid");  
				Date datum = rs.getDate("datum");  
				String megrendelo = rs.getString("megrendelo");  
				String cim = rs.getString("cim");  
				String telefon = rs.getString("telefon");  
				outputStream.write(mid + "  \t\t" + datum + "\t\t" + megrendelo + "\t\t" + telefon + "\t" + cim + "\n"); 
				
			}
			
			System.out.println("A fajlbairas megtortent!");
			
			rs.close();  
			outputStream.close();  
			
		} catch (FileNotFoundException e) {  
			System.err.println("File not found");  
		}  
	}  

	public static void etelMetadata(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("Select * from etel");		
		ResultSet rs = prstmt.executeQuery();		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		System.out.println("Etel tablaban a mezok szama: "+rsmd.getColumnCount());
		
		for (int i=1;i<=rsmd.getColumnCount();i++) {
			System.out.print(rsmd.getColumnName(i)+"("+rsmd.getColumnTypeName(i)+")   ");
		}
		
		System.out.println("\n");
	}
	
	public static void dataDeleteAll(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("DROP TABLE er_kapcsolo");		
		ResultSet rs = prstmt.executeQuery();	
				
		prstmt = conn.prepareStatement("DROP TABLE akciok");		
		rs = prstmt.executeQuery();
		
		prstmt = conn.prepareStatement("DROP TABLE megrendeles");		
		rs = prstmt.executeQuery();	
		
		prstmt = conn.prepareStatement("DROP TABLE etel");		
		rs = prstmt.executeQuery();	
		
		System.out.println("Adatbazis tartalma torolve!");
	}
	
	
	public static void menu(Connection conn) throws SQLException { 
		System.out.println("A program parancsai: ");
		System.out.println("0: kilepes\n1: tablak letrehozasa\n2: tablak feltoltese alap adatokkal\n3: etel felvetele manualisan \n4: manualis lekerdezes\n5: etelek módosítása\n6: akciok modositasa\n7: megrendelesek törlése\n8: etelek törlése\n9: bonyolultabb automatikus lekerdezesek\n10: metadata lekérdezése\n11: adatok kiírása fájlba\n12: teljes adatbazis torlese ");
		boolean ok=true;
		while(ok) {
			
			System.out.println("\nFuttatando parancs szama:\n-------------------------");
			Scanner sc = new Scanner(System.in);
			int executeCommand;
			executeCommand = sc.nextInt();
	
			switch (executeCommand) { 
			case 1:
			 	//táblák létrehozása
				createÉtelTable(conn);
				createAkciokTable(conn);
				createMegrendelesTable(conn);
				createErKapcsoloTable(conn);
				
				break;
			case 2:
				//táblák feltöltése			
				//1. Tábla - Étel feltöltése:
				addEtel(conn,new Etel(1, 1610, 3310, "Pizza", "Margareta"));
				addEtel(conn,new Etel(2, 2050, 4310, "Pizza", "4sajtos"));
				addEtel(conn,new Etel(3, 1550, 2500, "Salata", "Cezar Salata"));
				addEtel(conn,new Etel(4, 2120, 3160, "Husetel", "Gyros tal"));
				addEtel(conn,new Etel(5, 2120, 3160, "Husetel", "Cordon Blue box"));
				addEtel(conn,new Etel(6, 750, 1120, "Desszert", "Turogomboc"));
					
					
				//2. Tábla - Akciok feltöltése:
				addAkciok(conn,new Akciok(1, 40, 1));
				addAkciok(conn,new Akciok(2, 50, 6));
				addAkciok(conn,new Akciok(4, 50, 12));
				addAkciok(conn,new Akciok(5, 20, 2));
				addAkciok(conn,new Akciok(3, 30, 1));
					
					
				//3. Tábla - Megrendeles feltöltése:
				addMegrendeles(conn,new Megrendeles(1, Date.valueOf("2022-03-08"), "Juhasz akos", "Miskolc_xy_utca-1", "062011111"));
				addMegrendeles(conn,new Megrendeles(2, Date.valueOf("2021-12-20"), "Kis Pista", "Miskolc_Ady_Endre_utca-13", "0620122222"));
				addMegrendeles(conn,new Megrendeles(3, Date.valueOf("2021-12-02"), "Nagy Greta", "Miskolc_Kuruc_utca-22", "06203145160"));
				addMegrendeles(conn,new Megrendeles(4, Date.valueOf("2021-01-01"), "Kis Pista", "Miskolc_Ady_Endre_utca-13", "0630667708"));
				addMegrendeles(conn,new Megrendeles(5, Date.valueOf("2022-3-10"), "Kozepes Karoly", "Miskolc_Kuruc_utca-11", "0620198765"));
				addMegrendeles(conn,new Megrendeles(6, Date.valueOf("2022-06-13"), "Kis Pista", "Miskolc_Harsfa_utca-14", "0630611220"));			
				
					
				//4. Tábla - ErKapcsolo feltöltése:
				addErKapcsolo(conn,new ErKapcsolo( 5, 1));
				addErKapcsolo(conn,new ErKapcsolo( 2, 2));
				addErKapcsolo(conn,new ErKapcsolo( 1, 2));
				addErKapcsolo(conn,new ErKapcsolo( 4, 3));
				addErKapcsolo(conn,new ErKapcsolo( 1, 3));
				addErKapcsolo(conn,new ErKapcsolo( 3, 4));
				addErKapcsolo(conn,new ErKapcsolo( 4, 5));
				addErKapcsolo(conn,new ErKapcsolo( 2, 6));
	
				break;
			case 3:
				System.out.println("Uj etel azonositoja: ");
				int etel=sc.nextInt();
				System.out.println("Uj etel kisara: ");
				int kisar=sc.nextInt();
				System.out.println("Uj etel nagyara: ");
				int nagyar=sc.nextInt();
				sc.nextLine();
				System.out.println("Uj etel tipusa: ");
				String tipus=sc.nextLine();
				System.out.println("Uj etel neve: ");
				String nev=sc.nextLine();
				addEtel(conn,new Etel(etel,kisar,nagyar,tipus,nev));
				break;
			case 4:
				sc.nextLine();
				//lekérdezés 1. - Bizonyos típusú ételek kiírása:
				System.out.println("Milyen tipusu eteleket szeretnel lekerdezni?");
				String ltipus=sc.nextLine();
				whereEtel(conn,ltipus);													
				
				break;
			case 5:
				//Adatok módosítása:
				//adatok módosítása 1.
				System.out.println("Melyik azonositoju etelt szeretned modositani?");
				int metel=sc.nextInt();
				System.out.println("Uj kisar: ");
				int mkisar=sc.nextInt();
				System.out.println("Uj nagyar: ");
				int mnagyar=sc.nextInt();
				sc.nextLine();
				System.out.println("Uj tipus: ");
				String mtipus=sc.nextLine();
				System.out.println("Uj nev: ");
				String mnev=sc.nextLine();
				updateEtelAr(conn, metel, mkisar, mnagyar, mtipus, mnev);
				
				break;
			case 6:
				//Adatok módosítása:
				//adatok módosítása 1.
				System.out.println("Melyik azonositoju akciot szeretned modositani?");
				int makcio=sc.nextInt();
				System.out.println("Uj learazas: ");
				int mar=sc.nextInt();
				System.out.println("Uj honap");
				int mhonap=sc.nextInt();
				sc.nextLine();
					
				//adatok módosítása 2.
				updateAkciok(conn, makcio, mar, mhonap); 
				
				break;
			case 7:
				//adatok törlése 1. - Megredeles - Azonosító szerint:
				System.out.println("Melyik azonositoju megrendelest szeretned torolni?");
				int tmegrendeles=sc.nextInt();
				deleteMegrendeles(conn, tmegrendeles);
					
				break;				
			case 8:				
				//adatok törlése 2. - Etel - Azonosító szerint:
				System.out.println("Melyik azonositoju etelt szeretned torolni?");
				int tetel=sc.nextInt();
				deleteEtel(conn, tetel);
				
				break;				
			case 9:
				//lekérdezés 2. - Az év elsõ felében leárazáson lévõ ételek közül, a 2000-nál drágább kisárú ételek kiírása:
				//két tábla közti lekédezés
				whereElsoDraga(conn);					
					
				//lekérdezés 3. - Adott megrendelo által rendelt etelek:
				//két tábla közötti szûrés
				whereMegrendelo(conn, "Kis Pista");
				break;
			case 10:
				//metadata lekérdezése:
				etelMetadata(conn);
				
				break;
			case 11:
				//adatok kiírása fájlba:
				etelToFile(conn);
				
				break;
			case 12:
				//adatb torlese
				dataDeleteAll(conn);
				
				break;
			case 0:
				ok=false;
				break;
			}
		}
		
	}
}

