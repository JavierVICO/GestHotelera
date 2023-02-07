package Views;

import java.awt.Font;
import java.util.List;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controller.Reservas_controller;
import Models.Habitaciones;
import net.miginfocom.swing.MigLayout;
import swing.DateLabelFormatter;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Registrar_reserva_form extends JFrame {

	private JPanel contentPane;
	

	
	public Registrar_reserva_form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[15%,grow][70%,grow]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(114,189,217));
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "push[center]push","push[center]push"));
		
		JLabel registrar_reserva_txt = new JLabel("REGISTRAR RESERVA");
		registrar_reserva_txt.setFont(new Font("sansserif",1,23));
		panel_1.add(registrar_reserva_txt, "cell 0 0");
		
		JPanel panel_resg_reservas = new JPanel();
		panel.add(panel_resg_reservas, "cell 0 1,grow");
		panel_resg_reservas.setLayout(new MigLayout("", "push[center]push", "[grow][grow][grow][grow][][grow][grow]"));
		
		
		JLabel fecha_entrada = new JLabel("Fecha entrada:");
		panel_resg_reservas.add(fecha_entrada, "flowx,cell 0 0");
		
		UtilDateModel model_entrada = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "DIA");
		p.put("text.month", "MES");
		p.put("text.year", "AÑO");
		
		JDatePanelImpl fecha_entrada_Date_picker = new JDatePanelImpl(model_entrada,p);
		JDatePickerImpl datePicker_entrada = new JDatePickerImpl(fecha_entrada_Date_picker,new DateLabelFormatter());
		panel_resg_reservas.add(datePicker_entrada,"cell 0 0,width 20%");
		UtilDateModel model_salida = new UtilDateModel();
		JDatePanelImpl fecha_salida_Date_picker = new JDatePanelImpl(model_salida,p);
		
		JLabel fecha_salida = new JLabel("Fecha Salida:");
		panel_resg_reservas.add(fecha_salida, "cell 0 0");
		JDatePickerImpl datePicker_salida = new JDatePickerImpl(fecha_salida_Date_picker,new DateLabelFormatter());
		panel_resg_reservas.add(datePicker_salida,"cell 0 0,width 20%");
		//DESPLEGABLE ADULTOS
		JLabel num_adultos = new JLabel("Numero Adultos:");
		panel_resg_reservas.add(num_adultos, "flowx,cell 0 1");
		Integer[]adultos= {1,2};
		
		JComboBox<Integer> adultos_combo = new JComboBox<Integer>(adultos);
		panel_resg_reservas.add(adultos_combo, "cell 0 1,width 5%");
		
		JLabel num_kids = new JLabel("Numero Ni\u00F1os");
		panel_resg_reservas.add(num_kids, "cell 0 1");
		
		Integer [] kids= {0,1,2};
		
		JComboBox<Integer> kids_combo = new JComboBox<Integer>(kids);
		panel_resg_reservas.add(kids_combo, "cell 0 1,width 5%");
		
		JLabel tipo_habitacion = new JLabel("Tipo de Habitaci\u00F3n:");
		panel_resg_reservas.add(tipo_habitacion, "flowx,cell 0 2");
		
		Habitaciones habitacion=new Habitaciones();
		List<Habitaciones>lista_habitaciones_array=habitacion.listado_habitaciones();
		String array_hab[]=new String[lista_habitaciones_array.size()];
		for(int j=0;j<lista_habitaciones_array.size();j++) {
			 array_hab[j]=lista_habitaciones_array.get(j).getNombre();
		}
		
		JComboBox <String> lista_habitaciones = new JComboBox(array_hab);
		panel_resg_reservas.add(lista_habitaciones, "cell 0 2");

		
		JLabel cantidad = new JLabel("Cantidad:");
		panel_resg_reservas.add(cantidad, "cell 0 2");
		
		JLabel cliente_label = new JLabel("Cliente:");
		panel_resg_reservas.add(cliente_label, "flowx,cell 0 3");
		
		JTextField cliente_input = new JTextField();
		panel_resg_reservas.add(cliente_input, "cell 0 3");
		cliente_input.setColumns(10);
		
		JLabel user_exists = new JLabel("");
		user_exists.setForeground(new Color(255,0,0));
		panel_resg_reservas.add(user_exists, "cell 0 4");
		
		JButton resg_reserva = new JButton("Registrar reserva");
		panel_resg_reservas.add(resg_reserva, "cell 0 5");
		Integer [] cantidad_array= {1,2,3};
		
		JComboBox cantidad_combo = new JComboBox(cantidad_array);
		panel_resg_reservas.add(cantidad_combo, "cell 0 2");
		
		Reservas_controller reservas_controller=new Reservas_controller(lista_habitaciones,datePicker_entrada,datePicker_salida,
				adultos_combo,kids_combo,cantidad_combo,cliente_input, resg_reserva,user_exists);
		lista_habitaciones.addActionListener(lista_habitaciones);
		
		resg_reserva.addActionListener(reservas_controller);
		
	}

}
