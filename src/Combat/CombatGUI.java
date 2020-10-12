package Combat;

import Chess.Pieces.BasePiece;
import Dice.Dice;
import Engine_v2.Engine;
import Engine_v2.GameBoard;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
    private int roll_count;
    private int current_roll;
    private Random random;
    private Dice currentDice;
    private Object lock;
    private int diceModifier;
    JLabel results;
    public enum CombatState{
        running,
        leave,
        won,
        lost
    }
    private CombatState state;

    /*REDO OR REWRITE WHOLE CLASS, I WROTE IT POORLY*/

    public CombatGUI(BasePiece piece_one,BasePiece piece_two,int diceModifier){
        this.diceModifier = diceModifier;
        state = CombatState.running;
        setPiece_one(piece_one);
        setPiece_two(piece_two);
        combat_stats = Engine.get_Rolls(piece_one.getID(),piece_two.getID());
        random = new Random();
        lock = new Object();

        setSize(new Dimension(400,400));
        setLayout(new BorderLayout());
        JLabel player_1 = new JLabel(new ImageIcon(piece_one.getImage()));
        this.getContentPane().add(player_1,BorderLayout.WEST);

        JLabel player_2 = new JLabel(new ImageIcon(piece_two.getImage()));
        this.getContentPane().add(player_2,BorderLayout.EAST);
        this.setVisible(true);


        dicePanel = new JPanel();
        this.getContentPane().add(dicePanel,BorderLayout.CENTER);
        dicePanel.setLayout(new GridLayout(3,1));
        results = new JLabel("",SwingConstants.CENTER);
        dicePanel.add(results);
        dicePanel.add(new JLabel(new ImageIcon(getRandom_Dice().getImage())));

        JLabel stats_needed = new JLabel(String.format("Dice Rolls Needed: "),SwingConstants.CENTER);
        stats_needed.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        for(int i : combat_stats)
            stats_needed.setText(stats_needed.getText() + " " + i);
        if(diceModifier != 0)
            stats_needed.setText(stats_needed.getText() + ", Minus 1 to overall score");

        this.getContentPane().add(stats_needed,BorderLayout.NORTH);

        JPanel options = new JPanel();
        JButton roll = new JButton("Roll");

        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state == CombatState.running){
                    roll_count = random.nextInt(5) + 5;
                    current_roll = 0;
                    randomize_roll.start();
                }
            }
        });
        roll.setSize(new Dimension(100,50));
        options.add(roll);

        JButton leave = new JButton("Leave");
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state == CombatState.running)
                    state = CombatState.leave;
                setVisible(false);
            }
        });
        leave.setSize(new Dimension(100,50));
        options.add(leave);
        this.getContentPane().add(options,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public ActionListener update_dice = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(current_roll++ > roll_count) {
                randomize_roll.stop();
                System.out.println(currentDice.getNumber() + diceModifier);
                if(currentDice.getNumber() + diceModifier >= combat_stats[combat_stats.length - 1]){
                    results.setText("WON!");
                    state = CombatState.won;
                }else{
                    results.setText("LOST!");
                    state = CombatState.lost;
                }
                close_window.start();
            }else {
                dicePanel.invalidate();
                dicePanel.remove(1);
                dicePanel.add(new JLabel(new ImageIcon(getRandom_Dice().getImage())));
                dicePanel.revalidate();
            }
        }
    };
    public ActionListener hide = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            close_window.stop();
            setVisible(false);
        }
    };
    private Timer randomize_roll = new Timer(200,update_dice);
    private Timer close_window = new Timer(1000,hide);


    public Dice getRandom_Dice(){
        this.currentDice = Engine.dice[random.nextInt(6)];
        return currentDice;
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

    public CombatState getCombatState(){
        return this.state;
    }

    public int getDiceModifier(){ return this.diceModifier; }
}
