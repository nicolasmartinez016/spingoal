package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core;

import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.EditObjectiveDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.IConsultObjectivesView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 03/12/2017.
 */

public interface IConsultObjectivesCore {
    void retrieveObjectives();
    EditObjectiveDialog openEdition(Objective objective);
    IConsultObjectivesView getView();
    void removeObjective(Objective objective);
}
