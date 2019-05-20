package personal.nmartinez.fr.spingoal.components.consultwheels.presenter;

import personal.nmartinez.fr.spingoal.data.models.Wheel;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface ConsultWheelsPresenter {
    void setWheelToUse(Wheel wheel);
    void getData();
    void removeWheel(Wheel wheel);
}
