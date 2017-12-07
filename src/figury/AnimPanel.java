package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;

    private int delay = 10;

    private Timer timer;

    private static int numer = 0;
    private static List<Figura> figureList = new ArrayList<>();


    public AnimPanel() {
        super();
        timer = new Timer(delay, this);
        setOpaque(false);
    }

    public void initialize() {
        int width = getWidth();
        int height = getHeight();
        System.out.println(width + " " + height);

        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setBackground(Color.WHITE);
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    void addFig() {
        Figura fig = (numer++ % 2 == 0) ? new Kwadrat(buffer, delay, getWidth(), getHeight())
                : new Elipsa(buffer, delay, getWidth(), getHeight());
        timer.addActionListener(fig);
        figureList.add(fig);
        new Thread(fig).start();
    }

    void animate() {/*
        for(Figura figure: figureList) {
            figure.changeAnimationState();
        }*/
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    void changeSize() {
        image = createImage(getWidth(), getHeight());
        buffer = (Graphics2D) image.getGraphics();
        device = (Graphics2D) getGraphics();
        numer = 0;
        figureList = new ArrayList<>();
        System.out.println(getWidth() + " " + getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
}
