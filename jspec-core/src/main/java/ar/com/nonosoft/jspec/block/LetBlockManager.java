package ar.com.nonosoft.jspec.block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class LetBlockManager {

    public <T> void put(String name, T value) {
        put(name, ()-> value);
    }

    public <T> void put(String name, Supplier<T> supplier) {
        blocks.put(name, new SupplierProxyCache(supplier));
    }

    public <R> R block(String name) {
        return (R)blocks.get(name);
    }

    public void clean() {
        blocks.values().stream().forEach(SupplierProxyCache::evict);
    }

    private Map<String, SupplierProxyCache> blocks;

    public LetBlockManager() {
        blocks = new HashMap<>();
    }
}
