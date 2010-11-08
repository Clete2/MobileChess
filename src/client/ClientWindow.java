package client;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuItem;

import shared.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class ClientWindow {

	private JFrame frame;
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
		frame.setBounds(100, 100, 502, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 515, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem newLocalGameMenuItem = new JMenuItem("New Local Game");
		newLocalGameMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				game = new Game();
				initializePieces();
			}
		});
		mnFile.add(newLocalGameMenuItem);
		
		JMenuItem newInternetGameMenuItem = new JMenuItem("New Internet Game");
		newInternetGameMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new NewInternetGameDialog();
			}
		});
		mnFile.add(newInternetGameMenuItem);

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
					game.getBoard().getPieceList().selectPieceOnLabel(
							game.getBoard().getPieceList().getPieceAt(
									(short)(chessLabels.indexOf(e.getSource()) / 8),
									(short)(chessLabels.indexOf(e.getSource()) % 8)), game);
				}
			});
			chessSquares[i].add(chessLabels.get(i));
			frame.getContentPane().add(chessSquares[i]);
		}
	}

	private void initializePieces() {
		for(short i = 0; i < 64; i++) {
			chessLabels.elementAt(i).setIcon(game.getBoard().getPieceList().getImageForPiece((
					game.getBoard().getPieceList().getPieceAt((short)(i / 8), (short)(i % 8)))));
		}
	}
	
	public static Vector<JLabel> getChessLabels() {
		return chessLabels;
	}
}
