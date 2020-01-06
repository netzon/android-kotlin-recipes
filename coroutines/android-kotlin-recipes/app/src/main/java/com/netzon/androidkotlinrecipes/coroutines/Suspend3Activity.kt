package com.netzon.androidkotlinrecipes.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import com.netzon.androidkotlinrecipes.R
import kotlinx.android.synthetic.main.activity_suspend3.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class Suspend3Activity : AppCompatActivity(), CodeView.OnHighlightListener {

    private val mJobs: MutableList<Job> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend3)

        this.title = "Suspend 3"

        btn_start.setOnClickListener {
            start()
        }

        code_view.setOnHighlightListener(this)
            .setOnHighlightListener(this)
            .setTheme(Theme.MONOKAI_SUBLIME)
            .setCode("fun start() {\n" +
                    "        thread {\n" +
                    "            runBlocking {\n" +
                    "                val channel = Channel<Int>()\n" +
                    "                mJobs.add(launch {\n" +
                    "                    for (i in 1..5) {\n" +
                    "                        channel.send(i)\n" +
                    "                        delay(500)\n" +
                    "                    }\n" +
                    "                })\n" +
                    "\n" +
                    "                for (item: Int in channel) {\n" +
                    "                    runOnUiThread {\n" +
                    "                        tv_label.text = \"\${tv_label.text}\\n\$item\"\n" +
                    "                    }\n" +
                    "                }\n" +
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
        thread {
            runBlocking {
                val channel = Channel<Int>()
                mJobs.add(launch {
                    for (i in 1..5) {
                        channel.send(i)
                        delay(500)
                    }
                })

                for (item: Int in channel) {
                    runOnUiThread {
                        tv_label.text = "${tv_label.text}\n$item"
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        mJobs.forEach { it.cancel() }

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
