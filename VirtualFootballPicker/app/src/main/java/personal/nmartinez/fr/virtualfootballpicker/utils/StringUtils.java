package personal.nmartinez.fr.virtualfootballpicker.utils;

/**
 * Created by Nicolas on 02/12/2017.
 */

public class StringUtils {

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
            while (!(Character.toString(name.charAt(idx)).equalsIgnoreCase(" "))) {
                idx = idx - 1;
            }
            str.insert(idx, "\n");
            idx = idx + 28;
        }

        return str.toString();
    }
}
