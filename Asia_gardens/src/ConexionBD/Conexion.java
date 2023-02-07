package ConexionBD;


import java.sql.DriverManager;
import java.sql.SQLException;
import org.mariadb.jdbc.Connection;
public class Conexion {
	 // Propiedades
    private static Connection conn = null;
    static String db = "asia_gardens";

	 // Constructor
    public Conexion(){
    	
   
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    		conn=(Connection) DriverManager.getConnection("jdbc:mariadb://localhost/"+ db,
    				"root","");
    		
    	
    		if (conn != null){
    			 System.out.println("Conexión a base de datos "+ db +" OK");
    			 }
    	}catch(ClassNotFoundException | SQLException e){
    		e.printStackTrace();
    	}
    		
    	
    	
    	
    };
    public static Connection getConnection(){
    	  
    	 if (conn == null){
    	     new Conexion();
    	 }
    	  
    	 return conn;
    	    } // Fin 
    
    public static void cerrarConexion() throws SQLException{
	    if (conn != null){
	    	conn.close();
	    }
    }
}
  



