package Chess.Tile;

import Chess.Pieces.BasePiece;

import java.awt.*;

public class Tile {
    //Absolute x and y value for Tile Number for drawing purposes
    private int absolute_x;
    private int absolute_y;


    //Relative Tile number from (0,0) to (8,8)
    private int relative_x;
    private int relative_y;

    private int size;
    private Color Background;

    private BasePiece current_piece;

    public Tile(int relative_x, int relative_y, int size, Color Background){
        setSize(size);
        setRelative_x(relative_x);
        setRelative_y(relative_y);
        setBackground(Background);
    }

    public String getName(){
        return "" + (char)('A' + getRelative_y()) + (getRelative_x() + 1);
    }
    public int getRelative_x() {
        return relative_x;
    }

    public void setRelative_x(int x) {
        setAbsolute_x(x * this.getSize());
        this.relative_x = x;
    }

    public int getRelative_y() {
        return relative_y;
    }

    public void setRelative_y(int y) {
        setAbsolute_y(y * this.getSize());
        this.relative_y = y;
    }

    public int getAbsolute_x() {
        return absolute_x;
    }

    public void setAbsolute_x(int x) {
        this.absolute_x = x;
    }

    public int getAbsolute_y() {
        return absolute_y;
    }

    public void setAbsolute_y(int y) {
        this.absolute_y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getBackground() {
        return Background;
    }

    public void setBackground(Color background) {
        Background = background;
    }

    public BasePiece getCurrent_piece() {
        return current_piece;
    }

    public void setCurrent_piece(BasePiece current_piece) {
        this.current_piece = current_piece;
    }
}
