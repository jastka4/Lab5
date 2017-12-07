package figures;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figure {
    public Rectangle(int animationDelay) {
        super(animationDelay);

        shape = new Rectangle2D.Float(0, 0, 10, 10);
        affineTransformation = new AffineTransform();
        shapeArea = new Area(shape);
    }
}
