package com.example.madcapstone2.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.madcapstone2.R


class MapFragment : Fragment() {

    private lateinit var notificationsViewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWebview(view)
    }

    private fun loadWebview(view: View) {
        val webView: WebView = view.findViewById(R.id.wbMap)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView.loadUrl("https://www.google.com/maps/d/u/0/edit?mid=1h6XgNfUdHZz-Chi8YF-OnuOeywsKsA8&usp=sharing")
    }
}