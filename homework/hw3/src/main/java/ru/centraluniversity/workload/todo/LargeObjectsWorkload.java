package ru.centraluniversity.workload;

/**
 * Большие массивы размером в единицы мегабайт. Для G1 такие объекты не
 * помещаются в регион и выделяются как humongous (крупнее примерно половины
 * региона) - особая нагрузка и на G1, и на ZGC.
 * <p>
 * TODO: реализовать {@link #run()}: массивы в единицы МБ, результат итерации
 * обязан быть потреблён (поле {@code sink}).
 */
public final class LargeObjectsWorkload implements GcWorkload {

    private static final int ARRAY_SIZE = 2 * 1024 * 1024;
    private static final int ARRAYS_PER_ITERATION = 3;

    private Object sink;

    @Override
    public String name() {
        return "largeObjects";
    }

    @Override
    public void run() {
        for (int i = 0; i < ARRAYS_PER_ITERATION; i++) {
            byte[] array = new byte[ARRAY_SIZE];
            array[0] = (byte) i;
            sink = array;
        }
    }
}
