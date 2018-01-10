package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core;

/**
 * Created by Nicolas on 29/12/2017.
 */

public interface IEditObjectiveCore {

    void setObjectivePeriod(int period);
    void setObjectiveName(String name);
    void saveObjective();
    void checkObjectivePeriod();
    void checkObjectiveName();
}
