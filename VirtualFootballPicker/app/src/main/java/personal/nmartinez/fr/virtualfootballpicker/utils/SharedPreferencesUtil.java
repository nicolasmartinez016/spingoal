package personal.nmartinez.fr.virtualfootballpicker.utils;

import android.content.SharedPreferences;

/**
 * Created by Nicolas on 31/12/2017.
 */

public class SharedPreferencesUtil {

    public static void commitPreferenceChange(SharedPreferences sharedPreferences, String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
