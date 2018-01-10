package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;

/**
 * Created by Nicolas on 16/12/2017.
 */

public interface IEditWheelView {
    void displayUnsavedChanges();
    void hideUnsavedChanges();
    void displaySaveSuccess();
    String getEditedWheelName();
}
