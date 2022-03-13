package pl.mwaszczuk.domain

import kotlinx.coroutines.Dispatchers

class Dispatchers {

    val io = Dispatchers.IO
    val default = Dispatchers.Default
    val main = Dispatchers.Main
}
