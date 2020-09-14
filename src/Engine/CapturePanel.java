package Engine;

import javax.swing.*;
import java.awt.*;

public class CapturePanel extends JPanel {
	private JLabel playerLabel;
	private JPanel canvas;
	
	public CapturePanel(int w, int h, int player) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		playerLabel = new JLabel("P" + player + "'s captures", SwingConstants.CENTER);
		playerLabel.setForeground(Color.WHITE);
		add(playerLabel, BorderLayout.NORTH);
	}
	
	public void addPawn() {}
	public void addBishop() {}
	public void addKnight() {}
	public void addRook() {}
	public void addQueen() {}
	public void addKing() {}
}
