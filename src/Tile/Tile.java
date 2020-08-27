package Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener {
    private Color Background;
    private int x;
    private int y;

    public Tile(int x,int y,Color Background){
        super();
        set_X(x);
        set_Y(y);
        setBackground(Background);
        addMouseListener(this);
    }
    
    public int get_X(){
        return this.x;
    }
    public int get_Y(){
        return this.y;
    }
    public void set_X(int x){
        this.x = x;
    }
    public void set_Y(int y){
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(this.get_X() + " " + this.get_Y());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
