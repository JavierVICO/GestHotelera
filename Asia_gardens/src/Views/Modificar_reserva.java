package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Modificar_reserva extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel fecha_entrada_txt;
	private JLabel fecha_salida_txt;

	public Modificar_reserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[10%,grow]push[80%,grow]"));
		
		JPanel cabecera_container_update = new JPanel();
		cabecera_container_update.setBackground(new Color(115,191,219));
		panel.add(cabecera_container_update, "cell 0 0,grow");
		cabecera_container_update.setLayout(new MigLayout("", "push[center]push", "push[grow]push"));
		
		JLabel cabecera_update_container = new JLabel("Modificar  Reserva");
		cabecera_update_container.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cabecera_container_update.add(cabecera_update_container, "cell 0 0");
		
		JPanel update_container = new JPanel();
		panel.add(update_container, "cell 0 1,grow");
		update_container.setLayout(new MigLayout("", "[grow,center]", "[grow][grow][grow][grow]"));
		
		fecha_entrada_txt = new JLabel("Fecha Entrada:");
		update_container.add(fecha_entrada_txt, "flowx,cell 0 0");
		
		textField = new JTextField();
		update_container.add(textField, "cell 0 0,width 20%");
		textField.setColumns(10);
		
		fecha_salida_txt = new JLabel("Fecha Salida:");
		update_container.add(fecha_salida_txt, "flowx,cell 0 1");
		
		textField_1 = new JTextField();
		update_container.add(textField_1, "cell 0 1,width 20%");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		update_container.add(textField_2, "cell 0 2,width 20%");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		update_container.add(textField_3, "cell 0 3,width 20%");
		textField_3.setColumns(10);
		
		
		
		
	}

	
}
