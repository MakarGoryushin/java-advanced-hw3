# Дополнительный материал

- [HotSpot GC Tuning Guide (Java 21)](https://docs.oracle.com/en/java/javase/21/gctuning/) - официальный гайд по настройке GC; с него стоит начать, дальше - конкретные главы.
- [G1 Garbage Collector (Java 21)](https://docs.oracle.com/en/java/javase/21/gctuning/garbage-first-garbage-collector.html) - глава про G1: регионы, фазы young/marking/mixed, humongous, политика пауз.
- [JEP 377: ZGC](https://openjdk.org/jeps/377) - ZGC в JDK 15: Colored Pointers, Load Barrier, ZPages, почему паузы не зависят от размера кучи.
- [JEP 439: Generational ZGC](https://openjdk.org/jeps/439) - генерационный ZGC (JDK 21): зачем ZGC потребовалось деление на молодое и старое поколение.
- [JEP 379: Shenandoah](https://openjdk.org/jeps/379) - Shenandoah в JDK 15: конкурентная эвакуация, forwarding-указатели, роль в этом деле снапшот-барьеров.
- [JEP 318: Epsilon](https://openjdk.org/jeps/318) - no-op сборщик: зачем существует и в каких сценариях применим.
- [JMH wiki](https://github.com/openjdk/jmh/wiki) - документация JMH: режимы, аннотации, форки, прогрев.
- [JMH Profilers](https://github.com/openjdk/jmh/wiki/Profilers) - профайлеры JMH, включая gc: метрики `gc.alloc.rate` и `gc.alloc.rate.norm`.
- [java.lang.ref (Java 21 API)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/ref/package-summary.html) - документация по ссылкам: Reference, WeakReference, SoftReference, PhantomReference, ReferenceQueue.
