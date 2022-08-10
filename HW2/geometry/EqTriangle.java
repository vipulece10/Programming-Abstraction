package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implementing equilateral triangles, i.e., triangles in which all three sides have the same length.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author {add your full name here}
 */
public class EqTriangle implements Shape {
    private List <Point> EqTriangleVertices ;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public EqTriangle(Point... vertices) {
        // TODO
        if (vertices.length < 3) {
            throw new IllegalArgumentException("Not enough vertices");
        }
        else {

            this.EqTriangleVertices=new ArrayList<>();
            //Rounding off to three decimal places
            Point [] roundedVertices =new Point[vertices.length];
            for(int i=0;i<vertices.length;i++){
                roundedVertices[i]=new Point(Math.round(vertices[i].getX()*1000.0)/1000.0,Math.round(vertices[i].getY()*1000.0)/1000.0);
                System.out.println(roundedVertices[i].getX() + ", "+roundedVertices[i].getY());
            }
            EqTriangleVertices.add(roundedVertices[0]);
            EqTriangleVertices.add(roundedVertices[1]);
            EqTriangleVertices.add(roundedVertices[2]);
        }
        if (!isMember(EqTriangleVertices)) {
            throw new IllegalArgumentException("Not a valid triangle");
        }
    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid equilateral triangle if first three form the
     * vertices of the triangle.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first three vertices in the argument form a valid equilateral triangle, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        // TODO
        double side1, side2, side3;
        side1=calculate_distance(vertices.get(0), vertices.get(1));
        side2=calculate_distance(vertices.get(1), vertices.get(2));
        side3=calculate_distance(vertices.get(0), vertices.get(2));
        System.out.println(side1 + " ,"+side2+ ","+side3);
        if (side1==side2 && side2==side3 && side1 == side3) {
            return true;
        }
        return false;

    }
    
    @Override
    public int numberOfSides() {
        return 3;
    }
    
    @Override
    public List<Point> vertices() {
         // TODO
        return EqTriangleVertices;
    }
    
    @Override
    public EqTriangle rotateBy(int degrees) {
         // TODO
        Point p1=EqTriangleVertices.get(0);
        Point p2=EqTriangleVertices.get(1);
        Point p3=EqTriangleVertices.get(2);
        double angle=Math.toRadians(degrees);
        Point center=new Point((p1.getX()+ p2.getX()+p3.getX())/3,(p1.getY()+ p2.getY()+p3.getY())/3);
        Point x=new Point((p1.getX()-center.getX())*Math.cos(angle)-(p1.getY()-center.getY())*Math.sin(angle),
                (p1.getX()-center.getX())*Math.sin(angle)+(p1.getY()-center.getY())*Math.cos(angle));

        Point y=new Point((p2.getX()-center.getX())*Math.cos(angle)-(p2.getY()-center.getY())*Math.sin(angle),
                (p2.getX()-center.getX())*Math.sin(angle)+(p2.getY()-center.getY())*Math.cos(angle));

        Point z=new Point((p3.getX()-center.getX())*Math.cos(angle)-(p3.getY()-center.getY())*Math.sin(angle),
                (p3.getX()-center.getX())*Math.sin(angle)+(p3.getY()-center.getY())*Math.cos(angle));

        Point x_changed=new Point(Math.round((x.getX() + center.getX())*1000.0)/1000.0,Math.round((x.getY() + center.getY())*1000.0)/1000.0);
        Point y_changed=new Point(Math.round((y.getX() + center.getX())*1000.0)/1000.0,Math.round((y.getY() + center.getY())*1000.0)/1000.0);
        Point z_changed=new Point(Math.round((z.getX() + center.getX())*1000.0)/1000.0,Math.round((z.getY() + center.getY())*1000.0)/1000.0);

        return new EqTriangle(x_changed,y_changed,z_changed);

    }

    private float calculate_distance(Point p1, Point p2) {

        return (float) (Math.round(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2))*1000.0)/1000.0);

    }

    public String toString() {

      //  return "EqTriangle : " +  "(" + Math.round(EqTriangleVertices.get(0).getX()*1000.0)/1000.0 +", "+ Math.round(EqTriangleVertices.get(0).getY()*1000.0)/1000.0 + ")" + ", " + "(" + Math.round(EqTriangleVertices.get(1).getX()*1000.0)/1000.0 +", "+ Math.round(EqTriangleVertices.get(1).getY()) + ")" + ", " +
       //         "(" + Math.round(EqTriangleVertices.get(2).getX()*1000.0)/1000.0 +", "+ Math.round(EqTriangleVertices.get(2).getY()*1000.0)/1000.0 + ")";

        return "EqTriangle : " +  "(" + EqTriangleVertices.get(0).getX() +", "+ EqTriangleVertices.get(0).getY() + ")" + ", " + "(" + EqTriangleVertices.get(1).getX() +", "+ EqTriangleVertices.get(1).getY() + ")" + ", " +
                "(" + EqTriangleVertices.get(2).getX() +", "+ EqTriangleVertices.get(2).getY() + ")";
    }
}
