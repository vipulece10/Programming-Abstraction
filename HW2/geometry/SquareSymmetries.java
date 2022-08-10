package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareSymmetries implements Symmetries<Square>{
    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        for(Square t :symmetriesOf(s1)){
            List<Point> square1=t.vertices();
            List <Point> square2=s2.vertices();
            if(square1.get(0).getX()==square2.get(0).getX() && square1.get(0).getY()==square2.get(0).getY() &&
                    square1.get(1).getX()==square2.get(1).getX() && square1.get(1).getY()==square2.get(1).getY() &&
                    square1.get(2).getX()==square2.get(2).getX() && square1.get(2).getY()==square2.get(2).getY() &&
                    square1.get(2).getX()==square2.get(2).getX() && square1.get(2).getY()==square2.get(2).getY()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Square> symmetriesOf(Square s) {
        List <Point> coordinates = s.vertices();
        Point p1 = coordinates.get(0);
        Point p2 = coordinates.get(1);
        Point p3 = coordinates.get(2);
        Point p4= coordinates.get(3);
        List <Square> symmetries = new ArrayList<>();
        symmetries.add(s.rotateBy(0));
        symmetries.add(s.rotateBy(90));
        symmetries.add(s.rotateBy(180));
        symmetries.add(s.rotateBy(270));
        symmetries.add(new Square(p4,p3,p2,p1));
        symmetries.add(new Square(p2,p1,p4,p3));
        symmetries.add(new Square(p1,p4,p3,p2));
        symmetries.add(new Square(p3,p2,p1,p4));
        return symmetries;
    }
}
