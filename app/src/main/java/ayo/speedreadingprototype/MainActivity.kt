package ayo.speedreadingprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.beginStreamingText(assets.open("sample.txt"))
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.textStreamLiveData.observe(this, Observer { renderText(it) })
    }

    private fun renderText(word: String) {
        text_box.text = word
    }
}
