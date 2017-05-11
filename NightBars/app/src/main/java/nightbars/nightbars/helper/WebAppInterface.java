package nightbars.nightbars.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    // Pasa por parámetro el mensaje que se quiere mostar.
    @JavascriptInterface
    public void showDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(message).setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
