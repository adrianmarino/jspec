package ar.com.nonosoft.jspec.util.collection;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class Iterator<E> {

    public <A> A eachWithObject(A initialAcc, BiConsumer<A, E> block) {
        A acc = initialAcc;
        for (E element : collection) block.accept(acc, element);
        return acc;
    }

    public <A> A inject(A initialAcc, Inject<A, E> block) {
        A acc = initialAcc;
        for (E element : collection) acc = block.eval(acc, element);
        return acc;
    }

    public Stream<E> reject(Predicate<? super E> predicate) {
        return collection.stream().filter((e) -> !predicate.test(e));
    }

    private Collection<E> collection;

    public Iterator(Collection<E> collection) {
        this.collection = collection;
    }

    public static <E> Iterator<E> iterate(Collection<E> collection) {
        return new Iterator(collection);
    }

    public static <K, V> Iterator<Map.Entry<K, V>> iterate(Map<K, V> map) {
        return new Iterator(map.entrySet());
    }
}