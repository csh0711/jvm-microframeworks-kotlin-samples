package info.novatec

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("info.novatec")
                .mainClass(Application.javaClass)
                .start()
    }
}