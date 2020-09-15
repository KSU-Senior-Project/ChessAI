package Combat;

import Chess.Pieces.BasePiece;
import Engine.Engine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CombatGUI extends JFrame {

    private BasePiece piece_one;
    private BasePiece piece_two;
    private int[] combat_stats;
    private JPanel dicePanel;

    private Random random;


    public CombatGUI(BasePiece piece_one,BasePiece piece_two){
        setPiece_one(piece_one);
        setPiece_two(piece_two);
        combat_stats = Engine.get_Rolls(piece_one.getID(),piece_two.getID());
        random = new Random();

        setSize(new Dimension(400,400));
        setLayout(new BorderLayout());
        JLabel player_1 = new JLabel(new ImageIcon(piece_one.getImage()));
        this.getContentPane().add(player_1,BorderLayout.WEST);

        JLabel player_2 = new JLabel(new ImageIcon(piece_two.getImage()));
        this.getContentPane().add(player_2,BorderLayout.EAST);
        this.setVisible(true);



        dicePanel = new JPanel();
        this.getContentPane().add(dicePanel,BorderLayout.CENTER);
        dicePanel.setLayout(new GridLayout(1,2));
        for(int i = 0; i < 2;i++)
            dicePanel.add(new JLabel(new ImageIcon(Engine.dice[i])),BorderLayout.CENTER);

        JLabel stats_needed = new JLabel(String.format("Dice Rolls Needed: "),SwingConstants.CENTER);
        for(int i : combat_stats)
            stats_needed.setText(stats_needed.getText() + " " + i);

        this.getContentPane().add(stats_needed,BorderLayout.NORTH);

        JPanel options = new JPanel();
        JButton roll = new JButton("Roll");
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < random.nextInt(5) + 10;i++){
                    long start = System.currentTimeMillis();
                    dicePanel.invalidate();
                    dicePanel.removeAll();

                    for(int x = 0; x < 2;x++)
                        dicePanel.add(new JLabel(new ImageIcon(getRandom_Dice())));

                    dicePanel.revalidate();
                    while(start + 200 > System.currentTimeMillis()){
                        System.out.println(System.currentTimeMillis());
                    }
                }
            }
        });
        roll.setSize(new Dimension(100,50));
        options.add(roll);

        JButton leave = new JButton("Leave");
        leave.setSize(new Dimension(100,50));
        options.add(leave);
        this.getContentPane().add(options,BorderLayout.SOUTH);
    }


    public Image getRandom_Dice(){
        return Engine.dice[random.nextInt(6)];
    }

    public BasePiece getPiece_one() {
        return piece_one;
    }

    public void setPiece_one(BasePiece piece_one) {
        this.piece_one = piece_one;
    }

    public BasePiece getPiece_two() {
        return piece_two;
    }

    public void setPiece_two(BasePiece piece_two) {
        this.piece_two = piece_two;
    }
}
