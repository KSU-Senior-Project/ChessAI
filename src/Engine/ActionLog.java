package Engine;

import java.awt.*;

import javax.swing.*;

public class ActionLog extends JPanel{
	private JTextArea log;
	private JScrollPane scrollPane;
	
	public ActionLog(int w, int h) {	
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(w, h));
		log = new JTextArea(5,30);
		log.setFont(new Font("Serif", Font.PLAIN, 18));
		scrollPane = new JScrollPane(log);	
		log.setText("GameLog");
		log.setEditable(false);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void appendAction(String action) {
		log.append(action);
	}
	
	
}
