package com.carlisle.seed.module.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.carlisle.seed.R;
import com.carlisle.seed.module.base.CommonActivity;

/**
 * Creator      : carlisle
 * Date         : 05/09/2017
 * Description  :
 */

public class SimpleWebViewActivity extends CommonActivity {

    private static final String TAG = SimpleWebViewActivity.class.getSimpleName();
    private static final String KEY_URL = "url";
    private static final String KEY_TITLE = "title";

    private ViewGroup viewContainer;
    private WebView webView;
    private ProgressBar loadingView;

    private String url = null;
    private String title = null;

    public static Intent buildIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, SimpleWebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TITLE, title);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_web_view);

        url = getIntent().getStringExtra(KEY_URL);
        title = getIntent().getStringExtra(KEY_TITLE);

        loadingView = (ProgressBar) findViewById(R.id.loading_view);
        viewContainer = (ViewGroup) findViewById(R.id.web_view_container);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.setVisibility(View.GONE);
            }
        });

        toolbar.setTitle(title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(webView.getUrl())) {
            webView.loadUrl(url);
            webView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    private void destroyWebView() {
        viewContainer.removeView(webView);
        webView.removeAllViews();
        webView.destroy();
        webView = null;
    }

}
