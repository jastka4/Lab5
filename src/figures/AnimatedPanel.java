package figures;

import sun.security.provider.SHA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class AnimatedPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final int animationDelay = 10;

    private static int numberOfFigures = 0;
    private static List<Figure> figureList = new ArrayList<>();

    public static Timer timer;

    public AnimatedPanel()
    {
        super();
        timer = new Timer(animationDelay, this);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                Figure.updatePanelSize(getWidth(), getHeight());
                System.out.println("Resized");
            }
        });
    }

    public void paintComponent(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        for(Figure figure: figureList)
        {
            figure.draw(graphics);
        }
    }

    public void addFigure()
    {
        Figure figure = (numberOfFigures++ % 2 == 0) ? new Rectangle(animationDelay) :
                new Ellipse(animationDelay);
        figureList.add(figure);
        Thread thread = new Thread(figure);
        thread.start();
    }

    void animate() {
        Figure.changeAnimationState();
        if (timer.isRunning())
        {
            timer.stop();
            System.out.println("Stopped!");
        } else {
            timer.start();
            for(Figure figure: figureList)
            {
                figure.resumeAnimation();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}