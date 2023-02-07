package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import swing.RoundedBorder;

import javax.swing.JLabel;

public class confirmar extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			confirmar dialog = new confirmar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public confirmar() {
		setBounds(100, 100, 260, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "push[center]push", "[grow]"));
		{
			JLabel confirmtxt = new JLabel("\u00BFDesea Realizar esta accion?");
			confirmtxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
			
			contentPanel.add(confirmtxt, "cell 0 0");
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new MigLayout("", "push[45px][63px]push", "[21px]"));
			{
				JButton okButton = new JButton("Confirmar");
				okButton.setActionCommand("OK");
				okButton.setBorder(new RoundedBorder(10)); 
				okButton.setForeground(Color.BLACK);
				okButton.setBackground(new Color(114,189,217));
				buttonPane.add(okButton, "cell 0 0,alignx left,aligny top");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBorder(new RoundedBorder(10)); 
				cancelButton.setForeground(Color.BLACK);
				cancelButton.setBackground(new Color(255, 153, 131));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "cell 1 0,alignx left,aligny top");
			}
		}
	}

}
