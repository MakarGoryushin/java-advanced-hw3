package ru.centraluniversity.workload;

import java.util.ArrayDeque;

/**
 * Долгоживущие: буфер объектов, годных к выживанию. Часть переживает несколько
 * циклов GC и продвигается в old, часть по достижении лимита "умирает".
 */
public final class LongLivedObjectsWorkload implements GcWorkload {

    private static final int CHUNK = 4096;  // байт в долгоживущем объекте
    private static final int LIMIT = 2_000; // максимум долгоживущих

    private final ArrayDeque<byte[]> live = new ArrayDeque<>();
    private Object sink;

    @Override
    public String name() {
        return "long-lived-objects";
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (live.size() > LIMIT) {
                live.removeFirst();
            }
            live.addLast(new byte[CHUNK]);
            byte[] tmp = new byte[48];
            tmp[0] = (byte) i;
            sink = tmp;
        }
    }
}
