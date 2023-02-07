package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mariadb.jdbc.Statement;

import ConexionBD.Conexion;

public class Reservas {
	private int id;
	private String fecha;
	private String fecha_entrada;
	private String fecha_salida;
	private int numero_adultos;
	private int numero_kids;
	private String user_id;
	private Reserva_habitaciones reserva_habitaciones;
	private ResultSet rs=inizialize_resultset("SELECT * FROM reservas where  fecha_baja is null ");
	
	
	public Reservas(int id,String fecha,String fecha_entrada,String fecha_salida,
			int numero_adultos,int numero_kids,String user_id ) throws SQLException{
				this.id=id;
				this.fecha=fecha;
				this.fecha_entrada=fecha_entrada;
				this.fecha_salida=fecha_salida;
				this.numero_adultos=numero_adultos;
				this.numero_kids=numero_kids;
				this.user_id=user_id;	
				this.user_id = user_id;
				
	}
	public Reservas() {};
	
	public Reservas(String fecha_entrada, String fecha_salida, int numero_adultos, int numero_kids, String user_id) {

		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.numero_adultos = numero_adultos;
		this.numero_kids = numero_kids;
		
	}
	
	public int getID() {
		return id;
	}
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getFecha_entrada() {
		return fecha_entrada;
	}
	
	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	
	public String getFecha_salida() {
		return fecha_salida;
	}
	
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	public int getNumero_adultos() {
		return numero_adultos;
	}
	
	public void setNumero_adultos(int numero_adultos) {
		this.numero_adultos = numero_adultos;
	}
	
	public int getNumero_kids() {
		return numero_kids;
	}
	
	public void setNumero_kids(int numero_kids) {
		this.numero_kids = numero_kids;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
				System.out.print("Fallo en cerrarconexion reservas");
			}
		}	
		return rs;		
	}
	
	//OBTENER LISTADO RESERVAS
	public ArrayList<Reservas>listado_reservas(){
		ArrayList<Reservas>listado_reservas=new ArrayList();
		Reservas reserva=new Reservas();
	
        try {
        	
        	while(rs.next()) {
        		
        		reserva=new Reservas(rs.getInt("id"),rs.getString("fecha"),rs.getString("fecha_entrada"),
        				rs.getString("fecha_salida"),rs.getInt("numero_adultos"),rs.getInt("numero_ninyos"),
        				rs.getString("user_id"));
        		reserva.setReserva_habitaciones(recibir_habitacion_reservas(reserva.getID()));
        		
        		listado_reservas.add(reserva);
        		
        	}
        	
        }catch(Exception e){
        	System.out.println("Conex KO");
        	e.printStackTrace();	
        }
		
		
		return listado_reservas;
	}
	public Reserva_habitaciones recibir_habitacion_reservas(int id_reserva) {
	
			Reserva_habitaciones reserva_habitacion=new Reserva_habitaciones();
			return reserva_habitacion.obtener_reserva_habitaciones(id_reserva);
		
	}
	
	public void registrar_reserva(String fecha_entrada,String fecha_salida,int numero_adultos,int numero_ninyos,String user_id){
		

		try {
			rs.moveToInsertRow();
			rs.updateString("fecha_entrada", fecha_entrada);
			rs.updateString("fecha_salida",  fecha_salida);
			rs.updateInt("numero_adultos", numero_adultos);
			rs.updateInt("numero_ninyos", numero_ninyos);
			rs.updateString("user_id", user_id);
			rs.insertRow();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public void delete_reserva(int id_delete){
		try {
			while (rs.next()) {
		        int id = rs.getInt("ID");
		        if (id == id_delete) {
		          rs.deleteRow();
		        }
		      }
			
		}catch(SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}

	}
	public int obtener_id_reserva(String fecha_actual_reserva) {
		Reservas reserva=new Reservas();
		int id_reserva=0;
		try {
			while (rs.next()) {
				String fecha= rs.getString("fecha");
		        if (fecha.equals(fecha_actual_reserva) ) {
		        	reserva=new Reservas(rs.getInt("id"),
		        			rs.getString("fecha"),
		        			rs.getString("fecha_entrada"),
		        			rs.getString("fecha_salida"),
		        			rs.getInt("numero_adultos"),rs.getInt("numero_ninyos"),rs.getString("user_id"));
		        	
		        	id_reserva=reserva.getID();
		        	
		        }
		      }
			
		}catch(SQLException sqle) {
			System.out.println("Seleccione un campo e eliminar");
		}
		return id_reserva;
		
	}
	public Reserva_habitaciones getReserva_habitaciones() {
		return reserva_habitaciones;
	}
	public void setReserva_habitaciones(Reserva_habitaciones reserva_habitaciones) {
		this.reserva_habitaciones = reserva_habitaciones;
	} 

	
	


}
