package pl.mwaszczuk.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Assert

class TestObserver<T>(scope: CoroutineScope, flow: Flow<T>) {
    val values = mutableListOf<T>()
    private val job: Job = scope.launch {
        flow.collect { values.add(it) }
    }

    fun assertNoValues(): TestObserver<T> {
        Assert.assertEquals(emptyList<T>(), this.values)
        return this
    }

    fun assertValues(vararg values: T): TestObserver<T> {
        Assert.assertEquals(values.toList(), this.values)
        return this
    }

    fun finish(): TestObserver<T> {
        job.cancel()
        return this
    }
}
