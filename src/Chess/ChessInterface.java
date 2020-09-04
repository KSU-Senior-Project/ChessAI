package Chess;

import java.awt.Color;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class ChessInterface extends JFrame {
	JButton[][] grids = new JButton[8][8];
	
	JButton Start = new JButton();
	
	public ChessInterface() {
		setLayout(new GridLayout(8, 8));
		
		int count = 0;
		for(int i = 0; i < grids.length; i++, count++) {
			for(int j = 0; j < grids.length; j++) {
				grids[i][j] = new JButton();
				if(count % 2 == 0) {
					grids[i][j].setBackground(Color.WHITE);
					System.out.println("a");
				} else {
					grids[i][j].setBackground(Color.BLACK);
				}
				add(grids[i][j]);
				count++;
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame chessBorder = new ChessInterface();
		chessBorder.setTitle("ChessAI");
		chessBorder.setLocation(300, 200);
		chessBorder.setSize(400, 400);
		chessBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBorder.setVisible(true);
	}
}
