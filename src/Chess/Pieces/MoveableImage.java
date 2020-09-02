package Chess.Pieces;

import java.awt.*;
import java.awt.image.ImageObserver;

public class MoveableImage {
    private Image image;
    private int x;
    private int y;

    private int middle_x;
    private int middle_y;

    public MoveableImage(Image image, int x, int y){
        setImage(image);
        setX(x);
        setY(y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw_Image(Graphics g, ImageObserver observer){
        g.drawImage(getImage(),getX(),getY(),observer);
    }

    public int getMiddle_x() {
        return middle_x;
    }

    public void setMiddle_x(int middle_x) {
        this.middle_x = middle_x;
    }

    public int getMiddle_y() {
        return middle_y;
    }

    public void setMiddle_y(int middle_y) {
        this.middle_y = middle_y;
    }

    public void setMidpoint(int middle_x,int middle_y){
        this.middle_x = middle_x;
        this.middle_y = middle_y;
        this.x = this.middle_x - (this.image.getWidth(null) / 2);
        this.y = this.middle_y - (this.image.getHeight(null) / 2);
    }
}
