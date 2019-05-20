package personal.nmartinez.fr.spingoal.components.consultwheeldetail.view;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.spingoal.data.models.Wheel;

public interface ConsultWheelDetailView extends LifecycleOwner {

    void initViews(Wheel wheel);
}
