package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mariadb.jdbc.Statement;

import ConexionBD.Conexion;

public class User {

	private String email;
	private String password;
	private String Token;
	private Date fecha_validez_token;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String fecha_baja;
	private String fecha_creacion;
	private ResultSet rs=inizialize_resultset("SELECT * FROM users where fecha_baja is null");
	
	public User(String email,String password,String Token,Date fecha_validez_token,String nombre,String apellidos,String telefono,String fecha_baja,String fecha_creacion){
		this.email=email;
		this.password=password;
		this.Token=Token;
		this.fecha_validez_token=fecha_validez_token;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.fecha_baja=fecha_baja;
		this.fecha_creacion=fecha_creacion;
	}
	public User(String email,String nombre,String apellidos,String telefono,String fecha_creacion) {
		this.email=email;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.fecha_creacion=fecha_creacion;
		
	}
	public User(String email,String nombre,String apellidos,String telefono) {
		this.email=email;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		
		
	}
	public User() {};



	public String getEmail() {
		return email;
	}

	public void setEmail(String email ) {
		this.email = email;
		
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public Date getFecha_validez_token() {
		return fecha_validez_token;
	}

	public void setFecha_validez_token(Date fecha_validez_token) {
		this.fecha_validez_token = fecha_validez_token;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	
	public ResultSet inizialize_resultset(String Query){
			
			ResultSet rs = null;
	        Statement statement;
			try {
				
					statement = (Statement) Conexion.getConnection().createStatement(
							 ResultSet.TYPE_SCROLL_INSENSITIVE,
							 ResultSet.CONCUR_UPDATABLE);
							rs = statement.executeQuery(Query);
				
			} catch (SQLException e1) {
				try {
					Conexion.cerrarConexion();
				}catch(SQLException sqle) {	
					System.out.print("Fallo en cerrarconexion reservas"+sqle.getMessage());
				}
			}	
			return rs;		
		}

	public List<User>listado_usuarios(){
		List<User>listado_usuarios=new ArrayList();
		User usuario;
	
	    try {
	    	while(rs.next()) {
	    		listado_usuarios.add(usuario=new User(rs.getString("email"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("telefono")));
	    	}  	
	    }catch(Exception e){
	    	System.out.println("Conex KO"+e.getMessage());
	    	
	    }

		return listado_usuarios;
	}
	
	public void delete_user(String email_delete){
		try {
			while (rs.next()) {
		        String email = rs.getString("email");
		        if (email.equals(email_delete)) {
		          rs.deleteRow();
		        }
		      }
			
		}catch(SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}

	}
	
	public boolean comprobar_user(String email) {
		User user=new User();
		
		try {
			while(rs.next()) {
				String email_user=rs.getString("email");
				if(email_user.equals(email)) {
					return true;
				}
			}
			
		}catch(SQLException sqle){
			System.out.println("Seleccione un campo e eliminar");
			
		}
		return false;
	}
	
	public void registrar_user(String email,String nombre,String apellidos,String telefono,String fecha_creacion) {
		try {
			rs.moveToInsertRow();
			rs.updateString("email", email);
			rs.updateString("nombre",  nombre);
			rs.updateString("apellidos", apellidos);
			rs.updateString("telefono", telefono);
			rs.updateString("created_at", fecha_creacion);
			rs.insertRow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
