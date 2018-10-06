package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view;

import android.app.Activity;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 02/12/2017.
 */

public interface IConsultObjectivesView {
    void displayWaitingForData(boolean wait);
    void setObjectivesAdapter(List<Objective> objectives);
    Activity getViewActivity();

    void displayObjectiveDeletedPopup();

    void displayCantDeleteObjectivePopup();
}
