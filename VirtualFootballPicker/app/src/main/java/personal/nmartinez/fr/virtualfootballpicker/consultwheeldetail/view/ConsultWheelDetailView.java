package personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

public interface ConsultWheelDetailView extends LifecycleOwner {

    void initViews(Wheel wheel);
}
