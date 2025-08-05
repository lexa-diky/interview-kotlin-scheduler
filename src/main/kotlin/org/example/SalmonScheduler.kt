package org.example

import kotlin.time.Duration

interface SalmonScheduler {

    // Feel free to replace [task] lambda with anything else you may need
    fun schedule(delay: Duration, task: suspend () -> Unit)
}
