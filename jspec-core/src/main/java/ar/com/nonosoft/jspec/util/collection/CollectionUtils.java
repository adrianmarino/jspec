package ar.com.nonosoft.jspec.util.collection;

import com.google.common.collect.ImmutableMap;

import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <K, V> Stream<Entry<K, V>> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableMap.of(k1, v1, k2, v2, k3, v3, k4, v4).entrySet().stream();
    }

    public static <NODE> void lookUp(NODE rootNode, Consumer<NODE> execute, Function<NODE, NODE> nextNode) {
        NODE node = rootNode;
        while (node != null) {
            execute.accept(node);
            node = nextNode.apply(node);
        }
    }

    public static <RESULT, NODE, EXCEPTION extends Throwable> RESULT lookUpAndGet(NODE rootNode, Function<NODE, RESULT> getResult, Function<NODE, NODE> nextNode, Class<RESULT> resultClass, EXCEPTION missingException) throws EXCEPTION {
        NODE node = rootNode;
        RESULT result = null;
        while (node != null && result == null) {
            result = getResult.apply(node);
            node = nextNode.apply(node);
        }
        if (result == null) throw missingException;
        return result;
    }

    private CollectionUtils() {
    }
}
