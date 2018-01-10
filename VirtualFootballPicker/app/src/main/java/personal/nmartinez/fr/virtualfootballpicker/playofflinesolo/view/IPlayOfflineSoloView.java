package personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.view;

/**
 * Created by Nicolas on 08/12/2017.
 */

public interface IPlayOfflineSoloView {
    void setFirstHalfObjectiveTextView(String objective);
    void setSecondHalfObjectiveTextView(String objective);
    void displayOrHideFirstHalfObjective();
    void displayOrHideSecondHalfObjective();
    void setWheelUsedTextView(String wheel);
    void showError(String message);
    void displayStars(int stars);
}
