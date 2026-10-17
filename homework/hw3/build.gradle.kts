plugins {
    java
    id("me.champeau.jmh") version "0.7.3"
}

group = "ru.centraluniversity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    jmhImplementation("org.openjdk.jmh:jmh-core:1.37")
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

// Дефолты прогона для всей плагин-задачи `jmh`: разумные настройки по умолчанию,
// чтобы результаты были сравнимы между прогонами. Их можно переопределить
// аннотациями в собственном бенчмарке.
jmh {
    resultFormat.set("JSON")    // итоговые числа пишем в JSON, удобно для сводной таблицы и графиков
    fork.set(2)                 // два независимых JVM-процесса - честнее дисперсия между прогонами
    warmupIterations.set(3)     // 3 прогревочные итерации, их результат не учитывается (нужен разогрев JIT)
    iterations.set(5)           // 5 замеряемых итераций, из них считаются метрики
    timeUnit.set("ms")          // единица измерения в отчёте - миллисекунды

    // Прогон под конкретный сборщик мусора - передачей JVM-аргументов форкнутой JVM:
    //   ./gradlew jmh -PjmhJvmArgs="-XX:+UseG1GC -Xms2g -Xmx2g -Xlog:gc*:file=logs/g1.log"
    // Так один и тот же скомпилированный батч прогоняется под G1/ZGC/Shenandoah
    // без правки кода: аргументы из свойства попадают в JVM, где работает бенчмарк.
    val gcArgs = providers.gradleProperty("jmhJvmArgs")
    if (gcArgs.isPresent) {
        jvmArgs.set(gcArgs.get().split(" ").filter { it.isNotBlank() })
    }
}

// Папка для GC-логов: её нет в дереве исходников, но fork-нутая JVM пишет туда
// `-Xlog:gc*:file=logs/...`, поэтому создаём её к старту прогона.
tasks.named("jmh") {
    doFirst {
        mkdir("logs")
    }
}
