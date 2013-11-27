import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * @author marco.mangan@pucrs.br
 * @author marco.gonzales@pucrs.br
 *
 */
@SuppressWarnings("serial")
public class JBubbleExplodeClone extends JFrame {
	
	/**
	 * Main Tabuleiro.
	 */
	private Tabuleiro tabuleiro;
	
	/**
	 * Button actions.
	 */
	private ArrayList<ExplodeBubbleAction> actions;
	
	/**
	 * Display user score.
	 */
	private JLabel score;
	
	/**
	 * Display last bang shape
	 */
	private JLabel shape;
	
	/**
	 * Display combo multiplier.
	 */
	private JLabel combo;
	
	/**
	 * Displays end of game.
	 */
	private JLabel eog;
	
	/**
	 * A blue icon.
	 */
	private ImageIcon blueIcon;
	
	/**
	 * A yellow icon.
	 */
	private Icon yellowIcon;
	
	/**
	 * A green icon.
	 */
	private Icon greenIcon;
	
	/**
	 * A red icon.
	 */
	private Icon redIcon;

	/**
	 * Creates a new frame and init panels.
	 * 
	 * @param title
	 */
	public JBubbleExplodeClone(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabuleiro = new Tabuleiro();
		actions = new ArrayList<ExplodeBubbleAction>();

		loadIcons();
		createLabels();
		JPanel scoreTabuleiro = prepareScoreTabuleiro();

		JPanel cells = prepareMainTabuleiro();

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.NORTH, scoreTabuleiro);
		getContentPane().add(BorderLayout.CENTER, cells);
		
		update();
	}

	/**
	 * @return
	 */
	private JPanel prepareScoreTabuleiro() {
		JPanel scoreTabuleiro = new JPanel();
		JPanel scorePanel = new JPanel();
		JPanel shapePanel = new JPanel();
		JPanel comboPanel = new JPanel();
		JPanel eogPanel = new JPanel();

		preparePanels(scorePanel, shapePanel, comboPanel, eogPanel);

		prepareScoreTabuleiro(scoreTabuleiro, scorePanel, shapePanel, comboPanel, eogPanel);
		return scoreTabuleiro;
	}

	/**
	 * @return
	 */
	private JPanel prepareMainTabuleiro() {
		JPanel cells = new JPanel();
		cells.setLayout(new GridLayout(13, 10));
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 10; j++) {
				ExplodeBubbleAction e = new ExplodeBubbleAction(i, j);
				JButton b = new JButton(e);
				
				cells.add(b);
				actions.add(e);
				e.setButton(b);
			}
		}
		return cells;
	}

	/**
	 * @param placar
	 * @param scorePanel
	 * @param shapePanel
	 * @param comboPanel
	 * @param eogPanel
	 */
	private void prepareScoreTabuleiro(JPanel placar, JPanel scorePanel,
			JPanel shapePanel, JPanel comboPanel, JPanel eogPanel) {
		placar.setLayout(new BoxLayout(placar, BoxLayout.LINE_AXIS));
		placar.add(Box.createHorizontalGlue());
		placar.add(scorePanel);
		placar.add(Box.createRigidArea(new Dimension(10, 0)));

		placar.add(shapePanel);
		placar.add(Box.createRigidArea(new Dimension(10, 0)));

		placar.add(comboPanel);
		placar.add(Box.createRigidArea(new Dimension(10, 0)));

		placar.add(eogPanel);
	}

	/**
	 * @param scorePanel
	 * @param shapePanel
	 * @param comboPanel
	 * @param eogPanel
	 */
	private void preparePanels(JPanel scorePanel, JPanel shapePanel,
			JPanel comboPanel, JPanel eogPanel) {
		scorePanel.setLayout(new BorderLayout());
		Border scoreBorder = BorderFactory.createTitledBorder("Score:");
		scorePanel.setBorder(scoreBorder);
		scorePanel.add(BorderLayout.CENTER, score);

		shapePanel.setLayout(new BorderLayout());
		Border shapeBorder = BorderFactory.createTitledBorder("Shape:");
		shapePanel.setBorder(shapeBorder);
		shapePanel.add(BorderLayout.CENTER, shape);

		comboPanel.setLayout(new BorderLayout());
		Border comboBorder = BorderFactory.createTitledBorder("Combo:");
		comboPanel.setBorder(comboBorder);
		comboPanel.add(BorderLayout.CENTER, combo);

		eogPanel.setLayout(new BorderLayout());
		Border eogBorder = BorderFactory.createTitledBorder("Status:");
		eogPanel.setBorder(eogBorder);
		eogPanel.add(BorderLayout.CENTER, eog);
	}

	/**
	 * 
	 */
	private void createLabels() {
		score = new JLabel();
		shape = new JLabel();
		combo = new JLabel();
		eog = new JLabel();
	}

	/**
	 * 
	 */
	private void loadIcons() {
		blueIcon = new ImageIcon(getClass().getResource(
				"resources/bubble-blue.png"));
		greenIcon = new ImageIcon(getClass().getResource(
				"resources/bubble-green.png"));
		redIcon = new ImageIcon(getClass().getResource(
				"resources/bubble-red.png"));
		yellowIcon = new ImageIcon(getClass().getResource(
				"resources/bubble-yellow.png"));
	}

	/**
	 * 
	 *
	 */
	class ExplodeBubbleAction extends AbstractAction {
		/**
		 * 
		 */
		private Bolha bolha;
		
		/**
		 * 
		 */
		private int row;
		
		/**
		 * 
		 */
		private int column;
		
		/**
		 * 
		 */
		private JButton button;

		/**
		 * 
		 * @param row
		 * @param column
		 */
		public ExplodeBubbleAction(int row, int column) {
			setCell(row, column);
		}

		/**
		 * 
		 * @param button
		 */
		public void setButton(JButton button) {
			this.button = button;
			setCell(row, column);

		}

		/**
		 * 
		 * @param row
		 * @param column
		 */
		private void setCell(int row, int column) {
			this.row = row;
			this.column = column;
			bolha = tabuleiro.getLocal(row, column);
			if (bolha == null) {
				setEnabled(false);
				//putValue(NAME, "");
			} else {
				//putValue(
				//		NAME,
				//		String.format("<html><bold>%s</bold></html>",
				//				bolha.getCor()));
				if (button != null) {
					if (bolha.getCor() == 'Y') {
						button.setIcon(yellowIcon);
					} else if (bolha.getCor() == 'G') {
						button.setIcon(greenIcon);
					} else if (bolha.getCor() == 'R') {
						button.setIcon(redIcon);
					} else if (bolha.getCor() == 'B') {
						button.setIcon(blueIcon);
					}
					button.setSize(60, 60);
					button.setPreferredSize(new Dimension(60, 60));
					button.setMaximumSize(new Dimension(60, 60));
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tabuleiro.explodirBolha(bolha.getLinha(), bolha.getColuna());
			update();
		}

		/**
		 * 
		 */
		public void updateButton() {
			setCell(row, column);
		}

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		JBubbleExplodeClone frame = new JBubbleExplodeClone(
				"Bubble Explode Clone");

		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 
	 */
	public void update() {
		score.setText("" + tabuleiro.getEscore());
		shape.setText(tabuleiro.getFormaAnterior());
		combo.setText("" + tabuleiro.getCombo());
		for (ExplodeBubbleAction e : actions) {
			e.updateButton();
		}
		if (tabuleiro.verificarFim()) {
			eog.setText("Game over!");
			for (ExplodeBubbleAction e : actions) {
				e.setEnabled(false);
			}
		} else {
			eog.setText("Game on...");
		}
		score.requestFocus();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
