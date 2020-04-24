package com.github.dreamhead.moco.util;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;

public class Suppliers {
    public static <T> Supplier<T> memoize(final Supplier<T> delegate) {
        AtomicReference<T> value = new AtomicReference<>();
        return () -> {
            T val = value.get();
            if (val == null) {
                val = value.updateAndGet(cur -> cur == null ?
                        Objects.requireNonNull(delegate.get()) : cur);
            }
            return val;
        };
    }

    public static <T, R> Function<T, R> from(final Supplier<R> supplier) {
        return request -> supplier.get();
    }

    private Suppliers() {
    }
}
