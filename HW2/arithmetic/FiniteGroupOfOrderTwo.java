package arithmetic;
//import arithmetic.*;
/**
 * The implementation of the set of integers as a <code>Group</code>, with addition as the binary operation.
 *
 * @author Vipul Tiwari
 */

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {
    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne x, PlusOrMinusOne y) {
        return new PlusOrMinusOne(x.getValue() * y.getValue());
    }
    
    @Override
    public PlusOrMinusOne identity() {
        return new PlusOrMinusOne(1);
    }
    
    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne x) {
        return new PlusOrMinusOne(x.getValue());
    }
    
    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne x, int k) {
        if(k<0)
            k=k*-1;
        return k == 0 ? identity() : binaryOperation(x, exponent(x, k - 1));
    }
}
