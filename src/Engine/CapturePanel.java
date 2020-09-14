package Engine;

import Chess.Pieces.BasePiece;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class CapturePanel extends JPanel {
	private JLabel playerLabel;
	private JPanel canvas;
	
	public CapturePanel(int w, int h, int player) {
		GridLayout layout = new GridLayout(13,1);
		layout.setVgap(10);
		layout.setHgap(10);
		setLayout(layout);
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		playerLabel = new JLabel("P" + player + "'s captures", SwingConstants.CENTER);
		playerLabel.setForeground(Color.WHITE);
		add(playerLabel, BorderLayout.NORTH);
	}

	public void addCapture(Image captured_piece){
		this.invalidate();
		JLabel picLabel = new JLabel(new ImageIcon(captured_piece.getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		add(picLabel);
		this.revalidate();
	}
}
