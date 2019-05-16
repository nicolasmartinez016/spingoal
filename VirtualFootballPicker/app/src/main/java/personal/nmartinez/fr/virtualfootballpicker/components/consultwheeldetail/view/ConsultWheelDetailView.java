package personal.nmartinez.fr.virtualfootballpicker.components.consultwheeldetail.view;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Wheel;

public interface ConsultWheelDetailView extends LifecycleOwner {

    void initViews(Wheel wheel);
}
