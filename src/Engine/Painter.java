package Engine;

import Tile.Tile;

import javax.swing.*;
import java.awt.*;


/*Base Painter JPanel that will control underlying rendering*/
public class Painter extends JPanel {

    public static Tile[][] tiles;

    public Painter(){
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(8,8));

        tiles = new Tile[8][8];
        Color current_Color;
        for(int y = 0; y < 8;y++){
            for(int x = 0; x < 8;x++){
                if(y % 2 == 0)
                    current_Color = x % 2 == 1 ? Color.BLACK : Color.WHITE;
                else
                    current_Color = x % 2 == 0 ? Color.BLACK : Color.WHITE;
                tiles[y][x] = new Tile(x,y,current_Color);
                this.add(tiles[y][x]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
