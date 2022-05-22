package com.tanvircodder.exmple.uvinvercitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebVersityView extends AppCompatActivity {
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_versity_view);
        myWebView = (WebView) findViewById(R.id.webview);
//        now i am going to extract the text that are passed to me
        Intent intent = getIntent();
        String url = intent.getExtras().getString("web_url");
        String name = intent.getExtras().getString("name");
        setTitle(name);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        myWebView.setWebViewClient(webViewClient);
//        now i am going to load the url
        myWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.myWebView.canGoBack()) {
            this.myWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
class WebViewClientImpl extends WebViewClient{
    private Activity activity = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if(url.indexOf(url) > -1 ) return false;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}