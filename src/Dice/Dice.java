package Dice;

import java.awt.*;

public class Dice {
    public int number;
    public Image image;

    public Dice(int number,Image image){
        setImage(image);
        setNumber(number);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
