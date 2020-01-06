package com.netzon.androidkotlinrecipes.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.tiagohm.codeview.CodeView
import br.tiagohm.codeview.Language
import br.tiagohm.codeview.Theme
import com.netzon.androidkotlinrecipes.R
import kotlinx.android.synthetic.main.activity_suspend2.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class Suspend2Activity : AppCompatActivity(), CodeView.OnHighlightListener {

    private var mJobs: MutableList<Job> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend2)

        this.title = "Suspend 2"

        btn_start.setOnClickListener {
            start()
        }

        code_view.setOnHighlightListener(this)
            .setOnHighlightListener(this)
            .setTheme(Theme.MONOKAI_SUBLIME)
            .setCode("fun start() {\n" +
                    "        tv_label.text = \"Logs:\"\n" +
                    "\n" +
                    "        //Call runBlocking on separate thread so it will not block the UI thread\n" +
                    "        thread {\n" +
                    "            runBlocking {\n" +
                    "                // async 1\n" +
                    "                mJobs.add(launch {\n" +
                    "                    delay(500)\n" +
                    "                    runOnUiThread {\n" +
                    "                        tv_label.text = \"\${tv_label.text}\\nPrinted on Async1\"\n" +
                    "                    }\n" +
                    "                })\n" +
                    "\n" +
                    "                // async 2\n" +
                    "                mJobs.add(launch {\n" +
                    "                    runOnUiThread {\n" +
                    "                        tv_label.text = \"\${tv_label.text}\\nPrinted on Async2\"\n" +
                    "                    }\n" +
                    "                })\n" +
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
        tv_label.text = "Logs:"

        //Call runBlocking on separate thread so it will not block the UI thread
        thread {
            runBlocking {
                // async 1
                mJobs.add(launch {
                    delay(500)
                    runOnUiThread {
                        tv_label.text = "${tv_label.text}\nPrinted on Async1"
                    }
                })

                // async 2
                mJobs.add(launch {
                    runOnUiThread {
                        tv_label.text = "${tv_label.text}\nPrinted on Async2"
                    }
                })
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
