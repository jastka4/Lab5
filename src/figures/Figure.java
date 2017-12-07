/**
 *
 */
package figures;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * @author tb
 *
 */
public abstract class Figure implements Runnable
{

    protected Area shapeArea;
    protected Shape shape;
    protected AffineTransform affineTransformation;
    private int velocityX, velocityY;
    private double extendValue;
    private double spinAngle;
    private int animationDelay;
    private static int width;
    private static int height;
    private static boolean animationIsRunning = false;

    protected static final Random random = new Random();
    private final Color color;

    public Figure(int animationDelay)
    {
        this.animationDelay = animationDelay;

        velocityX = 1 + random.nextInt(5);
        velocityY = 1 + random.nextInt(5);
        extendValue = 1 + 0.05 * random.nextDouble();
        spinAngle = 0.1 * random.nextDouble();

        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    void draw(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color.brighter());
        graphics2D.fill(shape);
        graphics2D.setColor(color.darker());
        graphics2D.draw(shape);
    }

    @Override
    public void run() {
        affineTransformation.translate(width / 2, height / 2);
        shapeArea.transform(affineTransformation);
        shape = shapeArea;

        while (true) {
            shape = getToNextPosition();
            try {
                synchronized (this) {
                    if (animationIsRunning) {
                        Thread.sleep(animationDelay);
                        System.out.println("Thread running!");
                    } else {
                        System.out.println("Thread stopped!");
                        wait();
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }

    protected static void updatePanelSize(int width, int height)
    {
        Figure.width = width;
        Figure.height = height;

    }

    protected static void changeAnimationState()
    {
        animationIsRunning = !animationIsRunning;
    }

    protected synchronized void resumeAnimation(){
        notify();
    }

    protected Shape getToNextPosition()
    {
        shapeArea = new Area(shape);
        affineTransformation = new AffineTransform();
        Rectangle2D bounds = shapeArea.getBounds2D();
        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        if (centerX < 0 || centerX > width)
            velocityX = -velocityX;
        if (centerY < 0 || centerY > height)
            velocityY = -velocityY;
        // zwiekszenie lub zmniejszenie
        if (bounds.getHeight() > height / 3 || bounds.getHeight() < 10)
            extendValue = 1 / extendValue;
        // konstrukcja przeksztalcenia
        affineTransformation.translate(centerX, centerY);
        affineTransformation.scale(extendValue, extendValue);
        affineTransformation.rotate(spinAngle);
        affineTransformation.translate(-centerX, -centerY);
        affineTransformation.translate(velocityX, velocityY);
        // przeksztalcenie obiektu
        shapeArea.transform(affineTransformation);
        return shapeArea;
    }
}
