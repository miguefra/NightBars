package nightbars.nightbars.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;

import nightbars.nightbars.MapsActivity;

public class WebAppInterface {
    private Context context;
    private MapsActivity mapsActivity;
    private String placeName;

    public WebAppInterface(Context context, MapsActivity mapsActivity, String placeName) {
        this.context = context;
        this.mapsActivity = mapsActivity;
        this.placeName = placeName;
    }

    // Pasa por parámetro el mensaje que se quiere mostar.
    @JavascriptInterface
    public void showDialog(String message) {

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(mapsActivity.getValue()).setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();*/
    }

    @JavascriptInterface
    public String getPlaceName() {
        return placeName;
    }
}
