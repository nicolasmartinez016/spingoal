package personal.nmartinez.fr.virtualfootballpicker.createobjective.core;

import android.arch.lifecycle.LiveData;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.CreateObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.CreateObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.UpdateObjectiveRepositoryListener;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.virtualfootballpicker.data.repositories.objective.ObjectiveRepositoryImpl;
import personal.nmartinez.fr.virtualfootballpicker.models.ObjectiveModel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * Created by Nicolas on 09/12/2017.
 */

public class CreateObjectivePresenterImpl implements CreateObjectivePresenter, CreateObjectiveRepositoryListener, UpdateObjectiveRepositoryListener {

    private CreateObjectiveView view;
    private List<Objective> objectives;
    private Objective objective;
    private ObjectiveRepository objectiveRepository;
    private boolean isEditing;

    public CreateObjectivePresenterImpl(CreateObjectiveView view, Objective objective){
        this.objectiveRepository = new ObjectiveRepositoryImpl();
        this.view = view;
        if (objective != null) {
            this.objective = objective;
            isEditing = true;
        } else {
            this.objective = new Objective();
            this.objective.setEditable(true);
            this.objective.setPeriod(Objective.FIRST_PERIOD);
        }
        LiveData<List<Objective>> listLiveData = objectiveRepository.getObjectives();
        if (listLiveData != null) {
            listLiveData.observe(this.view, objectivesList -> {
                objectives = objectivesList;
            });
        }
    }

    /**
     * Checks if the objective we are creating has an available name
     * @param name
     * @return
     */
    private boolean isNameAvailable(String name) {
        if (name != null && !name.isEmpty()){
            for (Objective objective : this.objectives){
                if (name.equalsIgnoreCase(objective.getName()) && objective.getId() != this.objective.getId()){
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Checks if all the required fields are filled
     * If so, adds the new objective to the list of all of them
     * and saves the list again in the shared preferences
     * Else, notifies the view with the right error messages to display
     */
    @Override
    public void createObjective(ObjectiveModel objectiveModel) {
        if (objectiveModel != null && objective != null) {
            if (StringUtils.isNullOrEmpty(objectiveModel.getName())) {
                this.view.displayNameUnavailableError();
                this.view.displayObjectiveCreationFailure(isEditing);
            } else if (objectiveModel.getName().equalsIgnoreCase(objective.getName())) {
                this.view.displayNameUnavailableError();
                this.view.displayObjectiveCreationFailure(isEditing);
            } else {
                this.objective = objectiveModel.toObjective();
                if (isEditing) {
                    objectiveRepository.updateObjective(this.objective, this);
                    this.view.displayObjectiveCreationSucces(isEditing);
                } else {
                    this.objective.setId(this.objectives.size() + 1);
                    if (this.isNameAvailable(this.objective.getName())) {
                        this.view.hideNameUnavailableError();
                        if (objective.getPeriod() == Objective.FIRST_PERIOD ||
                                objective.getPeriod() == Objective.SECOND_PERIOD ||
                                objective.getPeriod() == Objective.BOTH_PERIODS) {
                            this.view.hidePickAPeriod();
                            objectiveRepository.createObjective(this.objective, this);
                            this.view.displayObjectiveCreationSucces(isEditing);
                        } else {
                            this.view.displayObjectiveCreationFailure(isEditing);
                            this.view.displayPickAPeriod();
                        }
                    } else {
                        this.view.displayObjectiveCreationFailure(isEditing);
                        this.view.displayNameUnavailableError();
                    }
                }
            }
        }
    }

    /**
     * Sets the period of the objective currently creating
     * @param period
     */
    @Override
    public void setObjectivePeriod(int period) {
        this.objective.setPeriod(period);
    }

    /**
     * Sets if the objective currently creating is editable
     * @param isEditable
     */
    @Override
    public void setObjectiveEditable(boolean isEditable) {
        this.objective.setEditable(isEditable);
    }

    @Override
    public void initViews() {
        if (view != null) {
            view.initViews(ObjectiveModel.fromObjective(objective), isEditing);
        }
    }

    @Override
    public void onCreateObjectiveSuccess() {
        if (this.view != null) {
            this.view.displayObjectiveCreationSucces(false);
        }
    }

    @Override
    public void onCreateObjectiveFailure() {
        if (this.view != null) {
            this.view.displayObjectiveCreationFailure(false);
        }
    }

    @Override
    public void onUpdateObjectiveSuccess() {
        if (this.view != null) {
            this.view.displayObjectiveCreationSucces(true);
        }
    }

    @Override
    public void onUpdateObjectiveFailure() {
        if (this.view != null) {
            this.view.displayObjectiveCreationFailure(true);
        }
    }
}
