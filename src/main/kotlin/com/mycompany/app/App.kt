package com.mycompany.app

import io.javalin.Javalin
import io.javalin.Javalin.log
import io.javalin.plugin.metrics.MicrometerPlugin
import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
import io.micrometer.core.instrument.binder.jetty.TimedHandler
import io.micrometer.core.instrument.util.HierarchicalNameMapper
import io.micrometer.graphite.GraphiteConfig
import io.micrometer.graphite.GraphiteMeterRegistry
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {

    val graphiteConfig: GraphiteConfig = object : GraphiteConfig {
        override fun host(): String {
            return "localhost"
        }

        override fun get(p0: String): String? {
            return null
        }

        override fun step(): Duration {
            return Duration.ofSeconds(5)
        }
    }

    val graphiteRegistry = GraphiteMeterRegistry(graphiteConfig, Clock.SYSTEM, HierarchicalNameMapper.DEFAULT)
    JvmMemoryMetrics().bindTo(graphiteRegistry)
    TimedHandler(graphiteRegistry, emptyList())

    val jav = Javalin.create { config ->
        //config.enableDevLogging()
        config.registerPlugin(MicrometerPlugin(graphiteRegistry))
        //config.requestLogger { ctx, ms -> log.error("${ctx.url()} time:$ms")}
    }
    // val app = Javalin.create().start(7000)
    val app = jav.start(7000)

    app.get("/") { ctx ->
        //log.error("Ping")
        ctx.result("Hello World")
    }

    app.get("/sleep") { ctx ->
        //log.error("Ping")
        Thread.sleep(1000)
        ctx.result("Hello World")
    }

    fun getFuture() = CompletableFuture<String>().apply {
        Executors.newSingleThreadScheduledExecutor()
                .schedule({ this.complete("Hello World!") }, 1, TimeUnit.SECONDS)
    }

    app.get("/future") { ctx ->
        log.error("Ping-Future")
        ctx.result(getFuture())
    }

    app.before { ctx ->
        //log.error("Before Request")
    }
}