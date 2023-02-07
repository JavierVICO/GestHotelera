package Controller;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import Models.Habitaciones;
import Models.Reservas;
import Models.User;
import Views.Insertar_habitacion;
import Views.Registrar_reserva_form;
import Views.registrar_user_form;

import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard_controller implements ActionListener,MouseListener  {
	private JButton home,habitaciones,reservas,usuarios,ajustes,registrar_reservas,btnBuscar,eliminar_Reservas,
	btn_buscar_habitaciones,btn_insertar_habitacion,btn_elimianr_habitacion,anterior,siguiente,anterior_user,siguiente_user,btn_insert_user;
	private JTabbedPane jtabbedpane;
	private JTextField search,txt_filter_habitaciones;
	private JTable habitaciones_table,reservas_table,user_table;
	private JLabel pagina_reservas,pagina_users;
	private Reservas_controller reservas_controller=new Reservas_controller(reservas_table);
	//private Reservas_controller reservas_paginar;
	private Habitaciones_controller habitaciones_controller=new Habitaciones_controller(habitaciones_table);
	User_controller user_controller=new User_controller(user_table);
	private String id="";
	private String email="";

	
	public Dashboard_controller(JButton home,JButton habitaciones,JButton reservas,JButton usuarios,JButton ajustes,JTabbedPane jtabbedpane,JTable habitaciones_table,
			JTable reservas_table,JButton registrar_reservas,JTextField search,JButton btnBuscar,JButton eliminar_Reservas,JButton btn_buscar_habitaciones,
			JTextField txt_filter_habitaciones,JButton btn_insertar_habitacion,JButton btn_elimianr_habitacion,JTable user_table,
			JButton anterior,JButton siguiente,JLabel pagina,JButton anterior_user,JButton siguiente_user,JLabel pagina_users,JButton btn_insert_user){
		this.home=home;
		this.habitaciones=habitaciones;
		this.reservas=reservas;
		this.usuarios=usuarios;
		this.ajustes=ajustes;
		this.jtabbedpane=jtabbedpane;
		this.habitaciones_table=habitaciones_table;
		this.reservas_table=reservas_table;
		this.registrar_reservas=registrar_reservas;
		this.search=search;
		this.btnBuscar=btnBuscar;
		this.eliminar_Reservas=eliminar_Reservas;
		this.btn_buscar_habitaciones=btn_buscar_habitaciones;
		this.txt_filter_habitaciones=txt_filter_habitaciones;
		this.btn_insertar_habitacion=btn_insertar_habitacion;
		this.btn_elimianr_habitacion=btn_elimianr_habitacion;
		this.user_table=user_table;
		this.anterior=anterior;
		this.siguiente=siguiente;
		this.pagina_reservas=pagina;
		this.anterior_user=anterior_user;
		this.siguiente_user=siguiente_user;
		this.pagina_users=pagina_users;
		this.btn_insert_user=btn_insert_user;
	}
	
	
	/**
	 * Segun Boton accede a determinado metodo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Object src=e.getSource();
		if(src==home){
			home_pulsado(jtabbedpane);
		}if(src==habitaciones){
			habitaciones_pulsado(jtabbedpane);
			Habitaciones_controller habitacion_controller=new Habitaciones_controller(habitaciones_table);
			habitacion_controller.cargar_tabla_habitaciones(habitaciones_table);
		}if(src==reservas){
			reservas_pulsado(jtabbedpane);
			Reservas_controller mi_reservas_controller=new Reservas_controller(reservas_table);
			mi_reservas_controller.cargar_tabla_reservas(reservas_table,pagina_reservas);
		}if(src==usuarios ){
			usuarios_pulsado(jtabbedpane);	
			user_controller.cargar_tabla_user(user_table,pagina_users);
			
		}if(src==ajustes) {
			ajustes_pulsado(jtabbedpane);
		}if(src==registrar_reservas) {
			invoke_registrar_reservas();
		}if(src==btnBuscar) {
			String txt_search_reservas=search.getText().toString();
			
			reservas_controller.search(txt_search_reservas,reservas_table);
			Reservas_controller mi_reservas_controller=new Reservas_controller(reservas_table);
			mi_reservas_controller.cargar_tabla_reservas(reservas_table,pagina_reservas);
		}if(src==eliminar_Reservas){
			Reservas reserva=new Reservas();
			try {
				reserva.delete_reserva(Integer.parseInt(id));
				reservas_controller.cargar_tabla_reservas(reservas_table,pagina_reservas);
				
			}catch(NumberFormatException n) {
				System.out.println("Se encuentra vacio");
			}
			
		}if(src==btn_buscar_habitaciones){
			String txt_search_habitaciones=txt_filter_habitaciones.getText().toString();
			System.out.println(txt_search_habitaciones);
			habitaciones_controller.search(txt_search_habitaciones, habitaciones_table);	
		}if(src==btn_insertar_habitacion){
			invoke_insert_habitaciones();
		}if(src==btn_elimianr_habitacion) {
			try {
				delete_habitacion(Integer.parseInt(id));
			}catch(NumberFormatException n) {
				System.out.println("Se encuentra vacio"+n.getMessage());
			}	
		}if(src==siguiente) {
			try {
				
				reservas_controller.avanzar_pagina(reservas_table);
				reservas_controller.cargar_tabla_reservas(reservas_table, pagina_reservas);
				
			}catch(IllegalArgumentException ie) {
				
				System.out.println("Superado limite paginas"+ie.getMessage());
			}
			
		}if(src==anterior){
			try {
				
				reservas_controller.retroceder_pagina();
				reservas_controller.cargar_tabla_reservas(reservas_table,pagina_reservas);
			}catch(IllegalArgumentException ie) {
				
				System.out.println("Superado limite paginas"+ie.getMessage());
				
				}catch(IndexOutOfBoundsException iobe) {
				System.out.println("Superado limite paginas"+iobe.getMessage());
				}
			
		}if(src==anterior_user){
			
			try {
				user_controller.retroceder_pagina(user_table);
				user_controller.cargar_tabla_user(user_table,pagina_users);
			}catch(IllegalArgumentException ie){
				System.out.println("Superado limite paginas"+ie.getMessage());
			}catch(IndexOutOfBoundsException iobe) {
			System.out.println("Superado limite paginas"+iobe.getMessage());
			}
			
		}if(src==siguiente_user) {
			System.out.println("Pulsado siguiente");
			user_controller.avanzar_pagina(reservas_table);
			user_controller.cargar_tabla_user(user_table,pagina_users);
		}if(src==btn_insert_user) {
			invoke_insert_users();
		}
		}

	public void home_pulsado(JTabbedPane home_tabbed){
		jtabbedpane.setSelectedIndex(0);
		
	}
	public void habitaciones_pulsado(JTabbedPane habitaciones) {
		jtabbedpane.setSelectedIndex(1);
	}
	public void reservas_pulsado(JTabbedPane jtabbedpane){
		jtabbedpane.setSelectedIndex(2);
	}
	public void usuarios_pulsado(JTabbedPane jtabbedpane){
		jtabbedpane.setSelectedIndex(3);
	}
	public void ajustes_pulsado(JTabbedPane jtabbedpane){
		jtabbedpane.setSelectedIndex(4);
	}
	/**
	 * Invocar JFrame registrar reservas
	 */
	public void invoke_registrar_reservas() {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	
	                	Registrar_reserva_form registrar_reserva=new Registrar_reserva_form();
	                	registrar_reserva.setSize(600,400);
	                	registrar_reserva.setVisible(true);
	                	registrar_reserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                
	                     
	                }catch (Exception e){
	                    e.printStackTrace();
	                }


	            }
	        });
	}
	//TODO TERMINAR
	public void invoke_update_reservas(){
		
	}
	/**
	 * Invocar JFrame insertar habitaciones
	 */
	public void invoke_insert_habitaciones(){
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	
	                	Insertar_habitacion insertar_habitacion=new Insertar_habitacion();
	                	insertar_habitacion.setSize(400,300);
	                	insertar_habitacion.setVisible(true);
	                	insertar_habitacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                     
	                }catch (Exception e){
	                    e.printStackTrace();
	                }


	            }
	        });
	}
	public void invoke_insert_users(){
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	
	                	registrar_user_form registrar_user_form=new registrar_user_form();
	                	registrar_user_form.setSize(400,300);
	                	registrar_user_form.setVisible(true);
	                	registrar_user_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                     
	                }catch (Exception e){
	                    e.printStackTrace();
	                }


	            }
	        });
	}

	/**
	 * OBTENER ID RESERVA PARA DELETE Y UPDATE
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src=e.getSource();
		if(src==reservas_table && reservas_table.getSelectedRow() != -1 ) {
			id = (String) reservas_table.getModel().getValueAt(reservas_table.getSelectedRow(), 0);
			System.out.println("Seleccionada reserva con id: "+id);
			
		}if(src==habitaciones_table && habitaciones_table.getSelectedRow()!=-1) {
			
			id=(String)habitaciones_table.getModel().getValueAt(habitaciones_table.getSelectedRow(),0);
        	System.out.println("Seleccionada habitacion con id: "+id);
		}if(src==user_table && user_table.getSelectedRow()!=-1) {
			
			email=(String)user_table.getModel().getValueAt(user_table.getSelectedRow(),0);
			System.out.println("Seleccionado User con id: "+email);
			
		}
		
       
	}
	/**
	 * Metodo eliminar Reservas
	 * @param id_delete
	 */
		public void delete_reserva(int id_delete){
			Reservas reserva=new Reservas();
			reserva.delete_reserva(id_delete);
			
			
		}
		/**
		 * Metodo eliminar Habitacion
		 * @param id_delete
		 */
		public void delete_habitacion(int id_delete) {
			Habitaciones habitacion=new Habitaciones();
			habitacion.delete_habitacion(id_delete);
		}
		public void delete_user(String email) {
			User user=new User();
			user.delete_user(email);
		}
		


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	



}
