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
        int idx = 35;

        while (idx < str.length())
        {
            str.insert(idx, "\n");
            idx = idx + 35;
        }
        return str.toString();
    }
}
