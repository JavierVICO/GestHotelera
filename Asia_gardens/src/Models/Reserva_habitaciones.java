package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mariadb.jdbc.Statement;

import ConexionBD.Conexion;

public class Reserva_habitaciones {
	
	private int habitacion_id;
	private int reserva_id;
	private int cantidad;
	private double precio;
	private String created_at;
	private Habitaciones habitacion;
	private ResultSet rs=inizialize_resultset("select * from reservas_habitaciones");
	public Reserva_habitaciones() {};
	public Reserva_habitaciones(int habitacion_id, int reserva_id, int cantidad, int precio,String created_at) {
		super();
		this.habitacion_id = habitacion_id;
		this.reserva_id = reserva_id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.created_at=created_at;
	}
	public Reserva_habitaciones(int habitacion_id, int reserva_id, int cantidad, double precio) {
		this.habitacion_id = habitacion_id;
		this.reserva_id = reserva_id;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	
	public int getHabitacion_id() {
		return habitacion_id;
	}
	public void setHabitacion_id(int habitacion_id) {
		this.habitacion_id = habitacion_id;
	}
	public int getReserva_id() {
		return reserva_id;
	}
	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
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
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
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
				//sqle.printStackTrace();
				System.out.print("Fallo en cerrarconexion reserva_habitacion");
			}
		}	
		return rs;		
	}
	public Reserva_habitaciones obtener_reserva_habitaciones(int id_reserva) {
		
		Reserva_habitaciones reserva_habitaciones;
		try {
			
			while(rs.next()) {
				int id=rs.getInt("reserva_id");
				
				if(id_reserva==id) {
					
					reserva_habitaciones =new Reserva_habitaciones(rs.getInt("habitacion_id"),rs.getInt("reserva_id"),rs.getInt("cantidad"),rs.getDouble("precio"));
					reserva_habitaciones.setHabitacion(new Habitaciones().obtener_habitacion_id(rs.getInt("habitacion_id")));
					
					return reserva_habitaciones;		
				}
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	
	}
	
	
	
		public void registrar_reserva_habitaciones(int habitacion_id,int reserva_id,int cantidad,double precio,String created_at){
				
		
				try {
					rs.moveToInsertRow();
					rs.updateInt("habitacion_id", habitacion_id);
					rs.updateInt("reserva_id",  reserva_id);
					rs.updateInt("cantidad", cantidad);
					rs.updateDouble("precio", precio);
					rs.updateString("created_at", created_at);
					rs.insertRow();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		public Habitaciones getHabitacion() {
			return habitacion;
		}
		public void setHabitacion(Habitaciones habitacion) {
			this.habitacion = habitacion;
		}
	
	
	
	

}
