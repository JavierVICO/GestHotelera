package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.mariadb.jdbc.Statement;

import ConexionBD.Conexion;


public class Habitaciones {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private double precio;
	private int num_max_personas;
	private int numero_camas;
	private Date fecha_baja;
	private Date created_at;
	private Date updapted;
	private ResultSet rs_habitaciones=inizialize_resultset("SELECT * FROM Habitaciones");
	
	public Habitaciones() {};
	public Habitaciones(int id,String nombre,String descripcion,int cantidad,double precio,
			int num_max_personas,int numero_camas){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precio=precio;
		this.num_max_personas=num_max_personas;
		this.numero_camas=numero_camas;
	
	}
	public  Habitaciones(String nombre,String descripcion,int cantidad,double precio,
			int num_max_personas,int numero_camas){
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precio=precio;
		this.num_max_personas=num_max_personas;
		this.numero_camas=numero_camas;
	};
	
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNum_max_personas() {
		return num_max_personas;
	}

	public void setNum_max_personas(int num_max_personas) {
		this.num_max_personas = num_max_personas;
	}

	public int getNumero_camas() {
		return numero_camas;
	}

	public void setNumero_camas(int numero_camas) {
		this.numero_camas = numero_camas;
	}
	public void registrar_habitacion( String nombre,String descripcion,int cantidad,double precio,int num_max_personas,int numero_camas) {
			
		
		try {
			rs_habitaciones.moveToInsertRow();
			rs_habitaciones.updateString("nombre",nombre);
			rs_habitaciones.updateString("descripcion", descripcion);
			rs_habitaciones.updateInt("cantidad",cantidad);
			rs_habitaciones.updateDouble("precio", precio);
			rs_habitaciones.updateInt("numero_maximo_personas", num_max_personas);
			rs_habitaciones.updateInt("numero_camas", numero_camas);
			rs_habitaciones.insertRow();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
	public ResultSet inizialize_resultset(String Query) {
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
				//sqle.printStackTrace();
				System.out.print("Fallo en cerrarconexion habitaciones");
			}
		}
	
		return rs;
		
	}

	//OBTENER LSITADO DE HABITACIONES
	public ArrayList<Habitaciones>listado_habitaciones() {
		Habitaciones habitacion=new Habitaciones();
		ArrayList<Habitaciones>listado_habitaciones=new ArrayList();
		
		try {
			while(rs_habitaciones.next()){
				listado_habitaciones.add(habitacion=new Habitaciones(rs_habitaciones.getInt("id"),
						rs_habitaciones.getString("nombre"),
						rs_habitaciones.getString("descripcion"),rs_habitaciones.getInt("cantidad"),
						rs_habitaciones.getDouble("precio"),rs_habitaciones.getInt("numero_maximo_personas"),
						rs_habitaciones.getInt("numero_camas")));
						
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
			
			
		
		return listado_habitaciones;
		
	}
	public void delete_habitacion(int id_delete){
		try {
			while (rs_habitaciones.next()) {
		        int id = rs_habitaciones.getInt("ID");
		        if (id == id_delete) {
		        	rs_habitaciones.deleteRow();
		        }
		      }
			
		}catch(SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}
	}
	
	
	
	public Habitaciones obtener_habitacion(String nombre_habitacion) {
		Habitaciones habitacion = null;
		
		try {
			while (rs_habitaciones.next()) {
				String nombre = rs_habitaciones.getString("nombre");
		        if (nombre_habitacion.equals(nombre)) {
		        	habitacion = new Habitaciones(rs_habitaciones.getInt("id"),
							rs_habitaciones.getString("nombre"),
							rs_habitaciones.getString("descripcion"),
							rs_habitaciones.getInt("cantidad"),
							rs_habitaciones.getDouble("precio"),
							rs_habitaciones.getInt("numero_maximo_personas"),
							rs_habitaciones.getInt("numero_camas"));
		        	break;
		        }
			}
		} catch (SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}
		
		return habitacion;
	}
	
	public Habitaciones obtener_habitacion_id(int  habitacion_id) {
		Habitaciones habitacion = null;
		
		try {
			while (rs_habitaciones.next()) {
				int  id = rs_habitaciones.getInt("id");
		        if (habitacion_id==id) {
		        	habitacion = new Habitaciones(rs_habitaciones.getInt("id"),
							rs_habitaciones.getString("nombre"),
							rs_habitaciones.getString("descripcion"),
							rs_habitaciones.getInt("cantidad"),
							rs_habitaciones.getDouble("precio"),
							rs_habitaciones.getInt("numero_maximo_personas"),
							rs_habitaciones.getInt("numero_camas"));
		        	break;
		        }
			}
		} catch (SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}
		
		return habitacion;
	}


	

}
