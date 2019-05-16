package personal.nmartinez.fr.virtualfootballpicker.components.consultwheels.presenter;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Wheel;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface ConsultWheelsPresenter {
    void setWheelToUse(Wheel wheel);
    void getData();
    void removeWheel(Wheel wheel);
}
