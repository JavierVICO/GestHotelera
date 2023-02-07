package Views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import net.miginfocom.swing.MigLayout;
import swing.RoundedBorder;

import javax.swing.JTabbedPane;

import Controller.Dashboard_controller;
import Controller.Habitaciones_controller;
import Controller.User_controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.GradientPaint;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable reservas_table;
	private JTextField search;
	private JTextField txt_filter_habitaciones;
	private JTable user_table;
	private JButton siguiente;
	private JTextField buscar_txt;
	

	public Dashboard() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[20%][grow]", "[grow]"));
		
		
		JPanel menu = new JPanel();
		panel.add(menu, "cell 0 0,grow");
		menu.setBackground(new Color(115,191,219));
		menu.setLayout(new MigLayout("", "push[center]push", "push[]15[]25[]25[]25[]25[]25[]push"));
		
		ImageIcon imagen = new ImageIcon(Dashboard.class.getResource("/imagenes/logo_colorizado.png"));
		JLabel logo = new JLabel(imagen);
		menu.add(logo, "cell 0 0");
		
		
		JButton home = new JButton("Home");
		home.setIcon(new ImageIcon(getClass().getResource("/Icon/ic_home.png")));
		home.setBackground(new Color(255,255,255));
		menu.add(home, "grow,cell 0 1");
		
		JButton habitaciones = new JButton("Habitaciones");
		habitaciones.setIcon(new ImageIcon(getClass().getResource("/Icon/habitacion.png")));
		habitaciones.setBackground(new Color(255,255,255));
		menu.add(habitaciones, "grow,cell 0 2");
		
		JButton reservas = new JButton("Reservas");
		reservas.setIcon(new ImageIcon(getClass().getResource("/Icon/calendario.png")));
		reservas.setBackground(new Color(255,255,255));
		menu.add(reservas, "grow,cell 0 3");
		
		
		JButton usuarios = new JButton("Usuarios");
		usuarios.setBackground(new Color(255,255,255));
		usuarios.setIcon(new ImageIcon(getClass().getResource("/Icon/user.png")));
		menu.add(usuarios, "grow,cell 0 4");
		
		
		JButton ajustes = new JButton("Ajustes");
		ajustes.setIcon(new ImageIcon(getClass().getResource("/Icon/ajustes.png")));
		ajustes.setBackground(new Color(255,255,255));
		menu.add(ajustes, "grow,cell 0 5");
	
		JPanel container_visual = new JPanel();
		panel.add(container_visual, "cell 1 0,grow");
		container_visual.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		container_visual.add(tabbedPane, "cell 0 0,grow");
		
		JPanel home_tabbed = new JPanel();
		tabbedPane.addTab("Home", null, home_tabbed, null);
		home_tabbed.setLayout(new MigLayout("", "push[grow]push", "push[center]push[70%][]"));
		
		JPanel panel_1 = new JPanel();
		home_tabbed.add(panel_1, "cell 0 0,grow");
		
		JLabel bienvenida = new JLabel("Bienvenido:");
		bienvenida.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_1.add(bienvenida);
		
		JPanel panel_2 = new JPanel();
		home_tabbed.add(panel_2, "cell 0 1,grow");
		
		JPanel habitaciones_tabbed = new JPanel();
		tabbedPane.addTab("Habitaciones", null, habitaciones_tabbed, null);
		habitaciones_tabbed.setLayout(new MigLayout("", "[grow]", "[20%,grow][10%,grow][60%,grow][10%,grow]"));
		
		JPanel insertar_habitaciones = new JPanel();
		habitaciones_tabbed.add(insertar_habitaciones, "cell 0 0,grow");
		insertar_habitaciones.setLayout(new MigLayout("", "[grow]", "[20%,grow]push[80%,grow]"));
		
		JPanel habitaciones_header = new JPanel();
		insertar_habitaciones.add(habitaciones_header, "cell 0 0,grow");
		habitaciones_header.setLayout(new MigLayout("", "push[center]push", "[]"));
		
		JLabel habitaciones_menu_txt = new JLabel("Menu Habitaciones:");
		habitaciones_menu_txt.setFont(new Font("SansSerif", Font.PLAIN, 18));
		habitaciones_header.add(habitaciones_menu_txt, "cell 0 0");
		
		JPanel boton_habitaciones_container = new JPanel();
		insertar_habitaciones.add(boton_habitaciones_container, "cell 0 1,grow");
		boton_habitaciones_container.setLayout(new MigLayout("", "push[][][]push", "[grow]"));
		
		JButton btn_insertar_habitacion = new JButton("Insertar Habitacion");
		btn_insertar_habitacion.setBorder(new RoundedBorder(10)); 
		btn_insertar_habitacion.setForeground(Color.BLACK);
		btn_insertar_habitacion.setBackground(new Color(115, 225, 156));
		boton_habitaciones_container.add(btn_insertar_habitacion, "cell 0 0");
		
		JButton btn_elimianr_habitacion = new JButton("Eliminar Habitacion");
		btn_elimianr_habitacion.setBorder(new RoundedBorder(10)); 
		btn_elimianr_habitacion.setForeground(Color.BLACK);
		btn_elimianr_habitacion.setBackground(new Color(255, 153, 131));
		boton_habitaciones_container.add(btn_elimianr_habitacion, "cell 1 0");
		
		JButton modificar_habitacion = new JButton("Modificar Habitacion");
		modificar_habitacion.setBorder(new RoundedBorder(10)); 
		modificar_habitacion.setForeground(Color.BLACK);
		modificar_habitacion.setBackground(new Color(255, 219, 131));
		boton_habitaciones_container.add(modificar_habitacion, "cell 2 0");
		
		JLabel buscar_habitaciones_label = new JLabel("Buscar:");
		habitaciones_tabbed.add(buscar_habitaciones_label, "flowx,cell 0 1");
		
		txt_filter_habitaciones = new JTextField();
		habitaciones_tabbed.add(txt_filter_habitaciones, "cell 0 1,growx");
		txt_filter_habitaciones.setColumns(10);
		
		JButton btn_buscar_habitaciones = new JButton("Buscar");
		habitaciones_tabbed.add(btn_buscar_habitaciones, "cell 0 1");
		
		JScrollPane scroll_habitaciones = new JScrollPane();
		habitaciones_tabbed.add(scroll_habitaciones, "cell 0 2,grow");
		
		JTable table_habitaciones = new JTable();
		scroll_habitaciones.setViewportView(table_habitaciones);
		

		JPanel panel_5 = new JPanel();
		habitaciones_tabbed.add(panel_5, "cell 0 3,grow");
		/*
		JButton anterior_habitaciones = new JButton("<");
		panel_5.add(anterior_habitaciones);
		
		JButton siguiente_habitaciones = new JButton(">");
		panel_5.add(siguiente_habitaciones);
		*/
		
		JPanel reservas_tabbed = new JPanel();
		tabbedPane.addTab("Reservas", null, reservas_tabbed, null);
		reservas_tabbed.setLayout(new MigLayout("", "[grow]", "[30%,grow][10%,grow][60%,grow][10%,grow]"));
		
		JPanel cabecera_reservas = new JPanel();
		reservas_tabbed.add(cabecera_reservas, "cell 0 0,grow");
		cabecera_reservas.setLayout(new MigLayout("", "[grow]", "[20%,grow][80%,grow]"));
		
		JPanel reservas_header = new JPanel();
		cabecera_reservas.add(reservas_header, "cell 0 0,grow");
		reservas_header.setLayout(new MigLayout("", "push[center]push", "[grow]"));
		
		JLabel opciones_reservas_txt = new JLabel("Menu reservas");
		opciones_reservas_txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		reservas_header.add(opciones_reservas_txt, "cell 0 0");
		
		JPanel CUD_reservas = new JPanel();
		cabecera_reservas.add(CUD_reservas, "cell 0 1,grow");
		CUD_reservas.setLayout(new MigLayout("", "push[][][]push", "[grow]"));
		
		JButton registrar_reservas = new JButton("Registrar reserva");
		registrar_reservas.setBorder(new RoundedBorder(10)); 
		registrar_reservas.setForeground(Color.BLACK);
		registrar_reservas.setBackground(new Color(115, 225, 156));
		CUD_reservas.add(registrar_reservas, "cell 3 0");
		
		JButton eliminar_Reservas = new JButton("Eliminar Reserva");
		eliminar_Reservas.setBorder(new RoundedBorder(10)); 
		eliminar_Reservas.setForeground(Color.BLACK);
		eliminar_Reservas.setBackground(new Color(255, 153, 131));
		CUD_reservas.add(eliminar_Reservas, "cell 5 0");
		
		
	
		JButton modificar_Reservas = new JButton("Modificar Reserva");
		modificar_Reservas.setBorder(new RoundedBorder(10)); 
		modificar_Reservas.setForeground(Color.BLACK);
		modificar_Reservas.setBackground(new Color(255, 219, 131));
		CUD_reservas.add(modificar_Reservas, "cell 7 0");
		
		JPanel usuarios_tabbed = new JPanel();
		tabbedPane.addTab("Usuarios", null, usuarios_tabbed, null);
		usuarios_tabbed.setLayout(new MigLayout("", "[grow]", "[20%,grow][10%,grow][60%,grow]push[10%,grow]push"));
		
		JPanel cabecera_user = new JPanel();
		usuarios_tabbed.add(cabecera_user, "cell 0 0,grow");
		cabecera_user.setLayout(new MigLayout("", "[grow]", "[20%,grow][80%,grow]"));
		
		JPanel panel_3 = new JPanel();
		cabecera_user.add(panel_3, "cell 0 0,grow");
		
		JLabel user_header = new JLabel("Menu usuarios:");
		user_header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel_3.add(user_header);
		
		JPanel panel_4 = new JPanel();
		cabecera_user.add(panel_4, "cell 0 1,grow");
		
		JButton btn_insert_user = new JButton("Insertar Usuario");
		btn_insert_user.setBorder(new RoundedBorder(10)); 
		btn_insert_user.setForeground(Color.BLACK);
		btn_insert_user.setBackground(new Color(115, 225, 156));
		panel_4.add(btn_insert_user);
		
		JButton btn_eliminar_user = new JButton("Eliminar usuario");
		btn_eliminar_user.setBorder(new RoundedBorder(10)); 
		btn_eliminar_user.setForeground(Color.BLACK);
		btn_eliminar_user.setBackground(new Color(255, 153, 131));
		panel_4.add(btn_eliminar_user);
		
		JButton btn_modificar_usuario = new JButton("Modificar usuario");
		btn_modificar_usuario.setBorder(new RoundedBorder(10)); 
		btn_modificar_usuario.setForeground(Color.BLACK);
		btn_modificar_usuario.setBackground(new Color(255, 219, 131));
		panel_4.add(btn_modificar_usuario);
		
		JLabel buscar = new JLabel("Buscar:");
		usuarios_tabbed.add(buscar, "flowx,cell 0 1");
		
		JScrollPane scrollPane = new JScrollPane();
		usuarios_tabbed.add(scrollPane, "cell 0 2,grow");
		
		user_table = new JTable();
		scrollPane.setViewportView(user_table);
		
		JPanel panel_paginador = new JPanel();
		usuarios_tabbed.add(panel_paginador, "cell 0 3,grow");
		panel_paginador.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		
		JPanel ajustes_tabbed = new JPanel();
		tabbedPane.addTab("Ajustes", null, ajustes_tabbed, null);
		
		JLabel lblNewLabel2 = new JLabel("Buscar:");
		reservas_tabbed.add(lblNewLabel2, "flowx,cell 0 1");
		
		search = new JTextField();
		reservas_tabbed.add(search, "cell 0 1,growx");
		search.setColumns(10);
		JButton btnBuscar = new JButton("Buscar");
		reservas_tabbed.add(btnBuscar, "cell 0 1");
		
		JScrollPane scroll_reservas = new JScrollPane();
		reservas_tabbed.add(scroll_reservas, "cell 0 2,grow");
		
		reservas_table = new JTable();
		scroll_reservas.setViewportView(reservas_table);

		JPanel paginador = new JPanel();
		reservas_tabbed.add(paginador, "cell 0 3,grow");
		
		JButton anterior_reservas = new JButton("<");
		paginador.add(anterior_reservas);
		
		JButton siguiente_reservas = new JButton(">");
		siguiente_reservas.setToolTipText("");
		paginador.add(siguiente_reservas);
		
		JLabel pagina_reservas = new JLabel("pagina");
		paginador.add(pagina_reservas);
		
	
		
		JButton anterior_user = new JButton("<");
		panel_paginador.add(anterior_user);
		
		JButton siguiente_user = new JButton(">");
		panel_paginador.add(siguiente_user);
		
		JLabel pagina_users = new JLabel("pagina");
		panel_paginador.add(pagina_users);
		
		buscar_txt = new JTextField();
		usuarios_tabbed.add(buscar_txt, "cell 0 1,width 80%");
		buscar_txt.setColumns(10);
		
		JButton buscar_btn = new JButton("Buscar");
		usuarios_tabbed.add(buscar_btn, "cell 0 1");
		
		//INSTANCIAS DE CONTROLLER Y ASOCIACIONES
		Dashboard_controller dashboard_controller=new Dashboard_controller(home,habitaciones,reservas,usuarios,ajustes,tabbedPane,table_habitaciones,
				reservas_table,registrar_reservas,search,btnBuscar,eliminar_Reservas,btn_buscar_habitaciones,txt_filter_habitaciones,btn_insertar_habitacion,
				btn_elimianr_habitacion,user_table,anterior_reservas,siguiente_reservas,pagina_reservas,anterior_user,siguiente_user,pagina_users,btn_insert_user);
	
		//INSTANCIAS DE ACTIONLISTENER
		home.addActionListener(dashboard_controller);
		habitaciones.addActionListener(dashboard_controller);
		reservas.addActionListener(dashboard_controller);
		usuarios.addActionListener(dashboard_controller);
		ajustes.addActionListener(dashboard_controller);
		registrar_reservas.addActionListener(dashboard_controller);
		btnBuscar.addActionListener(dashboard_controller);
		btn_buscar_habitaciones.addActionListener(dashboard_controller);
		eliminar_Reservas.addActionListener(dashboard_controller);
		btn_insertar_habitacion.addActionListener(dashboard_controller);
		btn_elimianr_habitacion.addActionListener(dashboard_controller);
		anterior_reservas.addActionListener(dashboard_controller);
		siguiente_reservas.addActionListener(dashboard_controller);
		anterior_user.addActionListener(dashboard_controller);
		siguiente_user.addActionListener(dashboard_controller);
		btn_insert_user.addActionListener(dashboard_controller);
		//INSTANCIA MOUSE LISTENER
		reservas_table.addMouseListener(dashboard_controller);
		table_habitaciones.addMouseListener(dashboard_controller);
		DefaultTableModel model= new DefaultTableModel();
		//Habitaciones_controller habitaciones_controller=new Habitaciones_controller(table_habitaciones);
		
		
	}


		protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
	        g2.setPaint(g);
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
	        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
	        super.paintComponents(grphcs);
	    }

}
