package view;

/**
 * Created by Arne Stenkrona on 03/16/19.
 */

import fractal.Fractal;
import input.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Viewport for fractals **/
public class Viewport extends Screen {

     /** Attributes specifying the domain to be drawn **/
    double domainReal; // real part of the lower left part of the domain
    double domainImag; // imaginary part of the lower left part of the domain
    double domainWidth; // width of the domain, i.e. range of real part

    private Fractal fractal;

        public Viewport(int width, int height, Fractal fractal) {
        super(width, height);

        setSize(width,height);
        setPreferredSize(new Dimension(width, height));

        this.domainReal = -2;
        this.domainImag = -1;
        this.domainWidth = 4;

        this.fractal = fractal;

        MouseInput mInput = new MouseInput(this);
        addMouseListener(mInput);
        addMouseMotionListener(mInput);
    }
    
    public void setFractal(Fractal f){
        this.fractal = f;
    }
    
    public void setMaxIterations(int i) {
        if (i <= 10000 && i > 0) {
            fractal.setMaxIterations(i);
        } else {
            System.err.println("Illegal iteration amount.");
        }
    }


    /**
     * Draw fractal as specified by domain members
     * **/
    public void drawFractal() {
        BufferedImage img = fractal.draw(domainReal, domainImag, domainWidth, getWidth(), getHeight());
        setImageBuffer(img);
        draw();
    }

    public void drawSelection(int x1, int y1, int x2, int y2){

        int w = Math.abs(x2 - x1);
        int h = w * getHeight() / getWidth();

        int x = x1 < x2 ? x1 : x1 - w;
        int y =  y1 < y2 ? y1 : y1 - h;

        drawWithSelection(x, y, w, h);
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
        y1 = getHeight() - y1;
        y2 = getHeight() - y2;

        double w = Math.abs(x2 - x1) / (float)getWidth() * domainWidth;
        double h = w * getHeight() / getWidth();


        double x = x1 < x2 ? domainReal + (x1  / (float)getWidth()) * domainWidth :
                             domainReal + (x1  / (float)getWidth()) * domainWidth - w;
        double y = y1 < y2 ? domainImag + (y1 / (float)getHeight()) * getDomainHeight() :
                             domainImag + (y1 / (float)getHeight()) * getDomainHeight() - h;

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
        return domainWidth * getHeight() / getWidth();
    }

    public void resetDomain() {
        this.domainReal = -2;
        this.domainImag = -1;
        this.domainWidth = 4;
    }
}
