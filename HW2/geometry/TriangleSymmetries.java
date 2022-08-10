package geometry;

import java.util.*;

public class TriangleSymmetries implements Symmetries<EqTriangle> {

    @Override
    public boolean areSymmetric(EqTriangle s1, EqTriangle s2) {
        for(EqTriangle t :symmetriesOf(s1)){
            List <Point> triangle1=t.vertices();
            List <Point> triangle2=s2.vertices();
            if(triangle1.get(0).getX()==triangle2.get(0).getX() && triangle1.get(0).getY()==triangle2.get(0).getY() &&
                    triangle1.get(1).getX()==triangle2.get(1).getX() && triangle1.get(1).getY()==triangle2.get(1).getY() &&
                    triangle1.get(2).getX()==triangle2.get(2).getX() && triangle1.get(2).getY()==triangle2.get(2).getY()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<EqTriangle> symmetriesOf(EqTriangle s) {
        List <Point> coordinates = s.vertices();
        Point p1 = coordinates.get(0);
        Point p2 = coordinates.get(1);
        Point p3 = coordinates.get(2);
        List <EqTriangle> symmetries = new ArrayList<>();
        symmetries.add(s.rotateBy(0));
        symmetries.add(s.rotateBy(120));
        symmetries.add(s.rotateBy(240));
        symmetries.add(new EqTriangle(p2,p1,p3));
        symmetries.add(new EqTriangle(p1,p3,p2));
        symmetries.add(new EqTriangle(p3,p2,p1));
        return symmetries;
    }
}
