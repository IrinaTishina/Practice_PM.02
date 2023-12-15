package Tishina_Irina_Animation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/* @author Тишина Ирина Андреевна */

public class Animation extends JFrame {

    private static Animation animation;
    private static long last_frame_time;
    private static Image Logotip;
    private static Image Snowflakes;
    private static Image Instrument;
    private static Image Lights;
    private static float drop_left = 100;
    private static float drop_top = -100;
    private static float drop_v = 100; 
    private static int score = 0; 

    public static void main(String[] args) throws IOException {
        Logotip = ImageIO.read(Animation.class.getResourceAsStream("Logotip.png"));
        Snowflakes = ImageIO.read(Animation.class.getResourceAsStream("Snowflakes.png"));
        Instrument = ImageIO.read(Animation.class.getResourceAsStream("Instrument.png"));
        Lights = ImageIO.read(Animation.class.getResourceAsStream("Lights.png"));
        animation = new Animation();
        animation.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        animation.setLocation(200, 50);
        animation.setSize(1000, 800);
        animation.setResizable(false);
        last_frame_time = System.nanoTime();
        AnimationField animation_field = new AnimationField();
        animation_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + Instrument.getWidth(null);
                float drop_bottom = drop_top + Instrument.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                
                if (is_drop) {
                    drop_top = -100;
                    drop_left = (int) (Math.random() * (animation_field.getWidth() - Instrument.getWidth(null)));
                    drop_v = drop_v + 10;
                    score++;
                    animation.setTitle("Score: " + score);
                } 
            }
            public void mousePressed1(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + Lights.getWidth(null);
                float drop_bottom = drop_top + Lights.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                
                if (is_drop) {
                    drop_top = -100;
                    drop_left = (int) (Math.random() * (animation_field.getWidth() - Lights.getWidth(null)));
                    drop_v = drop_v + 10;
                    score++;
                    animation.setTitle("Score: " + score);
                } 
            }
        });

        animation.add(animation_field);
        animation.setVisible(true);
    }

    private static void onRepaint(Graphics g) {
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top + drop_v * delta_time;
        g.drawImage(Logotip, 0, 0, null);
        g.drawImage(Snowflakes, (int) drop_left, (int) drop_top, null);
        if (drop_top > animation.getHeight()) g.drawImage(Instrument, 0, 200, null);
        if (drop_top > animation.getHeight()) g.drawImage(Lights, -80, -80, null);
    }

    private static class AnimationField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
