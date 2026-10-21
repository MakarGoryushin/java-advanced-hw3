package ru.centraluniversity.workload;

import java.util.ArrayDeque;

/**
 * Смесь короткоживущего мусора и долгоживущих: молодой мусор даёт нагрузку
 * на Eden, часть объектов переживает несколько циклов GC и продвигается в old.
 * <p>
 * TODO: реализовать {@link #run()}. Ориентиры - {@code ShortLivedGarbageWorkload}
 * (короткоживущие) и {@code LongLivedObjectsWorkload} (продвижение в old).
 * Результат итерации обязан быть потреблён (поле {@code sink}).
 */
public final class MixedWorkload implements GcWorkload {

    private static final int GARBAGE_COUNT = 30_000;
    private static final int GARBAGE_SIZE = 128;
    private static final int LONG_LIVED_SIZE = 4 * 1024;
    private static final int LONG_LIVED_LIMIT = 1_000;

    private final ArrayDeque<byte[]> longLived = new ArrayDeque<>();
    private Object sink;

    @Override
    public String name() {
        return "mixed";
    }

    @Override
    public void run() {
        for (int i = 0; i < GARBAGE_COUNT; i++) {
            byte[] garbage = new byte[GARBAGE_SIZE];
            garbage[0] = (byte) i;
            sink = garbage;

            if (i % 250 == 0) {
                longLived.addLast(new byte[LONG_LIVED_SIZE]);
                if (longLived.size() > LONG_LIVED_LIMIT) {
                    longLived.removeFirst();
                }
            }
        }

        sink = longLived.peekLast();
    }
}
