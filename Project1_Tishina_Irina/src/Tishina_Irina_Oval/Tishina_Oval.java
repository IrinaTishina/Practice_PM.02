package Tishina_Irina_Oval;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
/**
 *
 * @author Ира
 */
public class Tishina_Oval extends JFrame{
   private static  Tishina_Oval oval;

    public static void main(String[] args) {
        oval = new Tishina_Oval();
        oval.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        oval.setLocation(200, 50);
        oval.setSize(900, 600);
        oval.setResizable(false);
        OvalField oval_field = new OvalField();
        oval.add(oval_field);
        oval.setVisible(true);
    }

    public static void onRepaint(Graphics g){
        g.fillOval(10, 10, 200, 100);
    }

    public static class OvalField extends JPanel{

        @Override
        protected void paintComponent(Graphics g){

            super.paintComponent(g);
                onRepaint(g);
        }
    }

}    

