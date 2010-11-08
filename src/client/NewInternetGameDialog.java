package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewInternetGameDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField ipTextField;
	private JTextField portTextField;

	public NewInternetGameDialog() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Connect");
		setBounds(100, 100, 185, 142);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel ipLabel = new JLabel("IP:");
			ipLabel.setBounds(6, 12, 15, 16);
			ipLabel.setToolTipText("");
			contentPanel.add(ipLabel);
		}
		
		ipTextField = new JTextField();
		ipTextField.setBounds(38, 6, 141, 28);
		contentPanel.add(ipTextField);
		ipTextField.setColumns(10);
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(6, 44, 61, 16);
		contentPanel.add(portLabel);
		
		portTextField = new JTextField();
		portTextField.setBounds(38, 38, 141, 28);
		contentPanel.add(portTextField);
		portTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
}
