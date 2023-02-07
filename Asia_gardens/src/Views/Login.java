package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import Controller.Login_controller;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swing.MyTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;

public class Login extends JFrame {
	String email;
	String password;
	
	public Login() {
	
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		JPanel imagen = new JPanel();
		imagen.setBackground(new Color(0, 64, 64));
		panel.add(imagen,"width 100%");
		
		
		JLabel lblNewLabel = new JLabel("");
	
		ImageIcon image=new ImageIcon(Login.class.getResource("/imagenes/asia_gardens_back.jpg"));
		imagen.setLayout(new MigLayout("", "[218px]", "[263px]"));
		lblNewLabel.setIcon(image);
		imagen.add(lblNewLabel, "cell 0 0,grow");
		
	
		JPanel datos_container = new JPanel();
		datos_container.setBackground(new Color(255, 255, 255));
		panel.add(datos_container);
		
		//LAYOUT
		datos_container.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]25[]10[]10[]10[]10[]push[]"));
		JLabel bienvenida = new JLabel("Bienvenido a Asia Gardens");
		bienvenida.setFont(new Font("sansserif",1,18));
		datos_container.add(bienvenida, "cell 0 0");
		
		
		//RECIBIR CORREO
		MyTextField txtemail = new MyTextField();
		txtemail.setHint("Email");
		txtemail.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/mail.png")));
		datos_container.add(txtemail, "cell 0 2,width 50%");
		email=txtemail.getText().toString();
		
		//RECIBIR PASSWORD
		MyTextField txtpassword = new MyTextField();
		txtpassword.setHint("Contraseña");
		txtpassword.setPrefixIcon(new ImageIcon(getClass().getResource("/Icon/pass.png")));
		datos_container.add(txtpassword, "cell 0 3,width 50%");
		password=txtpassword.getText().toString();
		
		JButton forget = new JButton("¿Olvidaste tu contraseña?");
		forget.setForeground(new Color(100,100,100));
		forget.setFont(new Font("sansserif",1,12));
		forget.setContentAreaFilled(false);
		datos_container.add(forget, "cell 0 4");
		
		
		
		JButton login=new JButton("Login");
		login.setBackground(new Color(115,191,219));
		login.setForeground(new Color(250,250,250));
		datos_container.add(login,"cell 0 6,width 20%,height 40");
		 
		JLabel error = new JLabel("");
		datos_container.add(error, "cell 0 5");
		error.setForeground(new Color(255,0,0));
		//VIEW CONTROLLER
		
		Login_controller login_controller=new Login_controller(txtemail,txtpassword,error,this);
		login.addActionListener(login_controller);
	}
	
	
	
	
	
	
	
	public class Imagen_logo extends javax.swing.JPanel {
		 
		public Imagen_logo() {
		this.setSize(400, 400); //se selecciona el tamaño del panel
		}
		 
		//Se crea un método cuyo parámetro debe ser un objeto Graphics
		 
		public void paint(Graphics grafico) {
		Dimension size = getSize();
		 
		//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
		 
		ImageIcon Img = new ImageIcon(getClass().getResource("/Imagenes/logo_colorizado.png")); 
		 
		//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
		 
		grafico.drawImage(Img.getImage(), 0, 0, size.width, size.height, null);
		 
		setOpaque(false);
		super.paintComponent(grafico);
		}
		}
}
