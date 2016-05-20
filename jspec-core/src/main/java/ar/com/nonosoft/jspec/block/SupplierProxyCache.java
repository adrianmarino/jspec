package ar.com.nonosoft.jspec.block;

import java.util.function.Supplier;

public class SupplierProxyCache<T> implements Supplier<T> {

    @Override
    public T get() {
        return response == null ? response = block.get() : response;
    }

    public void evict() {
        response = null;
    }

    private T response;

    private Supplier<T> block;

    public SupplierProxyCache(Supplier<T> block) {
        this.block = block;
    }
}
