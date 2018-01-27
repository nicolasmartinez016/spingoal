package personal.nmartinez.fr.virtualfootballpicker.utils;

import android.util.Log;

/**
 * Created by Nicolas on 02/12/2017.
 */

public class StringUtils {

    private static final String TAG = "error";

    public static boolean isNullOrEmpty(String s){
        if (s == null){
            return true;
        }

        return s.equals("");
    }

    public static String cutObjectiveName(String name){
        StringBuilder str = new StringBuilder(name);
        int idx = 28;

        while (idx < str.length()) {
            try{
                while (!(Character.toString(name.charAt(idx)).equalsIgnoreCase(" "))) {
                    idx = idx - 1;
                    if (idx < 0){
                        idx = 28;
                        break;
                    }
                }
                str.insert(idx, "\n");
                idx = idx + 28;
            }
            catch (StringIndexOutOfBoundsException e){
                Log.e(TAG, e.getMessage());
            }

        }

        return str.toString();
    }
}
