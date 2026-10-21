package ru.centraluniversity.workload;

/**
 * Мусор молодого поколения: объекты рождаются в Eden и сразу забываются.
 * <p>
 * Служит образцом правильной формы: результат итерации обязан быть потреблён
 * (поле {@code sink}), иначе Escape Analysis скаляризует аллокации и нагрузка
 * на GC станет нулевой.
 */
public final class ShortLivedGarbageWorkload implements GcWorkload {

    private static final int COUNT = 200_000;

    private Object sink;

    @Override
    public String name() {
        return "short-lived-garbage";
    }

    @Override
    public void run() {
        for (int i = 0; i < COUNT; i++) {
            byte[] a = new byte[32];
            a[0] = (byte) i;
            sink = a;
        }
    }
}
