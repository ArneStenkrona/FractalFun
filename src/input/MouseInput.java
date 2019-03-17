package input;

import view.Viewport;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Arne Stenkrona on 03/16/19.
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    private int clickX;
    private int clickY;

    private Viewport viewport;

    boolean mouseHeld;

    public MouseInput(Viewport viewport) {
        this.viewport = viewport;
        this.mouseHeld = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
        mouseHeld = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int releaseX = e.getX();
        int releaseY = e.getY();

        viewport.zoom(clickX, clickY, releaseX, releaseY);
        viewport.drawFractal();
        mouseHeld = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseHeld) {
            viewport.drawSelection(clickX, clickY, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}