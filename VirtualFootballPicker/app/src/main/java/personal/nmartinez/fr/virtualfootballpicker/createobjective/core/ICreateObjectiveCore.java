package personal.nmartinez.fr.virtualfootballpicker.createobjective.core;

import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 09/12/2017.
 */

public interface ICreateObjectiveCore {
    boolean isNameAvailable(String name);
    void createObjective();
    void setObjectiveName(String name);
    void setObjectivePeriod(int period);
    void setObjectiveEditable(boolean isEditable);
}
