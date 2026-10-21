package ru.centraluniversity.workload;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Кэш на слабых/мягких ссылках: периодически создаются ключи, значения
 * удерживаются WeakReference/SoftReference, сборщик вычищает записи, чьи ключи
 * стали недостижимыми.
 * <p>
 * TODO: реализовать {@link #run()}. Тема ссылок: WeakReference/SoftReference/
 * PhantomReference, ReferenceQueue. Результат итерации обязан быть потреблён
 * (поле {@code sink}).
 */
public final class ReferenceHeavyWorkload implements GcWorkload {

    private static final int ENTRIES_PER_ITERATION = 10_000;
    private static final int VALUE_SIZE = 256;

    private final Map<Object, SoftReference<byte[]>> cache = new WeakHashMap<>();
    private Object sink;

    @Override
    public String name() {
        return "referenceHeavy";
    }

    @Override
    public void run() {
        for (int i = 0; i < ENTRIES_PER_ITERATION; i++) {
            Object key = new Object();
            byte[] value = new byte[VALUE_SIZE];
            value[0] = (byte) i;
            cache.put(key, new SoftReference<>(value));
        }

        sink = cache;
    }
}
