package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.json.JSONException;
import org.json.JSONObject;
import Views.Dashboard;
import Views.Login;
public class Login_controller implements ActionListener {
	private JTextField email;
	private JTextField password;
	private JLabel error;
	private JFrame login;
	
	public Login_controller(JTextField email,
			JTextField password,JLabel error,JFrame login) {
		super();
		this.email=email;
		this.password=password;
		this.error=error;
		this.login=login;
		
	}

	//LOGIN CON HTTPREQUEST
	
	@Override
	public void actionPerformed(ActionEvent e) {
		loguear(email,password,error,login);
		
		
		
	}
	//METODO CONEXION
	public static void loguear(JTextField email,JTextField password,JLabel error,JFrame login) {
	
		try {
			
			URL url = new URL("http://localhost/SW-GestionHotelera/sw_user.php");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			//PARAMETROS
			Map<String, String> parameters = new HashMap<>();
			parameters.put("action", "login");
			//parameters.put("user", "{\"email\":\"j@j.com\",\"password\":\"1234\"}");
			parameters.put("user", "{\"email\":\""+email.getText().toString()+"\",\"password\":"+password.getText().toString()+"}");
			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(getParamsString(parameters));
			out.flush();
			out.close();
			int status=con.getResponseCode();

			
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
					in.close();
					con.disconnect();
					System.out.println(status);
				
					//PARSEO DEL JSON
					JSONObject myrespuesta=new JSONObject(content.toString());
					System.out.println(myrespuesta);
					JSONObject user_object=new JSONObject(myrespuesta.getJSONObject("data").toString());
					
					if(user_object.toString()!=null){
						System.out.println("Login ok");
						invocar_dashboard(login);
						
					}else { 
						System.out.println("no funciona");
						
						};
				
		}catch(MalformedURLException murl) {
			murl.printStackTrace();
		}catch(IOException IOE ) {
			IOE.printStackTrace();
		}catch(JSONException jsonexp){
			
			System.out.println("El usuario no existe");
			error.setText("El usuario no existe");
		}
		
		}
	

	
	 public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
	        StringBuilder result = new StringBuilder();

	        for (Map.Entry<String, String> entry : params.entrySet()) {
	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	          result.append("=");
	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	          result.append("&");
	        }

	        String resultString = result.toString();
	        return resultString.length() > 0
	          ? resultString.substring(0, resultString.length() - 1)
	          : resultString;
	    }
	 
	 public static void invocar_dashboard(JFrame login){
		  SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                		
		                	JFrame dashboard=new Dashboard();
		                    dashboard.setSize(1000,600);
		                    dashboard.setVisible(true);
		                    login.setVisible(false);
		                    login.dispose();
		                    
	                    
	                }catch (Exception e){
	                    e.printStackTrace();
	                }

	            }
	        });
	 }
	
	
}

