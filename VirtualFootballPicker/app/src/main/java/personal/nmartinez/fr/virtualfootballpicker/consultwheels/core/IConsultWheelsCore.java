package personal.nmartinez.fr.virtualfootballpicker.consultwheels.core;

import android.content.Context;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.EditWheelDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.IConsultWheelsView;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface IConsultWheelsCore {
    void setWheelToUse(Wheel wheel);
    List<Wheel> getData();
    Wheel getSelectedWheel();
    EditWheelDialog openEdition(Wheel wheel);
    IConsultWheelsView getView();
    void removeWheel(Wheel wheel);
}
