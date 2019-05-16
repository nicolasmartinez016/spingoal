package personal.nmartinez.fr.virtualfootballpicker.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import personal.nmartinez.fr.virtualfootballpicker.R;

public class AlertUtils {

    private static void displayLongCrouton(Activity activity, String message, int color, ViewGroup view){

        //create config
        Configuration myConfig = new Configuration.Builder().
                setDuration(2500)
                .build();

        //create style
        Style style = new Style.Builder().setConfiguration(myConfig)
                .setBackgroundColor(color)
                .build();


        Crouton.clearCroutonsForActivity(activity);

        Crouton crouton = Crouton.makeText(activity, message, style, view);
        crouton.show();
    }

    public static void displayErrorCrouton(Activity activity, String message, ViewGroup view) {
        displayLongCrouton(activity, message, R.color.warningColor, view);
    }

    public static void displaySuccessCrouton(Activity activity, String message, ViewGroup view) {
        displayLongCrouton(activity, message, R.color.green, view);
    }
}
