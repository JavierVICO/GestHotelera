package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Habitaciones_controller;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import swing.RoundedBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Insertar_habitacion extends JFrame {

	private JPanel contentPane;
	private JTextField nombre_input;
	private JTextField descripcion_input;
	private JTextField precio_input;

	/**
	 * Create the Frame
	 */
	public Insertar_habitacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[20%,grow][80%,grow]"));
		
		JPanel cabecera_registrar_habitacion = new JPanel();
		cabecera_registrar_habitacion.setBackground(new Color(115,191,219));
		panel.add(cabecera_registrar_habitacion, "cell 0 0,grow");
		cabecera_registrar_habitacion.setLayout(new MigLayout("", "push[center]push", "[grow]"));
		
		JLabel resg_habitacion = new JLabel("Menu Insertar Habitacion");
		resg_habitacion.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cabecera_registrar_habitacion.add(resg_habitacion, "cell 0 0");
		
		JPanel insert_habitacion_container = new JPanel();
		panel.add(insert_habitacion_container, "cell 0 1,grow");
		insert_habitacion_container.setLayout(new MigLayout("", "[grow,center]", "[][][][][][][]"));
		
		JLabel nombretxt = new JLabel("Nombre:");
		insert_habitacion_container.add(nombretxt, "flowx,cell 0 0");
		
		nombre_input = new JTextField();
		insert_habitacion_container.add(nombre_input, "cell 0 0");
		nombre_input.setColumns(10);
		
		JLabel descripciontxt = new JLabel("Descripcion:");
		insert_habitacion_container.add(descripciontxt, "flowx,cell 0 1");
		
		descripcion_input = new JTextField();
		insert_habitacion_container.add(descripcion_input, "cell 0 1");
		descripcion_input.setColumns(10);
		
		JLabel cantidadtxt = new JLabel("Cantidad:");
		insert_habitacion_container.add(cantidadtxt, "cell 0 2");
		Integer[]cantidad= {1,2,3,4,5,6};
		
		JComboBox<Integer> cantidad_combo = new JComboBox<Integer>(cantidad);
		insert_habitacion_container.add(cantidad_combo,"cell 0 2");
	
		JLabel preciotxt = new JLabel("Precio:");
		insert_habitacion_container.add(preciotxt, "flowx,cell 0 3");
		
		precio_input = new JTextField();
		insert_habitacion_container.add(precio_input, "cell 0 3");
		precio_input.setColumns(10);
		
		JLabel num_maximo_personastxt = new JLabel("Numero max.personas");
		insert_habitacion_container.add(num_maximo_personastxt, "flowx,cell 0 4");
		
		Integer[]num_max_personas= {1,2,3,4};
		JComboBox<Integer> num_max_personas_combo = new JComboBox<Integer>(num_max_personas);
		insert_habitacion_container.add(num_max_personas_combo,"cell 0 4");
		JLabel num_maximo_camastxt = new JLabel("Numero max. camas:");
		insert_habitacion_container.add(num_maximo_camastxt, "flowx,cell 0 5");
		
		Integer[]num_max_camas= {1,2,3};
		JComboBox<Integer> num_max_camas_combo = new JComboBox<Integer>(num_max_camas);
		insert_habitacion_container.add(num_max_camas_combo,"cell 0 5");
		
		JButton btn_insertar = new JButton("Registrar");
		btn_insertar.setBorder(new RoundedBorder(10)); 
		btn_insertar.setForeground(Color.BLACK);
		btn_insertar.setBackground(new Color(114,189,217));
		insert_habitacion_container.add(btn_insertar, "flowx,cell 0 6");
		
		JButton btn_limpiar = new JButton("Limpiar");
		btn_limpiar.setBorder(new RoundedBorder(10)); 
		btn_limpiar.setForeground(Color.BLACK);
		insert_habitacion_container.add(btn_limpiar, "cell 0 6");
		
		
		Habitaciones_controller habitacion_controller=new Habitaciones_controller(nombre_input,descripcion_input,
				cantidad_combo,precio_input,num_max_personas_combo,num_max_camas_combo,btn_insertar,btn_limpiar);
		
		btn_insertar.addActionListener(habitacion_controller);
		
	}

}
