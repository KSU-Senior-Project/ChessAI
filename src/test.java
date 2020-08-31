import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test(){
        super();
        setSize(new Dimension(500,500));
        Image i = null;
        try{
            i = ImageIO.read(getClass().getResource("./resources/Chess.png"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
        this.setVisible(true);
        this.setIconImage(i);
    }

    public static void main(String[] args){
        new test();
    }

}
