package personal.nmartinez.fr.virtualfootballpicker.createwheels.view;

/**
 * Created by Nicolas on 04/01/2018.
 */

public interface ICreateWheelView {
    void displayUnsavedChanges(boolean isDisplayed);
    String getNameToCreate();
    void showCreationSuccess();
    void showCreationFailure();
}
