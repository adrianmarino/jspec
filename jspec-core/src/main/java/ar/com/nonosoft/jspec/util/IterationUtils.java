package ar.com.nonosoft.jspec.util;

import java.util.function.Consumer;
import java.util.function.Function;

public class IterationUtils {

    public static <NODE> void lookUp(NODE rootNode, Consumer<NODE> execute, Function<NODE, NODE> nextNode) {
        NODE node = rootNode;
        while (node != null) { execute.accept(node); node = nextNode.apply(node); }
    }

    public static <RESULT, NODE, EXCEPTION extends Throwable> RESULT lookUpAndGet(NODE rootNode, Function<NODE, RESULT> getResult, Function<NODE, NODE> nextNode,  Class<RESULT> resultClass, EXCEPTION missingException) throws EXCEPTION {
        NODE node = rootNode;
        RESULT result = null;
        while (node != null && result == null) { result = getResult.apply(node); node = nextNode.apply(node); }
        if(result == null) throw missingException;
        return result;
    }

    private IterationUtils() {}
}
