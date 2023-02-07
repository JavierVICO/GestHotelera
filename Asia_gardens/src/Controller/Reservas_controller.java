package Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdatepicker.impl.JDatePickerImpl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Models.Habitaciones;
import Models.Reserva_habitaciones;
import Models.Reservas;
import Models.User;

public class Reservas_controller implements ActionListener {
	JTable reservas_table;
	JLabel pagina;
	DefaultTableModel model;
	JComboBox<String> lista_habitaciones;
	JComboBox adultos_combo,kids_combo,cantidad_combo;
	JDatePickerImpl datePicker_entrada,datePicker_salida;
	JTextField cliente_input ;
	JLabel num_adultos,tipo_habitacion,user_exists;
	JButton resg_reserva;
	ProveedorDeDatosDePaginacion<Reservas>proveedordatosdepaginacion=crearProveedorDeDatos();
	private int filas_por_pagina=10;
	private int paginaActual;
    private int numeroPaginas = 0;
	private int numero_filas_total=0;

	
	
	public Reservas_controller(JTable reservas_table){
		this.reservas_table=reservas_table;
		this.numero_filas_total=proveedordatosdepaginacion.getTotalRowCount();
		this.paginaActual=1;
		
		
		
	}
	public Reservas_controller(JComboBox lista_habitaciones,JDatePickerImpl datePicker_entrada,JDatePickerImpl datePicker_salida,
			JComboBox adultos_combo,JComboBox kids_combo,JComboBox cantidad_combo,JTextField cliente_input,JButton resg_reserva,
			JLabel user_exists) {
		this.lista_habitaciones=lista_habitaciones;
		this.datePicker_entrada=datePicker_entrada;
		this.datePicker_salida=datePicker_salida;
		this.adultos_combo=adultos_combo;
		this.kids_combo=kids_combo;
		this.cantidad_combo=cantidad_combo;
		this.cliente_input=cliente_input;
		this.resg_reserva=resg_reserva;
		this.user_exists=user_exists;
	}
	public void cargar_tabla_reservas(JTable reservas_table,JLabel pagina){
		String info[][]=cargar_reservas(reservas_table);
		/*
		String titulos[]= {"id","Fecha","Fecha entrada","Fecha Salida","Numero Adultos","Numero niños","Usuario",
				"Habitacion nombre"};
				*/
		String titulos[]= {"id","Usuario","cantidad","Fecha entrada","Fecha Salida","Numero Adultos","Numero niños","Habitacion","Precio"};
		
		DefaultTableModel model=new DefaultTableModel(info,titulos);
		reservas_table.setModel(model);

		pagina.setText("Pagina: "+paginaActual+"/"+
				(int)( Math.ceil(proveedordatosdepaginacion.getTotalRowCount()/10)+1)+"  Total registros: "+proveedordatosdepaginacion.getTotalRowCount());
	}
	
	public String [][]cargar_reservas(JTable reservas_table){
		
		Reservas reserva=new Reservas();
		List<Reservas>lista_reservas=paginar(reservas_table);
		
		String info_reservas[][]=new String[lista_reservas.size()][9];
		
		for(int i=0;i<info_reservas.length;i++) {
			
			/*
			info_reservas[i][0]=String.valueOf(lista_reservas.get(i).getID());
			info_reservas[i][1]=String.valueOf(lista_reservas.get(i).getFecha());
			info_reservas[i][2]=String.valueOf(lista_reservas.get(i).getFecha_entrada());
			info_reservas[i][3]=String.valueOf(lista_reservas.get(i).getFecha_salida());
			info_reservas[i][4]=String.valueOf(lista_reservas.get(i).getNumero_adultos());
			info_reservas[i][5]=String.valueOf(lista_reservas.get(i).getNumero_kids());
			info_reservas[i][6]=lista_reservas.get(i).getUser_id();
			info_reservas[i][7]=String.valueOf(lista_reservas.get(i).getReserva_habitaciones().getHabitacion().getNombre());
			*/
			info_reservas[i][0]=String.valueOf(lista_reservas.get(i).getID());
			info_reservas[i][1]=String.valueOf(lista_reservas.get(i).getUser_id());
			info_reservas[i][2]=String.valueOf(lista_reservas.get(i).getReserva_habitaciones().getCantidad());
			info_reservas[i][3]=String.valueOf(lista_reservas.get(i).getFecha_entrada());
			info_reservas[i][4]=String.valueOf(lista_reservas.get(i).getFecha_salida());
			info_reservas[i][5]=String.valueOf(lista_reservas.get(i).getNumero_adultos());
			info_reservas[i][6]=String.valueOf(lista_reservas.get(i).getNumero_kids());
			info_reservas[i][7]=String.valueOf(lista_reservas.get(i).getReserva_habitaciones().getHabitacion().getNombre());
			info_reservas[i][8]=String.valueOf(lista_reservas.get(i).getReserva_habitaciones().getHabitacion().getPrecio());
		}
		
		return info_reservas;
		
	}
	private ProveedorDeDatosDePaginacion<Reservas> crearProveedorDeDatos(){
		
		Reservas reserva=new Reservas();
		List<Reservas> lista_reservas=reserva.listado_reservas();
		
		 return new ProveedorDeDatosDePaginacion<Reservas>() {
	            @Override
	            public int getTotalRowCount() {
	                return lista_reservas.size();
	            }

	            @Override
	            public List<Reservas> getRows(int startIndex, int endIndex) {
	                return lista_reservas.subList(startIndex, endIndex);
	            }
	        };
	}
	//FILTRO CON METODO NATIVO DE JTABLE
	public void search(String filter,JTable reservas_table ) {
		if(filter.isEmpty()) {
			paginar(reservas_table);
		}else {
			/*
			Reservas reserva_busqueda=new Reservas();
	
			reserva_busqueda.inizialize_resultset("SELECT * FROM reservas WHERE fecha_baja IS NULL AND user_id LIKE %victor%");
			List<Reservas> lista_reservas1=reserva_busqueda.listado_reservas();
			
			for(int i=0;i<lista_reservas1.size();i++) {
				System.out.println(lista_reservas1.get(i).getUser_id());
			}
			
			model=(DefaultTableModel) reservas_table.getModel();
			TableRowSorter<DefaultTableModel>trs=new TableRowSorter<>(model);
			reservas_table.setRowSorter(trs);
			trs.setRowFilter(RowFilter.regexFilter(filter));
			*/
		}
		
		
	}
	
