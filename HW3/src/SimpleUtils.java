import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;


public class SimpleUtils {
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start){
        return from_start ? items.stream().reduce((a,b)-> a.compareTo(b) < 0 ? a : a.compareTo(b) == 0 ? a : b  ).orElse(null) :
                items.stream().reduce((a, b) -> a.compareTo(b) < 0 ? a : a.compareTo(b) == 0 ? b : a).orElse(null);
    }

    public static <K, V> List<String> flatten(Map<K, V> aMap){
        return aMap.entrySet().stream().map(e -> e.getKey() + " -> " + e.getValue()).collect(Collectors.toList());
    }


}
