package nightbars.nightbars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import nightbars.nightbars.helper.WebAppInterface;

import static nightbars.nightbars.LocalActivity.PLACE_NAME;

public class MapsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        WebView webView = (WebView) this.findViewById(R.id.webView);

        webView.addJavascriptInterface(new WebAppInterface(this, MapsActivity.this, (String) extras.get(PLACE_NAME)), "Android");

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //webView.loadUrl("file:///android_asset/mapa.html");
        webView.loadUrl("file:///android_asset/como_llegar.html");
    }
}
