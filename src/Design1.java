import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Design1 extends Frame {
    MyCanvas canvas = new MyCanvas();
    Design1(){
        super("My First Design");
        this.setSize(400,400);
        canvas.setSize(this.getSize());
        canvas.setBackground(Color.BLACK);
        this.add(canvas);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
    public class MyCanvas extends Canvas{
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            g2.setBackground(Color.BLACK);
            g2.setColor(Color.WHITE);
            g2.fillRect(5,5,10,10);
        }
    }
}
