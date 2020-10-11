package Engine_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel {
	private JLabel turnLabel;
	private JLabel moveLabel;
	public JButton endTurnButton;
	
	public StatusPanel(int w, int h) {
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		turnLabel = new JLabel("Player 1's Turn", SwingConstants.CENTER);
		turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		turnLabel.setForeground(Color.WHITE);
		moveLabel = new JLabel("Moves: 0", SwingConstants.CENTER);	
		moveLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		moveLabel.setForeground(Color.WHITE);
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBackground(Color.WHITE);
		endTurnButton.setForeground(Color.BLACK);
		endTurnButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		endTurnButton.addActionListener(e -> Engine.next_Turn());
		add(turnLabel);
		add(moveLabel);
		add(endTurnButton);
	}
	public void updateMove_Count(int count){
		moveLabel.setText(String.format("Moves: %s",count));
	}
	public void updatePlayer_Turn(int player){turnLabel.setText(String.format("Player %s's Turn",player));}
}
