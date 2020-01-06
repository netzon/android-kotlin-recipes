package com.netzon.androidkotlinrecipes.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import com.netzon.androidkotlinrecipes.R
import kotlinx.android.synthetic.main.activity_suspend1.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class Suspend1Activity : AppCompatActivity(), CodeView.OnHighlightListener {

    private var mJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend1)

        this.title = "Suspend 1"

        btn_start.setOnClickListener {
            start()
            it.isEnabled = false
        }

        code_view.setOnHighlightListener(this)
            .setOnHighlightListener(this)
            .setTheme(Theme.MONOKAI_SUBLIME)
            .setCode("fun start() {\n" +
                    "        mJob = GlobalScope.launch {\n" +
                    "            var i = 0\n" +
                    "            while (true) {\n" +
                    "                runOnUiThread {\n" +
                    "                    tv_label.text = \"\$i\"\n" +
                    "                    i++\n" +
                    "                }\n" +
                    "                delay(1000)\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }")
            .setLanguage(Language.KOTLIN)
            .setWrapLine(false)
            .setFontSize(10f)
            .setZoomEnabled(false)
            .setShowLineNumber(true)
            .setStartLineNumber(1)
            .apply()
    }

    private fun start() {
        mJob = GlobalScope.launch {
            var i = 0
            while (true) {
                runOnUiThread {
                    tv_label.text = "$i"
                    i++
                }
                delay(1000)
            }
        }
    }

    override fun onDestroy() {
        mJob?.cancel()

        super.onDestroy()
    }

    override fun onStartCodeHighlight() {

    }

    override fun onLanguageDetected(p0: Language?, p1: Int) {

    }

    override fun onFontSizeChanged(p0: Int) {

    }

    override fun onLineClicked(p0: Int, p1: String?) {

    }

    override fun onFinishCodeHighlight() {

    }
}
