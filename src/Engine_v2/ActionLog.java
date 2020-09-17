package Engine_v2;

import javax.swing.*;
import java.awt.*;

public class ActionLog extends JPanel{
	private static JTextArea log;
	private JScrollPane scrollPane;
	private final int borderWidth = 10;
	
	public ActionLog(int w, int h) {	
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, borderWidth));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(w, h));
		log = new JTextArea(5,30);
		//log.setFont(new Font("Serif", Font.PLAIN, 18));
		scrollPane = new JScrollPane(log);	
		log.setText("GameLog");
		log.setEditable(false);
		add(scrollPane, BorderLayout.CENTER);		
	}
	
	public static void appendAction(String action) {
		log.append("\n" + action);
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	
}
