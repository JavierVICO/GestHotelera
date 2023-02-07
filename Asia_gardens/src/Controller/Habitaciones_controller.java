package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Models.Habitaciones;
import Models.Reservas;

public class Habitaciones_controller implements  ActionListener  {
	private JTable table_habitaciones;
	private DefaultTableModel model;
	private JTextField nombre_input,descripcion_input,precio_input;
	private JComboBox cantidad_combo,num_max_personas_combo,num_max_camas_combo;
	private JButton btn_insertar,btn_limpiar;
	ProveedorDeDatosDePaginacion<Habitaciones>proveedordatosdepaginacion=crearProveedorDeDatos();
	private int filas_por_pagina=10;
	private int paginaActual;
    private int numeroPaginas = 0;
	private int numero_filas_total=0;
	
	
	public Habitaciones_controller(JTable table_habitaciones) {
		this.table_habitaciones=table_habitaciones;
		this.numero_filas_total=proveedordatosdepaginacion.getTotalRowCount();
		this.paginaActual=1;
	}
	public Habitaciones_controller(JTextField nombre_input,JTextField descripcion_input,
			JComboBox <Integer>cantidad_combo,JTextField precio_input,JComboBox<Integer> num_max_personas_combo,
			JComboBox<Integer> num_max_camas_combo,JButton btn_insertar,JButton btn_limpiar){
		this.nombre_input=nombre_input;
		this.descripcion_input=descripcion_input;
		this.cantidad_combo=cantidad_combo;
		this.precio_input=precio_input;
		this.num_max_personas_combo=num_max_personas_combo;
		this.num_max_camas_combo=num_max_camas_combo;
		this.btn_insertar=btn_insertar;
		this.btn_limpiar=btn_limpiar;
				
	};
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		if(src==btn_insertar){
			insertar_habitacion();
		}if(src==btn_limpiar) {
			limpiar_datos();
		}
		
	}

	public void cargar_tabla_habitaciones(JTable table_habitaciones){
		String info[][]=cargar_datos(table_habitaciones);
		String titulos[]= {"id","Nombre","Descripcion","Cantidad","Precio","Numero max.personas","Numero camas"};
		DefaultTableModel model=new DefaultTableModel(info,titulos);
		table_habitaciones.setModel(model);
		
	}
	/**
	 * Metodo Cargar Datos en la Table habitaciones
	 * @param table_habitaciones
	 * @return
	 */
	public String[][]cargar_datos(JTable table_habitaciones){
		
		Habitaciones habitacion=new Habitaciones();
		List<Habitaciones>lista=paginar(table_habitaciones);
		
		String info[][]=new String[lista.size()][7];
		
		for(int i=0;i<info.length;i++){
			
			info[i][0]=String.valueOf(lista.get(i).getId());
			info[i][1]=lista.get(i).getNombre();
			info[i][2]=lista.get(i).getDescripcion();
			info[i][3]=String.valueOf(lista.get(i).getCantidad());
			info[i][4]=String.valueOf(lista.get(i).getPrecio());
			info[i][5]=String.valueOf(lista.get(i).getNum_max_personas());
			info[i][6]=String.valueOf(lista.get(i).getNumero_camas());
		}
		
		return info;
		
		
	}
	private ProveedorDeDatosDePaginacion<Habitaciones> crearProveedorDeDatos(){
		Habitaciones habitacion=new Habitaciones();
		List<Habitaciones>lista_habitaciones=habitacion.listado_habitaciones();
		
		 return new ProveedorDeDatosDePaginacion<Habitaciones>() {
	            @Override
	            public int getTotalRowCount() {
	                return lista_habitaciones.size();
	            }

	            @Override
	            public List<Habitaciones> getRows(int startIndex, int endIndex) {
	                return lista_habitaciones.subList(startIndex, endIndex);
	            }
	        };
	}
		private List<Habitaciones> paginar(JTable table_habitaciones ) {
				
				int inicio=(paginaActual-1)*filas_por_pagina; //1-1*10=0
				int fin=inicio+filas_por_pagina;
				if(inicio<0) {
					inicio=1;
				}if(fin>proveedordatosdepaginacion.getTotalRowCount() ){
					fin=proveedordatosdepaginacion.getTotalRowCount();
				}
					List<Habitaciones>lista=proveedordatosdepaginacion.getRows(inicio, fin);
					((AbstractTableModel) table_habitaciones.getModel()).fireTableDataChanged();
					
				
				return lista;
				
				
			}

	 /**
	  * METODO FILTRAR POR LA TABLA
	  * @param filter
	  * @param table_habitaciones
	  */
		public void search(String filter,JTable table_habitaciones ) {
				
				model=(DefaultTableModel) table_habitaciones.getModel();
				TableRowSorter<DefaultTableModel>trs=new TableRowSorter<>(model);
				table_habitaciones.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(filter));
				
			}
		/**
		 * METODO VALIDAR CADENA LETRAS
		 * @param cadena
		 * @return
		 */
		 public static boolean contieneSoloLetras(String cadena) {
		    for (int x = 0; x < cadena.length(); x++) {
		        char c = cadena.charAt(x);
		        
		        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
		            return false;
		        }
		    }
		    return true;
		}
		public void insertar_habitacion(){
			Habitaciones habitacion=new Habitaciones();
			String nombre="";
			if(contieneSoloLetras(nombre_input.getText().toString())){
				 nombre=nombre_input.getText().toString();
				 String descripcion=descripcion_input.getText().toString();
					Integer cantidad=(Integer) cantidad_combo.getSelectedItem();
					int int_cantidad=(int) cantidad_combo.getSelectedItem();;
					Double precio=Double.parseDouble(precio_input.getText().toString());
					Integer cantidad_max_personas=(Integer)num_max_personas_combo.getSelectedItem();
					int int_cantidad_max_personas=(int) num_max_personas_combo.getSelectedItem();
					Integer cantidad_max_camas=(Integer) num_max_camas_combo.getSelectedItem();
					int int_cantidad_max_camas=(int)num_max_camas_combo.getSelectedItem();
					
					habitacion.registrar_habitacion(nombre, descripcion, int_cantidad, precio, int_cantidad_max_personas, int_cantidad_max_camas);
			}else {
				System.err.println("Datos no validos");
			}
			
		}
		
		public void limpiar_datos(){
			nombre_input.setText(" Manolo");
			descripcion_input.setText(" ");
			precio_input.setText(" ");
		}
		
	
		
		
	

}
