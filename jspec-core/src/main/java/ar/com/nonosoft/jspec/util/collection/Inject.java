package ar.com.nonosoft.jspec.util.collection;

public interface Inject<A, E> {
    A eval(A acc, E element);
}