package sametdundar.com.sametdundartodolistapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utils {

    public static void showWarningDialog(Context context,
                                         String message) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle("Uyarı");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        try {
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog show = alert.show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
