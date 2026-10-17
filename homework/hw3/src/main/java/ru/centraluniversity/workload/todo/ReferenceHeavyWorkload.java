package ru.centraluniversity.workload;

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

    // TODO: поля и константы кэша

    private Object sink;

    @Override
    public String name() {
        return "referenceHeavy";
    }

    @Override
    public void run() {
        // TODO: одна итерация: ключи кэша создаются и сразу забываются.
    }
}
