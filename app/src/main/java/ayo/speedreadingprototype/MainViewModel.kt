package ayo.speedreadingprototype

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.InputStream

class MainViewModel constructor(
    coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider(),
    val speedReader: SpeedReader = SpeedReader()
) : BaseViewModel(coroutineContextProvider) {

    val textStreamLiveData = MutableLiveData<String>()

    fun beginStreamingText(inputStream: InputStream) {
        launch {
            speedReader.getTextStream(inputStream).collect { word ->
                textStreamLiveData.postValue(word)
            }
        }
    }
}