package figures;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    public Ellipse(int animationDelay) {
        super(animationDelay);

        shape = new Ellipse2D.Float(0, 0, 10, 10);
        affineTransformation = new AffineTransform();
        shapeArea = new Area(shape);
    }
}
