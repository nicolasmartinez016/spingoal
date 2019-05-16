package personal.nmartinez.fr.virtualfootballpicker.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;

public class ImageUtils {

    public static Drawable getStarImage(Context context, int stars) {
        switch (stars) {
            case 0 :
                return context.getResources().getDrawable(R.drawable.onestar);
            case 1 :
                return context.getResources().getDrawable(R.drawable.twostars);
            case 2 :
                return context.getResources().getDrawable(R.drawable.threestars);
            case 3 :
                return context.getResources().getDrawable(R.drawable.fourstars);
            case 4 :
                return context.getResources().getDrawable(R.drawable.fivestars);
            default:
                return null;
        }
    }

    public static Drawable getPeriodImage(Context context, int period) {
        switch (period) {
            case Objective.FIRST_PERIOD :
                return context.getResources().getDrawable(R.drawable.field_first_period);
            case Objective.SECOND_PERIOD :
                return context.getResources().getDrawable(R.drawable.field_second_period);
            case Objective.BOTH_PERIODS :
                return context.getResources().getDrawable(R.drawable.field);
            default :
                return null;
        }
    }
}
