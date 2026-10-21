package ru.centraluniversity.workload;

import java.util.ArrayList;
import java.util.List;

/**
 * Фабрика нагрузок на сборщик мусора.
 * <p>
 * Каждая нагрузка живёт в своём top-level классе (см. {@code ShortLivedGarbageWorkload},
 * {@code LongLivedObjectsWorkload}) - они же образец правильной формы: каждая итерация
 * обязана "потреблять" результат (sink-поле), иначе Escape Analysis скаляризует
 * аллокации и нагрузка на GC станет нулевой.
 * <p>
 * Остальные нагрузки (mixed, largeObjects, referenceHeavy) даны классами-заготовками:
 * осталось реализовать {@code run()} в каждом.
 */
public final class Workloads {

    private Workloads() {
    }

    public static GcWorkload shortLivedGarbage() {
        return new ShortLivedGarbageWorkload();
    }

    public static GcWorkload longLivedObjects() {
        return new LongLivedObjectsWorkload();
    }

    /**
     * Смесь короткоживущего мусора и долгоживущих.
     * TODO: реализовать run() в MixedWorkload, после чего раскомментировать в all().
     */
    public static GcWorkload mixed() {
        return new MixedWorkload();
    }

    /**
     * Большие массивы в единицы мегабайт.
     * TODO: реализовать run() в LargeObjectsWorkload, после чего раскомментировать в all().
     */
    public static GcWorkload largeObjects() {
        return new LargeObjectsWorkload();
    }

    /**
     * Кэш на слабых/мягких ссылках.
     * TODO: реализовать run() в ReferenceHeavyWorkload, после чего раскомментировать в all().
     */
    public static GcWorkload referenceHeavy() {
        return new ReferenceHeavyWorkload();
    }

    /**
     * Все нагрузки для прогона: готовые + свои (добавить после реализации).
     */
    public static List<GcWorkload> all() {
        List<GcWorkload> result = new ArrayList<>();
        result.add(shortLivedGarbage());
        result.add(longLivedObjects());
        result.add(mixed());
        result.add(largeObjects());
        result.add(referenceHeavy());
        return result;
    }
}
