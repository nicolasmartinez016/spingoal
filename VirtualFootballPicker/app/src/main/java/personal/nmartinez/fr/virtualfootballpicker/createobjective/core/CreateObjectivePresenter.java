package personal.nmartinez.fr.virtualfootballpicker.createobjective.core;


import personal.nmartinez.fr.virtualfootballpicker.models.ObjectiveModel;

/**
 * Created by Nicolas on 09/12/2017.
 */

public interface CreateObjectivePresenter {
    void createObjective(ObjectiveModel objectiveModel);
    void setObjectivePeriod(int period);
    void setObjectiveEditable(boolean isEditable);

    void initViews();
}
