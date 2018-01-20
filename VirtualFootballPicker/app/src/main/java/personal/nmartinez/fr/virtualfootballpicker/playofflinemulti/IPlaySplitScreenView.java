package personal.nmartinez.fr.virtualfootballpicker.playofflinemulti;

/**
 * Created by Nicolas on 13/01/2018.
 */

public interface IPlaySplitScreenView {

    void pickFirstPlayerFirstPeriodObjective(String objective);
    void pickFirstPlayerSecondPeriodObjective(String objective);
    void pickSecondPlayerFirstPeriodObjective(String objective);
    void pickSecondPlayerSecondPeriodObjective(String objective);

    void pickFirstPlayerStars(int stars);
    void pickSecondPlayerStars(int stars);

    void setWheelToBeUsed(String wheel);

    void showError(String message);
}
