package personal.nmartinez.fr.virtualfootballpicker.components.consultobjectives.view;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.data.models.Objective;

/**
 * Created by Nicolas on 02/12/2017.
 */

public interface ConsultObjectivesView extends LifecycleOwner {
    void displayWaitingForData(boolean wait);

    void showObjectives(List<Objective> objectives);

    void showObjectiveDeletedSuccess();

    void showObjectiveDeletedFailure();
}
