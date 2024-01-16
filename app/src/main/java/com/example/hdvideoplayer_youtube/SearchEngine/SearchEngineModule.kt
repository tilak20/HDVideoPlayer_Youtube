@file:Suppress("PackageName")

/* ktlint-disable package-name */
package com.example.hdvideoplayer_youtube.SearchEngine

class SearchEngineModule {

    fun createSearchEngineManager(): SearchEngineManager {
        return SearchEngineManagerImpl()
    }
}
