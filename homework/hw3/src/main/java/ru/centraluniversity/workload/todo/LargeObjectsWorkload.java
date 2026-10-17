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

    // TODO: константа размера массива

    private Object sink;

    @Override
    public String name() {
        return "largeObjects";
    }

    @Override
    public void run() {
        // TODO: одна итерация: выделить большой массив, результат в sink.
    }
}
