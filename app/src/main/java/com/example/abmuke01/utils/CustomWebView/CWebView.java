package com.example.abmuke01.utils.CustomWebView;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.example.abmuke01.utils.BootstrapBuilder.BootstrapCodeBuilder;
import com.example.abmuke01.utils.BootstrapBuilder.model.Htmlout;

public class CWebView {
    private WebView webView;
    public CWebView(WebView webView) {
        this.webView = webView;
        enableJs();
    }
    private void enableJs(){
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }
    private void loadHtmlout(Htmlout out){
        this.webView.loadData(out.getEncodingHTML(),out.getMineType(),out.getEncodingType());
    }
    public void loadHtml(BootstrapCodeBuilder bootstrapCodeBuilder){
        loadHtmlout(bootstrapCodeBuilder.getRenderableHtml());
    }
}
