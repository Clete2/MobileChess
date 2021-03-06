package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;

import network.IPPortValidator;

import shared.Game;
import shared.Piece;
import shared.PieceColor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.SwingConstants;

public class ClientWindow {

	private JFrame frame;
	private static JLabel colorLabel;
	private static JLabel turnLabel;
	private static JLabel turnNumberLabel;
	private static JPanel[] chessSquares;
	private static Vector<JLabel> chessLabels;
	private static Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 502, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		centerWindow(frame);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 515, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem newLocalGameMenuItem = new JMenuItem("New Local Game");
		newLocalGameMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				game = new Game();
				initializePieces();
			}
		});
		mnFile.add(newLocalGameMenuItem);

		JMenuItem newInternetGameMenuItem = new JMenuItem("Join Internet Game");
		newInternetGameMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				IPPortValidator validator = new IPPortValidator();
				String ip = "";
				String port = "";
				boolean valid = false;
				while(!valid) {
					ip = JOptionPane.showInputDialog("IP Address:");
					if(ip == null) {
						return;
					}
					valid = validator.isIPValid(ip);
				}
				valid = false;
				while(!valid) {
					port = JOptionPane.showInputDialog("Port:");
					if(port == null) {
						return;
					}
					valid = validator.isPortValid(port);
				}
				try {
					game = new Game(validator.getIPBytes(ip), Short.parseShort(port));
					initializePieces();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnFile.add(newInternetGameMenuItem);
		
		colorLabel = new JLabel("");
		colorLabel.setBounds(10, 521, 95, 16);
		frame.getContentPane().add(colorLabel);
		
		turnLabel = new JLabel("");
		turnLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		turnLabel.setBounds(355, 521, 132, 16);
		frame.getContentPane().add(turnLabel);
		
		turnNumberLabel = new JLabel("");
		turnNumberLabel.setBounds(202, 521, 122, 16);
		frame.getContentPane().add(turnNumberLabel);

		chessSquares = new JPanel[64];
		chessLabels = new Vector<JLabel>();

		for(int i = 0; i < 64; i++) { // Sudo make me a grid
			chessSquares[i] = new JPanel();
			chessSquares[i].setBounds((i % 8 * 60) + 10, ((i / 8) * 60) + 35, 60, 60); // Keeps spacing between panels
			chessLabels.add(new JLabel());
			if((i % 2 == 0 && (i / 8) % 2 == 0) || (i % 2 == 1 && (i / 8) % 2 == 1)) { // Make checkered pattern
				chessSquares[i].setBackground(Color.GRAY); // Does this make me look fat?
			} else {
				chessSquares[i].setBackground(Color.LIGHT_GRAY); // How about this?
			}
			chessLabels.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Piece pieceToWatch = 
						game.getBoard().getPieceList().getPieceAtIndex(chessLabels.indexOf(e.getSource()));
					if(pieceToWatch.getPieceColor().equals(PieceColor.WHITE) &&
							!game.getBoard().getPieceList().isPieceSelected()) { // First selection, player and piece are white
						game.getWhitePlayer().selectPiece(pieceToWatch, game);
					} else if(pieceToWatch.getPieceColor().equals(PieceColor.BLACK) &&
							!game.getBoard().getPieceList().isPieceSelected()) { // First selection, player and piece are black
						game.getBlackPlayer().selectPiece(pieceToWatch, game);
					} else if(game.getBoard().getPieceList().isPieceSelected()) { // Moving to blank piece or second selection (where player is allowed to click on opposite color)
						game.getBoard().getPieceList().getPieceSelector().selectPiece(pieceToWatch, game);
					}
				}
			});
			chessSquares[i].add(chessLabels.get(i));
			frame.getContentPane().add(chessSquares[i]);
		}
	}

	private void initializePieces() {
		for(short i = 0; i < 64; i++) {
			chessLabels.elementAt(i).setIcon(game.getBoard().getPieceList().getImageForPiece((
					game.getBoard().getPieceList().getPieceAtIndex(i))));
		}
	}

	public static Vector<JLabel> getChessLabels() {
		return chessLabels;
	}

	private void centerWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int)((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	public static void setColorLabelText(String textToSet) {
		colorLabel.setText(textToSet);
	}
	
	public static void setTurnLabelText(String textToSet) {
		turnLabel.setText(textToSet);
	}
	
	public static void setTurnNumberLabelText(String textToSet) {
		turnNumberLabel.setText(textToSet);
	}
}
