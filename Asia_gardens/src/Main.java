import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ConexionBD.Conexion;
import Views.Dashboard;
import Views.Login;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	/*
                    Login login=new Login();
                    login.setSize(1000,600);
                    login.setVisible(true);
                    login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    */
                	Dashboard dashboard=new Dashboard();
                	dashboard.setSize(1000,600);
                	dashboard.setVisible(true);
                	dashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
		
		


}
