package Engine;

import Chess.Pieces.ChessPieceBase;
import Tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/*Base Painter JPanel that will control underlying rendering*/
public class Painter extends JPanel {

    public Painter(){
        //Setting defaults for JPanel
        this.setLayout(new GridLayout(8,8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

        //Drawing Tiles
        for(int y = 0; y < Engine.tiles.length;y++){
            for(int x = 0; x < Engine.tiles.length;x++){
                g.setColor(Engine.tiles[y][x].get_Background());
                g.fillRect(Engine.tiles[y][x].get_X() * GUI.square_size,Engine.tiles[y][x].get_Y() * GUI.square_size,GUI.square_size,GUI.square_size);
            }
        }

        //Drawing Chess Pieces
        for(int i = 0; i < Engine.teams.length;i++){
            for(ChessPieceBase piece : Engine.teams[i].get_Chess_Pieces())
                piece.draw_Piece(g,this);
        }



    }

}
