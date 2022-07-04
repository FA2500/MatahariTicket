package com.example.pancaranticket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

public class BillPlz extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billplz);

        Bundle extras = getIntent().getExtras();
        double total = extras.getDouble("total");
        int seat = extras.getInt("seat");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Map<String, String> map = new HashMap<>();
        map.put("email", userInfo.getEmail());
        map.put("phone", "01156403489");
        map.put("name", userInfo.getFullName());
        map.put("amount", String.valueOf(total * 100));
        map.put("desc",seat+" Bus seat");

        myWebView.loadUrl("https://pay.khronalforce.com/pay",map);

        myWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);

                Log.d("Webview","Current URL = "+url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(url.contains("true"))
                {
                    //testtest(view , "True");
                }
                else if(url.contains("false"))
                {
                    //testtest(view , "Failed");
                }
            }
        });

    }

    public void testtest(View v , String status)
    {
        //Intent intent = new Intent(BillPlz.this, testpayment.class);
        //intent.putExtra("status",status);
        //startActivity(intent);

    }
}
