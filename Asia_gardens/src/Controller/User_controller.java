package Controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.hamcrest.Matcher;

import Models.Reservas;
import Models.User;
/**
 * Controlador de ventana User
 * @author javier
 *
 */
public class User_controller implements ActionListener {
	private JTable user_table;
	private ModeloDeTabla<User>modeloDeTabla;
	ProveedorDeDatosDePaginacion<User>proveedordatosdepaginacion=crearProveedorDeDatosUser();
	private  JTextField email_input,password_input,nombre_input,apellidos_input,telefono_input;
	private JButton registrar_btn;
	private JLabel error;
	private JLabel pagina_users;
	private int filas_por_pagina=10;
	private int paginaActual;
    private int numeroPaginas = 0;
	private int numero_filas_total=0;
	
	
	public User_controller(JTextField email_input,JTextField password_input,JTextField nombre_input,JTextField apellidos_input,
			JTextField telefono_input,JButton registrar_btn,JLabel error ) {
		this.email_input=email_input;
		this.password_input=password_input;
		this.nombre_input=nombre_input;
		this.apellidos_input=apellidos_input;
		this.telefono_input=telefono_input;
		this.registrar_btn=registrar_btn;
		this.error=error;
	};
	public User_controller(JTable user_table) {
		this.user_table=user_table;
		this.numero_filas_total=proveedordatosdepaginacion.getTotalRowCount();
		this.paginaActual=1;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		if(src==registrar_btn) {
			System.out.println("pulsado");
			insertar_user();
		}
			
	}
	
	public void insertar_user() {
	    String email = email_input.getText().toString();
	    String password = password_input.getText().toString();
	    String nombre = nombre_input.getText().toString();
	    String apellidos = apellidos_input.getText().toString();
	    String telefono = telefono_input.getText().toString();

	    boolean emailValid = validar_email(email);
	    boolean passwordValid = !password.isEmpty();
	    boolean nombreValid = !nombre.isEmpty();
	    boolean apellidosValid = !apellidos.isEmpty();
	    boolean telefonoValid = validate_number(telefono);

	    if (emailValid) {
	        email_input.setBackground(new Color(0, 247, 0));
	    } else {
	        email_input.setBackground(new Color(239, 65, 70));
	    }
	    if (passwordValid) {
	        password_input.setBackground(new Color(0, 247, 0));
	    } else {
	        password_input.setBackground(new Color(239, 65, 70));
	    }
	    if (nombreValid) {
	        nombre_input.setBackground(new Color(0, 247, 0));
	    } else {
	        nombre_input.setBackground(new Color(239, 65, 70));
	    }
	    if (apellidosValid) {
	        apellidos_input.setBackground(new Color(0, 247, 0));
	    } else {
	        apellidos_input.setBackground(new Color(239, 65, 70));
	    }
	    if (telefonoValid) {
	        telefono_input.setBackground(new Color(0, 247, 0));
	    } else {
	        telefono_input.setBackground(new Color(239, 65, 70));
	    }

	    User user = new User();
	    if (!user.comprobar_user(email) && emailValid && passwordValid && nombreValid && apellidosValid && telefonoValid) {
	        String fecha_alta = getCurrentTimestamp();
	        
	        System.out.println(fecha_alta);
	        //user.registrar_user(email, nombre, apellidos, telefono, fecha_alta);
	        error.setText("Insertado correctamente");
	        error.setBackground(new Color(0, 247, 0));
	    } else {
	        error.setText("Datos erroneos");
	        error.setBackground(new Color(239, 65, 70));
	    }
	}

	private String getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance();
 	   Date currentDate = calendar.getTime();
 	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	   String fecha_hora_actual = format.format(currentDate);
 			   return fecha_hora_actual;
	}	

	
	/**
	 * Metodo cargar tabla de usuarios
	 * @param user_table
	 */
	public void cargar_tabla_user(JTable user_table,JLabel pagina_users){

		try {
			

			user_table.setModel(CrearModeloDetabla());
			List<User>lista=paginar(user_table);
			modeloDeTabla=(ModeloDeTabla<User>) user_table.getModel();
			modeloDeTabla.setListRows(lista);
			
			pagina_users.setText("Pagina: "+paginaActual+"/"+
			(int)( Math.ceil(proveedordatosdepaginacion.getTotalRowCount()/10+1))+"  Total registros: "+proveedordatosdepaginacion.getTotalRowCount());
		}catch(IndexOutOfBoundsException iobe) {
			System.out.println(iobe.getMessage());
		}
		
	}
	
	private List<User> paginar(JTable user_table) {
		
		int inicio=(paginaActual-1)*filas_por_pagina; //1-1*10=0
		int fin=inicio+filas_por_pagina;
		System.out.println("pagina actual"+paginaActual);
		if(inicio<0) {
			inicio=1;
		}if(fin>proveedordatosdepaginacion.getTotalRowCount() ){
			fin=proveedordatosdepaginacion.getTotalRowCount();
		}
			
			List<User>lista=proveedordatosdepaginacion.getRows(inicio, fin);
			((AbstractTableModel) user_table.getModel()).fireTableDataChanged();
			
		
		return lista;
		
		
	}
	
	
	
	/**
	 * Crear modelo de la Tabla para User
	 */
	private TableModel CrearModeloDetabla() {
		return new ModeloDeTabla<User>(){

			@Override
			public int getColumnCount() {
				
				return 4;
			}

			@Override
			public Object getValueAt(User user, int columnas) {
				switch(columnas){
				case 0:
					return user.getEmail();
				case 1:
					return user.getNombre();
				case 2:
					return user.getApellidos();
				case 3:
					return user.getTelefono();
				}
				return null;
			}

			@Override
			public String getColumnName(int columnas) {
				switch(columnas){
				case 0:
					return "Email";
				case 1:
					return "Nombre";
				case 2:
					return "Apellidos";
				case 3:
					return "Telefono";
				}
				return null;
			}
			
		};
	}
	
	/**
	 * Metodo obtener datos del modelo.
	 * @return
	 */

	private ProveedorDeDatosDePaginacion<User> crearProveedorDeDatosUser(){
		User user=new User();
		List<User>lista_user=user.listado_usuarios();
		
		 return new ProveedorDeDatosDePaginacion<User>() {
	            @Override
	            public int getTotalRowCount() {
	                return lista_user.size();
	            }

	            @Override
	            public List<User> getRows(int startIndex, int endIndex) {
	                return lista_user.subList(startIndex, endIndex);
	            }
	        };
	}

	public void avanzar_pagina(JTable reservas_table) {
		paginaActual++;
		System.out.println(paginaActual);
	
	
	}
	
	public void retroceder_pagina(JTable reservas_table) {
		paginaActual--;
	}
	
	  public static  boolean validar_email(String email){
	        Pattern pattern = Pattern
	                .compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
	        java.util.regex.Matcher matcher=pattern.matcher(email);
	        return matcher.matches();

	}
	  
	 

		  


	 public static boolean validate_number(String phoneNumber) {
		 
		    Pattern pattern = Pattern.compile("^\\d{9}$");
		    java.util.regex.Matcher matcher = pattern.matcher(phoneNumber);
		     return matcher.matches();
		 }
	 
	
		  
	
	

}
