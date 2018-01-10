package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;

import android.app.Activity;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface IConsultWheelsView {
    void showError(String message);
    void applyChangesInWheels();
    Activity getViewActivity();
}
