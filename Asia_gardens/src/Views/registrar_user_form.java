package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.User_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import swing.RoundedBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class registrar_user_form extends JFrame {

	private JPanel contentPane;
	private JTextField email_input;
	private JTextField password_input;
	private JTextField nombre_input;
	private JTextField apellidos_input;
	private JLabel telefono_txt;
	private JTextField telefono_input;



	/**
	 * Create the frame.
	 */
	public registrar_user_form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel insert_user_container = new JPanel();
		insert_user_container.setBackground(new Color(255, 255, 255));
		contentPane.add(insert_user_container);
		insert_user_container.setLayout(new MigLayout("", "[grow]", "[20%,grow][80%,grow]"));
		
		JPanel cabecera = new JPanel();
		cabecera.setBackground(new Color(114,189,217));
		insert_user_container.add(cabecera, "cell 0 0,grow");
		
		JLabel insertar_user_txt = new JLabel("Insertar Nuevo Cliente");
		insertar_user_txt.setFont(new Font("SansSerif", Font.PLAIN, 17));
		cabecera.add(insertar_user_txt);
		
		JPanel input_container = new JPanel();
		input_container.setBackground(new Color(255,255,255));
		insert_user_container.add(input_container, "cell 0 1,grow");
		input_container.setLayout(new MigLayout("", "push[center]push", "[][][][][][][]"));
		
		JLabel email_txt = new JLabel("Email:");
		input_container.add(email_txt, "flowx,cell 0 0");
		
		email_input = new JTextField();
		//email_input.setBackground(new Color(0,247,0));
		input_container.add(email_input, "cell 0 0");
		email_input.setColumns(10);
		
		JLabel password_txt = new JLabel("Contrase\u00F1a:");
		input_container.add(password_txt, "flowx,cell 0 1");
		
		password_input = new JTextField();
		input_container.add(password_input, "cell 0 1");
		password_input.setColumns(10);
		
		JLabel nombre_txt = new JLabel("Nombre:");
		input_container.add(nombre_txt, "flowx,cell 0 2");
		
		nombre_input = new JTextField();
		input_container.add(nombre_input, "cell 0 2");
		nombre_input.setColumns(10);
		
		JLabel apellidos_txt = new JLabel("Apellidos:");
		input_container.add(apellidos_txt, "flowx,cell 0 3");
		
		apellidos_input = new JTextField();
		input_container.add(apellidos_input, "cell 0 3");
		apellidos_input.setColumns(10);
		
		telefono_txt = new JLabel("Telefono:");
		input_container.add(telefono_txt, "flowx,cell 0 4");
		
		telefono_input = new JTextField();
		input_container.add(telefono_input, "cell 0 4");
		telefono_input.setColumns(10);
		
		JLabel error = new JLabel("");
		input_container.add(error, "cell 0 5");
		
		JButton registrar_btn = new JButton("Registrar");
		registrar_btn.setBorder(new RoundedBorder(10)); 
		registrar_btn.setForeground(Color.BLACK);
		registrar_btn.setBackground(new Color(114,189,217));
		input_container.add(registrar_btn, "cell 0 6");
		
		User_controller user_controller=new User_controller(email_input,password_input,nombre_input,apellidos_input,telefono_input,registrar_btn,error);
		registrar_btn.addActionListener(user_controller);
	}

}
