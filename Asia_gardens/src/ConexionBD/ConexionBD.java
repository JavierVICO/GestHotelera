package ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static ConexionBD instance;
	private static Connection conexion ;
	static String db = "asia_gardens";
	
	public ConexionBD() {
		
		try {
			 Class.forName("org.mariadb.jdbc.Driver");
			 
			 Connection conexion1 = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost/"+ db,
						"root","");
					
		if (conexion1 != null){
		 System.out.println("Conexión a base de datos "+ db +" OK");
		 }
		}catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		} finally {
		if (conexion != null)
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public ConexionBD getInstance() {
		
		if(conexion==null) {
			instance=new ConexionBD();
		}
		return instance;
		
	}

}
