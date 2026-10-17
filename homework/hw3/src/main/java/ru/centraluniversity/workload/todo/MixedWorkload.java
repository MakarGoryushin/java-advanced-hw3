package ru.centraluniversity.workload;

/**
 * Смесь короткоживущего мусора и долгоживущих: молодой мусор даёт нагрузку
 * на Eden, часть объектов переживает несколько циклов GC и продвигается в old.
 * <p>
 * TODO: реализовать {@link #run()}. Ориентиры - {@code ShortLivedGarbageWorkload}
 * (короткоживущие) и {@code LongLivedObjectsWorkload} (продвижение в old).
 * Результат итерации обязан быть потреблён (поле {@code sink}).
 */
public final class MixedWorkload implements GcWorkload {

    // TODO: поля и константы нагрузки

    private Object sink;

    @Override
    public String name() {
        return "mixed";
    }

    @Override
    public void run() {
        // TODO: одна итерация: короткоживущие + долгоживущие, результат в sink.
    }
}
