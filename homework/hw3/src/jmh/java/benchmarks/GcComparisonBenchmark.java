package benchmarks;

/**
 * Заготовка JMH-бенчмарка для сравнения сборщиков мусора G1, ZGC и Shenandoah.
 * <p>
 * Это минимальный скелет - логики здесь нет, её предстоит написать самим, как и
 * свои нагрузки в {@code ru.centraluniversity.workload.Workloads}.
 *
 * <h2>Что должно получиться</h2>
 * <ul>
 *   <li>Один бенчмарк, который гоняет все нагрузки из {@code Workloads.all()}: готовые
 *       {@code short-lived-garbage} и {@code long-lived-objects} плюс свои (mixed, largeObjects, referenceHeavy).</li>
 *   <li>Один и тот же набор нагрузок прогоняется под G1, ZGC и Shenandoah передачей JVM-аргументов
 *       через {@code -PjmhJvmArgs} - код бенчмарка не меняется, меняется только
 *       коллектор в форкнутой JVM.</li>
 *   <li>Мерится среднее время итерации или пропускная способность под каждой нагрузкой.
 *       Результат каждой итерации обязан быть потреблён (Blackhole либо sink-поле в самой
 *       нагрузке), иначе Escape Analysis выкинет аллокации и нагрузка на GC станет нулевой.</li>
 * </ul>
 *
 * <h2>Подсказки по устройству</h2>
 * TODO: расставить аннотации:
 * <ul>
 *   <li>{@code @State(Scope.Benchmark)} - состояние на весь прогон.</li>
 *   <li>{@code @Param} со списком нагрузок из {@code Workloads.all()} - JMH сам прогонит
 *       каждую нагрузку. Имя для {@code @Benchmark}-метода и отчёта берётся
 *       из {@code GcWorkload.name()}.</li>
 *   <li>{@code @BenchmarkMode(Mode.AverageTime)} или {@code Throughput} с {@code @OutputTimeUnit}
 *       - среднее время итерации или сколько итераций в секунду.</li>
 *   <li>{@code @Fork}/{@code @Warmup}/{@code @Measurement} - форки и прогревочные/замеряемые
 *       итерации. Для GC-сравнения они обязательны: каждая форка - новая JVM с выбранным
 *       коллектором.</li>
 *   <li>{@code @Benchmark}-метод: {@code workload.run()}, результат - в {@code Blackhole}
 *       (плюс к sink в самих нагрузках - двойная страховка от скаляризации).</li>
 * </ul>
 *
 * <h2>Запуск</h2>
 * <pre>
 *   ./gradlew jmh -PjmhJvmArgs="-XX:+UseG1GC -Xms2g -Xmx2g -Xlog:gc*:file=logs/g1.log"
 *   ./gradlew jmh -PjmhJvmArgs="-XX:+UseZGC -Xms2g -Xmx2g -Xlog:gc*:file=logs/zgc.log"
 *   ./gradlew jmh -PjmhJvmArgs="-XX:+UseShenandoahGC -Xms2g -Xmx2g -Xlog:gc*:file=logs/shenandoah.log"
 * </pre>
 * Результаты - JSON в {@code build/results/jmh/results.json}; по ним строятся графики для отчёта.
 */
public class GcComparisonBenchmark {
    // TODO: реализовать бенчмарк (см. подсказки выше).
}
