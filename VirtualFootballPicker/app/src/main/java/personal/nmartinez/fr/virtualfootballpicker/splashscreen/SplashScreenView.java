package personal.nmartinez.fr.virtualfootballpicker.splashscreen;

import android.arch.lifecycle.LifecycleOwner;

public interface SplashScreenView extends LifecycleOwner {
    void showCreatingWheel();

    void showErrorInObjectivesCreation();

    void showWheelCreated();

    void showErrorInWheelCreation();

    void showWheelsRetrieved();

    void showCreatingObjectives();
}
