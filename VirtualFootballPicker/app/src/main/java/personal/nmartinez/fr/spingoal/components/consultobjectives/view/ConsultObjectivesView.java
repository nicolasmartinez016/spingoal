package personal.nmartinez.fr.spingoal.components.consultobjectives.view;

import android.arch.lifecycle.LifecycleOwner;

import java.util.List;

import personal.nmartinez.fr.spingoal.data.models.Objective;

/**
 * Created by Nicolas on 02/12/2017.
 */

public interface ConsultObjectivesView extends LifecycleOwner {
    void displayWaitingForData(boolean wait);

    void showObjectives(List<Objective> objectives);

    void showObjectiveDeletedSuccess();

    void showObjectiveDeletedFailure();
}