	private List<Reservas> paginar(JTable reservas_table) {
		
		int inicio=(paginaActual-1)*filas_por_pagina; //1-1*10=0
		int fin=inicio+filas_por_pagina;
		System.out.println("pagina actual"+paginaActual);
		if(inicio<0) {
			inicio=1;
		}if(fin>proveedordatosdepaginacion.getTotalRowCount() ){
			fin=proveedordatosdepaginacion.getTotalRowCount();
		}
		
			List<Reservas>lista=proveedordatosdepaginacion.getRows(inicio, fin);
			((AbstractTableModel) reservas_table.getModel()).fireTableDataChanged();
			
		
		return lista;
		
		
	}

	
	public void avanzar_pagina(JTable reservas_table) {
		paginaActual++;

	}
	
	public void retroceder_pagina() {
		paginaActual--;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		
		if(src==resg_reserva) {
			
			insertar_reserva();
		}
		
	}
	
	
	public void insertar_reserva() {
		Reservas reserva=new Reservas();
		Habitaciones habitaciones=new Habitaciones();
		User user=new User();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		 String user_id="";
		 try {
			 
			//VALORES DEL DATEPICKER
		  Date fecha_entrada= (Date)datePicker_entrada.getModel().getValue();
		  Date fecha_salida=(Date)datePicker_salida.getModel().getValue();
		  //FORMATEO ESO VALORES A YYYY-MM-DD
		  String fechaFormateada_entrada = formato.format(fecha_entrada);
		  String fechaFormateada_salida=formato.format(fecha_salida);
		 
		 
		      Date fecha_entrada_date = formato.parse(fechaFormateada_entrada);
		      Date fecha_salida_date = formato.parse(fechaFormateada_salida);
		      //DIFERENCIA EN DIAS
		      long diferenciaEnMilisegundos = fecha_salida_date.getTime() - fecha_entrada_date.getTime();
		      long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
		    	      
		      //FECHA ACTUAL
		      Date fechaActual = new Date();
		      if (fecha_entrada_date.after(fechaActual) && fecha_salida_date.after(fechaActual)&& fecha_entrada_date.before(fecha_salida_date)) {
		    	  
		    	   String fecha_entrada_srt=formato.format(fecha_entrada_date);
			       String fecha_salida_srt=formato.format(fecha_salida_date);		
			       int numero_adultos=(int)adultos_combo.getSelectedItem();
			       int numero_ninyos=(int)kids_combo.getSelectedItem();
			       String nombre_habitacion=(String)lista_habitaciones.getSelectedItem();
			      //RECIBE OBJ HABITACION 
			       Habitaciones mi_habitacion=habitaciones.obtener_habitacion(nombre_habitacion);

			       user_id=cliente_input.getText().toString();
		       if(user.comprobar_user(user_id)){
		    	   
		    	   System.out.println("Insertada reserva");
		    	   reserva.registrar_reserva(fecha_entrada_srt, fecha_salida_srt, numero_adultos,numero_ninyos,user_id);
		    	   
		    	   Calendar calendar = Calendar.getInstance();
		    	   Date currentDate = calendar.getTime();
		    	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	   String fecha_hora_actual = format.format(currentDate);
		    	   //Aplicar texto y color al label
		    	   user_exists.setText("Reserva Registrada Correctamente");
		    	   user_exists.setForeground(new Color(0,247,0));
		    	   //OBTENER DATOS INSERT TABLA RESERVA/HABITACIONES
		    	   int id_reserva=reserva.obtener_id_reserva(fecha_hora_actual);//Obtengo id reserva insertada a traves de current time stamp
		    	   int id_habitacion=mi_habitacion.getId();
		    	   int cantidad=(int)cantidad_combo.getSelectedItem();
		    	   double precio=(mi_habitacion.getPrecio()*diferenciaEnDias);//CALCULO PRECIO TOTAL RESERVA
		    	   Reserva_habitaciones reserva_habitacion=new Reserva_habitaciones();
		    	   reserva_habitacion.registrar_reserva_habitaciones(id_habitacion, id_reserva, cantidad, precio,fecha_hora_actual);
		    	   
		       }else {
		    	   user_exists.setText("El cliente no se encuentra registrado");
		       }
		      } else {
		        System.out.println("La fecha dada es menor o igual a hoy");
		        user_exists.setText("Existen errores en su solicitud");
		      }
		    } catch (ParseException e) {
		      System.out.println("Error al parsear la fecha");
		    }catch(NullPointerException npe) {
		    	 System.out.println("Campos vacios");
		    }
	}

		   

	}
	
	

	

	
 
