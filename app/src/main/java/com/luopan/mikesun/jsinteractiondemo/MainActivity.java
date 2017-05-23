package com.luopan.mikesun.jsinteractiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    WebView webView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInteration(), "control");
        webView.loadUrl("file:///android_asset/demo.html");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:jsfunction()");
            }
        });

    }

    private class JsInteration {
        String responseMsg;

        @JavascriptInterface
        public String javaFunc(String msg) {
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            return "javaFunc ok";
        }

        @JavascriptInterface
        public void responseMsg(String responseMsg) {
            Toast.makeText(getApplicationContext(),responseMsg,Toast.LENGTH_SHORT).show();
            this.responseMsg = responseMsg;
        }

    }
}
