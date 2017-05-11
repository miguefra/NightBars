package nightbars.nightbars;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import nightbars.nightbars.helper.WebAppInterface;

public class MapsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_maps);

        WebView webView = (WebView) this.findViewById(R.id.webView);

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/maps.html");
    }
}
