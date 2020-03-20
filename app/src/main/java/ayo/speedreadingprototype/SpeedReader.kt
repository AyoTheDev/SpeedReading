package ayo.speedreadingprototype

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

private const val CHARSET_NAME = "UTF-8"
private const val DEFAULT_DELAY = 200L

class SpeedReader {

    fun getTextStream(inputStream: InputStream): Flow<String> {
        return flow {
            var line: String?
                InputStreamReader(inputStream, Charset.forName(CHARSET_NAME)).use { isr ->
                    BufferedReader(isr).use { br ->
                        while (br.readLine().also { line = it } != null) {
                            line?.trim()?.split(" ")?.forEach { word ->
                                emit(word)
                            }
                        }
                    }
                }
        }.flowOn(Dispatchers.Default).onEach { delay(DEFAULT_DELAY) }
    }
}