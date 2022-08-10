import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

public class BijectionGroup {

    private static<T> ArrayList<ArrayList<T>> generatePermutations(ArrayList<T> lst, ArrayList<ArrayList<T>> permutations, int n, int start){
        if(start==n){
            permutations.add(new ArrayList<>(lst));
        }
        for(int i=start; i<n; i++){
            Collections.swap(lst,start,i);
            generatePermutations(lst,permutations,n,start+1);
            Collections.swap(lst,start,i);
        }
        return permutations;
    }

    public static<T> Set<Function<T, T>> bijectionsOf(Set<T> domain){
        ArrayList<T> temp = new ArrayList<>(domain);
        return generatePermutations(temp, new ArrayList<>(), domain.size(), 0)
                .stream()
                .map(list -> (Function<T, T>) t -> list.get(temp.indexOf(t)))
                .collect(Collectors.toSet());
    }
    public static  void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        /*Set<Function<Integer,Integer>> bijections=bijectionsOf(a_few);

        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });*/


        // you have to figure out the data types in the lines below
        
        FiniteGroupofBijections <Integer> g = bijectionGroup(a_few);
        Function<Integer,Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        Function<Integer,Integer> f2 = g.inverseOf(f1);
        Function<Integer,Integer> id = g.identity();

    }

    private static <T> FiniteGroupofBijections<T> bijectionGroup(Set<Integer> a_few) {
        return new FiniteGroupofBijections<>(a_few);
    }

}
