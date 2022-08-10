package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class implementing squares.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author {Vipul Tiwari}
 */

public class Square implements Shape {
    private List <Point> SquareVertices;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the square. If more than four
     * points are provided, only the first four are considered by this constructor. If less than four points are
     * provided, or if the points do not form a valid square, the constructor throws <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public Square(Point... vertices) {
        // TODO
        if (vertices.length < 4) {
            throw new IllegalArgumentException("Not enough vertices");
        }
        else {

            this.SquareVertices=new ArrayList<>();
            //Rounding off to three decimal places
            Point [] vertices_rounded =new Point[vertices.length];
            for(int i=0;i<vertices.length;i++){
                vertices_rounded[i]=new Point(Math.round(vertices[i].getX()*1000.0)/1000.0,Math.round(vertices[i].getY()*1000.0)/1000.0);
              //  vertices_rounded[i]=new Point(vertices[i].getX(),vertices[i].getY());
            }
            SquareVertices=rotateCounterclockwise(vertices_rounded);

        }
        if (!isMember(SquareVertices)) {
            throw new IllegalArgumentException("Not a valid Square");
        }
    }

   public Square(boolean flag,Point... vertices) {
        // TODO
        if (vertices.length < 4) {
            throw new IllegalArgumentException("Not enough vertices");
        }
        else {

            this.SquareVertices=new ArrayList<>();
            //Rounding off to three decimal places
            Point [] vertices_rounded =new Point[vertices.length];
            for(int i=0;i<vertices.length;i++){
                vertices_rounded[i]=new Point(Math.round(vertices[i].getX()*1000.0)/1000.0,Math.round(vertices[i].getY()*1000.0)/1000.0);
             //   vertices_rounded[i]=new Point(vertices[i].getX(),vertices[i].getY());
            }

            SquareVertices.add(vertices_rounded[0]);
            SquareVertices.add(vertices_rounded[1]);
            SquareVertices.add(vertices_rounded[2]);
            SquareVertices.add(vertices_rounded[3]);



            List <Point> tempVertices=new ArrayList<>();
            for(int i=0;i<SquareVertices.size();i++){
                tempVertices.add(SquareVertices.get(i));
            }

          Collections.sort(tempVertices,(a,b)->  ((a.getX()==b.getX()) ? Double.compare(a.getY(),b.getY()) : Double.compare(a.getX(),b.getX())));

            while(SquareVertices.get(0).getX()!=tempVertices.get(0).getX() && SquareVertices.get(0).getY()!=tempVertices.get(0).getY()){
                      Collections.rotate(SquareVertices, 1);
            }

        }
        if (!isMember(SquareVertices)) {
            throw new IllegalArgumentException("Not a valid Square");
        }
    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid square if the first four form the vertices of
     * the square. This method considers the points in a counterclockwise manner starting with the vertex with the least
     * x-value. If multiple vertices have the same x-value, the counterclockwise ordering starts at the vertex with the
     * least y-value amongst them.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first four vertices in the argument form a valid square, and <code>false</code>
     * otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {

        double side1, side2, side3 ,side4;
        side1=calculate_distance(vertices.get(0), vertices.get(1));
        side2=calculate_distance(vertices.get(0), vertices.get(3));
        side3=calculate_distance(vertices.get(1), vertices.get(2));
        side4=calculate_distance(vertices.get(2), vertices.get(3));
       // System.out.println(side1 +" "+side2+" "+side3+" "+side4);
        if (side1==side2 && side1==side3 && side1 == side4 && side2==side3 && side2==side4 && side3==side4) {
            return true;
        }
        return false;
    }
    
    @Override
    public int numberOfSides() {
        return 4;
    }
    
    @Override
    public List<Point> vertices() {
        // TODO
        return SquareVertices;
    }
    
    @Override
    public Square rotateBy(int degrees) {
        // TODO
        Point p1=SquareVertices.get(0);
        Point p2=SquareVertices.get(1);
        Point p3=SquareVertices.get(2);
        Point p4=SquareVertices.get(3);
        System.out.println("In rotate by");
        System.out.println(p1.getX()+", "+p1.getY());
        System.out.println(p2.getX()+", "+p2.getY());
        System.out.println(p3.getX()+", "+p3.getY());
        System.out.println(p4.getX()+", "+p4.getY());
        double angle=Math.toRadians(degrees);
        Point center=new Point((p1.getX()+p2.getX()+p3.getX()+p4.getX())/4,(p1.getY()+p2.getY()+p3.getY()+p4.getY())/4);
        Point a=new Point((p1.getX()-center.getX())*Math.cos(angle)-(p1.getY()-center.getY())*Math.sin(angle),
                (p1.getX()-center.getX())*Math.sin(angle)+(p1.getY()-center.getY())*Math.cos(angle));

        Point b=new Point((p2.getX()-center.getX())*Math.cos(angle)-(p2.getY()-center.getY())*Math.sin(angle),
                (p2.getX()-center.getX())*Math.sin(angle)+(p2.getY()-center.getY())*Math.cos(angle));

        Point c=new Point((p3.getX()-center.getX())*Math.cos(angle)-(p3.getY()-center.getY())*Math.sin(angle),
                (p3.getX()-center.getX())*Math.sin(angle)+(p3.getY()-center.getY())*Math.cos(angle));

        Point d=new Point((p4.getX()-center.getX())*Math.cos(angle)-(p4.getY()-center.getY())*Math.sin(angle),
                (p4.getX()-center.getX())*Math.sin(angle)+(p4.getY()-center.getY())*Math.cos(angle));

       /* Point a_changed=new Point(a.getX()+center.getX(),a.getY()+center.getY());
        Point b_changed=new Point(b.getX()+center.getX(),b.getY()+center.getY());
        Point c_changed=new Point(c.getX()+center.getX(),c.getY()+center.getY());
        Point d_changed=new Point(d.getX()+center.getX(),d.getY()+center.getY()); */

        Point a_changed=new Point(Math.round((a.getX()+center.getX())*1000.0)/1000.0,Math.round((a.getY()+center.getY())*1000.0)/1000.0);
        Point b_changed=new Point(Math.round((b.getX()+center.getX())*1000.0)/1000.0,Math.round((b.getY()+center.getY())*1000.0)/1000.0);
        Point c_changed=new Point(Math.round((c.getX()+center.getX())*1000.0)/1000.0,Math.round((c.getY()+center.getY())*1000.0)/1000.0);
        Point d_changed=new Point(Math.round((d.getX()+center.getX())*1000.0)/1000.0,Math.round((d.getY()+center.getY())*1000.0)/1000.0);

        System.out.println("After rotation");
        System.out.println(a_changed.getX() + " "+a_changed.getY());
        System.out.println(b_changed.getX() + " "+b_changed.getY());
        System.out.println(c_changed.getX() + " "+c_changed.getY());
        System.out.println(d_changed.getX() + " "+d_changed.getY());
        return new Square(false,a_changed,b_changed,c_changed,d_changed);
    }
    
    @Override
    public String toString() {
       /* return "Square : " +  "(" + SquareVertices.get(0).getX() +", "+SquareVertices.get(0).getY() + ")" + ", " +
                "(" + SquareVertices.get(1).getX() +", "+ SquareVertices.get(1).getY() + ")" + ", " +
                "(" + SquareVertices.get(2).getX() +", "+ SquareVertices.get(2).getY() + ")" + ", " +
                "(" + SquareVertices.get(3).getX() +", "+ SquareVertices.get(3).getY() + ")"; */

        return "Square : " +  "(" + Math.round(SquareVertices.get(0).getX()*1000.0)/1000.0 +", "+Math.round(SquareVertices.get(0).getY()*1000.0)/1000.0 + ")" + ", " +
                "(" + Math.round(SquareVertices.get(1).getX()*1000.0)/1000.0 +", "+ Math.round(SquareVertices.get(1).getY()*1000.0)/1000.0 + ")" + ", " +
                "(" + Math.round(SquareVertices.get(2).getX()*1000.0)/1000.0 +", "+ Math.round(SquareVertices.get(2).getY()*1000.0)/1000.0 + ")" + ", " +
                "(" + Math.round(SquareVertices.get(3).getX()*1000.0)/1000.0 +", "+ Math.round(SquareVertices.get(3).getY()*1000.0)/1000.0 + ")";


    }

    private float calculate_distance(Point p1, Point p2) {
        return (float) (Math.round(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2))*1000.0)/1000.0);
       // return distance;
    }

    private List<Point> rotateCounterclockwise(Point... vertices) {
        System.out.println(vertices[0].getX()+", "+vertices[0].getY());
        System.out.println(vertices[1].getX()+", "+vertices[1].getY());
        System.out.println(vertices[2].getX()+", "+vertices[2].getY());
        System.out.println(vertices[3].getX()+", "+vertices[3].getY());
        Arrays.sort(vertices, new Counterclockwise(vertices));

        System.out.println("After rotation counter clockwise");
        System.out.println(vertices[0].getX()+", "+vertices[0].getY());
        System.out.println(vertices[1].getX()+", "+vertices[1].getY());
        System.out.println(vertices[2].getX()+", "+vertices[2].getY());
        System.out.println(vertices[3].getX()+", "+vertices[3].getY());
        return Arrays.asList(vertices);
    }
}
