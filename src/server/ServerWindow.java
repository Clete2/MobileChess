package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerWindow {

	private static ServerSocketHandler server;
	private JFrame frmMobileChessServer;
	private JTextField listenPortTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server = new ServerSocketHandler((short)1337);
					server.start();
					ServerWindow window = new ServerWindow();
					window.frmMobileChessServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMobileChessServer = new JFrame();
		frmMobileChessServer.setTitle("Mobile Chess Server");
		frmMobileChessServer.setBounds(100, 100, 271, 55);
		frmMobileChessServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frmMobileChessServer.getContentPane().setLayout(gridBagLayout);
		
		JLabel portLabel = new JLabel("Port to listen on:");
		GridBagConstraints gbc_portLabel = new GridBagConstraints();
		gbc_portLabel.insets = new Insets(0, 0, 0, 5);
		gbc_portLabel.anchor = GridBagConstraints.EAST;
		gbc_portLabel.gridx = 0;
		gbc_portLabel.gridy = 0;
		frmMobileChessServer.getContentPane().add(portLabel, gbc_portLabel);
		
		listenPortTextField = new JTextField();
		listenPortTextField.setText("1337");
		GridBagConstraints gbc_listenPort = new GridBagConstraints();
		gbc_listenPort.insets = new Insets(0, 0, 0, 5);
		gbc_listenPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_listenPort.gridx = 1;
		gbc_listenPort.gridy = 0;
		frmMobileChessServer.getContentPane().add(listenPortTextField, gbc_listenPort);
		listenPortTextField.setColumns(10);
		
		JButton portApplyButton = new JButton("Apply");
		portApplyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				server.shutdown();
				server = new ServerSocketHandler(Short.parseShort(listenPortTextField.getText()));
				server.start();
			}
		});
		GridBagConstraints gbc_portApplyButton = new GridBagConstraints();
		gbc_portApplyButton.gridx = 2;
		gbc_portApplyButton.gridy = 0;
		frmMobileChessServer.getContentPane().add(portApplyButton, gbc_portApplyButton);
	}

}
