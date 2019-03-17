package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Screen extends JPanel{

    private BufferedImage image;

    private boolean selection;
    private int x, y, w, h;

    public Screen(int w, int h) {
        image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        selection = false;
        repaint();
    }

    public void setImageBuffer(BufferedImage img) {
        image = img;
    }

    public void draw() {
        selection = false;
        repaint();
    }

    public void drawWithSelection(int x, int y, int w, int h){
        selection = true;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(image,0,0,image.getWidth(), image.getHeight(), null);
        if (selection) {
            g.drawRect(x, y, w, h);
        }
    }
}
