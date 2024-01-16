package com.example.hdvideoplayer_youtube.Main

import android.annotation.SuppressLint

class ApplicationGraph {

    companion object {

        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        private var graph: ApplicationGraph? = null

        @JvmStatic
        fun init() {
            if (graph == null) {
                graph = ApplicationGraph()
            }
        }
    }
}
