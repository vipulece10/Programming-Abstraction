package geometry;

import java.util.Comparator;

public class Counterclockwise implements Comparator <Point > {
    private Point center ;
    private Point[] vertices;

    public Counterclockwise(Point []vertices){
        this.vertices=vertices;
        this.center=new Point((vertices[0].getX()+vertices[1].getX()+vertices[2].getX()+vertices[3].getX())/4,
                (vertices[0].getY()+vertices[1].getY()+vertices[2].getY()+vertices[3].getY())/4);
    }

    @Override
    public int compare(Point p1, Point p2) {
        double angle1 = (Math.toDegrees(Math.atan2(p1.getX() - center.getX(),p1.getY() - center.getY()))-270)%360;
        double angle2 = (Math.toDegrees(Math.atan2(p2.getX() - center.getX(),p2.getY() - center.getY()))-270)%360;

        return (int)(angle2 - angle1);

    }
}
