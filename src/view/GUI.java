package view;

import fractal.Julia;
import fractal.Mandelbrot;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    Viewport viewport;

    public GUI(int screenW, int screenH){

        setSize(screenW, screenH);
        //setResizable(false);
        setTitle("Fractal Fun");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(0,0);
        GridBagLayout layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        setVisible(true);

        addViewport(screenW, screenH);
        addControlPanel();

        pack();

        draw();
    }

    private void addViewport(int w, int h) {
        viewport = new Viewport(w, h, new Mandelbrot());
        getContentPane().add(viewport);

    }

    private void addControlPanel(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;

        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(getWidth(), 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        add(panel, c);
        pack();
        // fractal buttons
        JLabel bl = new JLabel(" Fractal: ");
        panel.add(bl);
        JButton mb = new JButton("Mandelbrot");
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewport.setFractal(new Mandelbrot());
                viewport.resetDomain();
                viewport.drawFractal();
            }
            });

        JButton mj = new JButton("Julia");
        mj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewport.setFractal(new Julia(-0.7269, 0.1889));
                viewport.resetDomain();
                viewport.drawFractal();
            }
        });
        panel.add(mb);
        panel.add(mj);
        // iteration count
        JLabel il = new JLabel(" Iterations: ");
        panel.add(il);
        SpinnerModel model = new SpinnerNumberModel(100, 1, 10000, 1);
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(spinner.getPreferredSize());
        spinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                viewport.setMaxIterations((Integer) spinner.getValue());
                viewport.drawFractal();
            }
        });
        panel.add(spinner);
    }

    public void draw(){
        viewport.drawFractal();
    }
}
