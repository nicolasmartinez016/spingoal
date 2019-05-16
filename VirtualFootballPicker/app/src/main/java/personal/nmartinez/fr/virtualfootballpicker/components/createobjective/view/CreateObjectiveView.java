package personal.nmartinez.fr.virtualfootballpicker.components.createobjective.view;

import android.arch.lifecycle.LifecycleOwner;

import personal.nmartinez.fr.virtualfootballpicker.data.models.ObjectiveModel;

/**
 * Created by Nicolas on 09/12/2017.
 */

public interface CreateObjectiveView extends LifecycleOwner {
    void displayNameUnavailableError();
    void hideNameUnavailableError();
    void displayObjectiveCreationSucces(boolean isEditing);
    void displayPickAPeriod();
    void hidePickAPeriod();
    void displayObjectiveCreationFailure(boolean isEditing);
    void showError(String message);
    void initViews(ObjectiveModel objective, boolean isEditing);

}
