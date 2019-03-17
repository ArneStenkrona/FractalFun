package view;

/**
 * Created by Arne Stenkrona on 03/16/19.
 */

import fractal.Fractal;
import input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Viewport for fractals **/
public class Viewport extends JFrame {

    //private int width;
    //private int height;
    //static double aspectRatio;
    private Screen screen;
     /** Attributes specifying the domain to be drawn **/
    double domainReal; // real part of the lower left part of the domain
    double domainImag; // imaginary part of the lower left part of the domain
    double domainWidth; // width of the domain, i.e. range of real part

    private Fractal fractal;

    public Viewport(int width, int height, Fractal fractal) {
        //this.width = width;
        //this.height = height;
        //this.aspectRatio = width / height;
        setSize(width,height);
        setResizable(false);

        this.domainReal = -2;
        this.domainImag = -1;
        this.domainWidth = 4;

        this.fractal = fractal;

        //setTitle("Mandelbrot");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        MouseInput mInput = new MouseInput(this);
        screen.addMouseListener(mInput);
        screen.addMouseMotionListener(mInput);
    }

    /**
     * Initialize the viewport
     * **/
    private void init() {
        setLocation(0,0);
        setLayout(new GridLayout(1,1,0,0));

        screen = new Screen(getWidth(),getHeight());
        add(screen);

        setVisible(true);
    }

    /**
     * Draw fractal as specified by domain members
     * **/
    public void drawFractal() {
        BufferedImage img = fractal.draw(domainReal, domainImag, domainWidth, screen.getWidth(), screen.getHeight());
        screen.setImageBuffer(img);
        screen.draw();
    }

    public void drawSelection(int x1, int y1, int x2, int y2){

        int w = Math.abs(x2 - x1);
        int h = w * screen.getHeight() / screen.getWidth();

        int x = x1 < x2 ? x1 : x1 - w;
        int y =  y1 < y2 ? y1 : y1 - h;

        screen.drawWithSelection(x, y, w, h);
    }

    /**
     * Changes the domain of the fractal to be drawn by the viewport
     * according to a bounding box determined by points (x1,y1) and (x2,y2)
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * **/
    public void zoom(int x1, int y1, int x2, int y2) {
        // compensate for screen coordinates being inverted
        y1 = screen.getHeight() - y1;
        y2 = screen.getHeight() - y2;

        double w = Math.abs(x2 - x1) / (float)screen.getWidth() * domainWidth;
        double h = w * screen.getHeight() / screen.getWidth();


        double x = x1 < x2 ? domainReal + (x1  / (float)screen.getWidth()) * domainWidth :
                             domainReal + (x1  / (float)screen.getWidth()) * domainWidth - w;
        double y = y1 < y2 ? domainImag + (y1 / (float)screen.getHeight()) * getDomainHeight() :
                             domainImag + (y1 / (float)screen.getHeight()) * getDomainHeight() - h;

        // change domain
        domainReal = x;
        domainImag = y;
        domainWidth = w;
    }
    /**
     * gets the domain height, as determined by domainWidth
     * and aspect ratio
     * @return height of the domain, i.e. range of the imaginary component
     * */
    public double getDomainHeight(){
        return domainWidth * screen.getHeight() / screen.getWidth();
    }
}
