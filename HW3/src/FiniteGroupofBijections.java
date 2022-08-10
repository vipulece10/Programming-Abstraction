import java.util.*;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FiniteGroupofBijections <T> implements Group<Function<T,T>>{

    private Set<Integer> set=new HashSet<>();

    public FiniteGroupofBijections(Set<Integer> set){
        this.set=set;
    }

    @Override
    public Function<T,T> binaryOperation(Function<T,T> one, Function<T,T> other) {
        return one.andThen(after -> other.apply((T) after));
    }

    @Override
    public Function<T, T> identity() {
        return (T t) -> (T) t;
    }

    @Override
    public   Function<T,T> inverseOf(Function< T,T> t) {
        List <T> domain = set.stream().map(x -> (T) x).collect(Collectors.toList());
        List<T> range=set.stream().map(x->t.apply((T)x)).collect(Collectors.toList());
        List <T> temp=range;
        ArrayList<Function<T,T>> functions=new ArrayList<>( domain.stream().map(x -> (Function<T,T>) a -> domain.get(temp.indexOf(a))).collect(Collectors.toList()));
        return functions.get(0);
    }


    @Override
    public   Function<T,T> exponent(Function< T,T> t, int k) {
        if (k < 0)
            throw new IllegalArgumentException("The exponent must be a non-negative integer value.");
        return k == 0 ? identity() : binaryOperation(t, exponent(t, k - 1));
    }
}
