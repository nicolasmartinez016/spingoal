package personal.nmartinez.fr.virtualfootballpicker.createobjective.view;

/**
 * Created by Nicolas on 09/12/2017.
 */

public interface ICreateObjectiveView {
    void displayNameUnavailableError();
    void hideNameUnavailableError();
    void displayObjectiveCreationSucces();
    void displayPickAPeriod();
    void hidePickAPeriod();
    void displayObjectiveCreationFailure();
    void showError(String message);
}
