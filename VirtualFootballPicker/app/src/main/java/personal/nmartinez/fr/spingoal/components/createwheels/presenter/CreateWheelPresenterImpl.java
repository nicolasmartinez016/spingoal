package personal.nmartinez.fr.spingoal.components.createwheels.presenter;

import java.util.List;

import personal.nmartinez.fr.spingoal.components.createwheels.view.CreateWheelView;
import personal.nmartinez.fr.spingoal.data.repositories.objective.interfaces.ObjectiveRepository;
import personal.nmartinez.fr.spingoal.data.repositories.objective.ObjectiveRepositoryImpl;
import personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces.CreateWheelRepositoryListener;
import personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces.UpdateWheelRepositoryListener;
import personal.nmartinez.fr.spingoal.data.models.Objective;
import personal.nmartinez.fr.spingoal.data.models.Wheel;
import personal.nmartinez.fr.spingoal.data.repositories.wheel.interfaces.WheelRepository;
import personal.nmartinez.fr.spingoal.data.repositories.wheel.WheelRepositoryImpl;
import personal.nmartinez.fr.spingoal.data.models.WheelModel;
import personal.nmartinez.fr.spingoal.utils.StringUtils;

/**
 * Created by Nicolas on 04/01/2018.
 */

public class CreateWheelPresenterImpl implements CreateWheelPresenter, CreateWheelRepositoryListener, UpdateWheelRepositoryListener {

    private Wheel wheel;

    private CreateWheelView view;
    private List<Objective> objectives;
    private boolean isEditing;
    WheelRepository wheelRepository;
    ObjectiveRepository objectiveRepository;
    private List<Wheel> wheels;
    private int objectivesSelected;

    public CreateWheelPresenterImpl(CreateWheelView view, Wheel wheel){
        this.view = view;
        this.objectiveRepository = new ObjectiveRepositoryImpl();
        this.wheelRepository = new WheelRepositoryImpl();
        this.wheel = wheel;
        if (wheel == null) {
            this.wheel = new Wheel();
            this.wheel.setEditable(true);
        } else {
            objectiveRepository.getObjectivesByWheel(this.wheel.getId()).observe(this.view, objectivesRetrieved -> {
                this.wheel.setObjectives(objectivesRetrieved);
                objectivesSelected = objectivesRetrieved.size();
            });
        }
        this.isEditing = wheel != null;

        wheelRepository.getWheels().observe(view, wheelsRetrieved -> {
            wheels = wheelsRetrieved;
            if (!this.isEditing && wheels != null && this.wheel != null) {
                this.wheel.setId(wheels.size() + 1);
            }
        });
    }

    @Override
    public void initViews() {

        this.objectiveRepository.getObjectives().observe(this.view, objectives -> {
            this.objectives = objectives;
            WheelModel wheelModel = WheelModel.fromWheel(wheel);
            wheelModel.setEditing(isEditing);
            wheelModel.setAllObjectives(objectives);
            view.initViews(wheelModel);
        });
    }

    /**
     * Gets the name from the view, checks if it is available
     * if yes, saves the wheel in the shared preferences
     * else, notifies the view that the name is already taken
     */
    @Override
    public void createWheel(WheelModel wheelModel) {
        if (this.wheel != null && wheelModel != null) {
            this.wheel.setName(wheelModel.getName());
            if (isWheelNameAvailable()) {
                saveWheel();
            } else {
                view.showNameNotAvailable();
                view.showCreationFailure();
            }
        }

    }


    private boolean isWheelNameAvailable() {
        if (StringUtils.isNullOrEmpty(this.wheel.getName())) {
            return false;
        }

        for (Wheel wheel : this.wheels) {
            if (wheel.getName().equalsIgnoreCase(this.wheel.getName()) && wheel.getId() != this.wheel.getId()) {
                return false;
            }
        }

        return true;
    }

    /**
     * If the given objective is in the wheel's objective, removes it
     * Else, adds it to the wheel
     * @param objective
     */
    @Override
    public void addOrRemoveObjective(Objective objective) {
        if (this.wheel.getObjectives().contains(objective)){
            this.wheel.getObjectives().remove(objective);
            objectivesSelected = objectivesSelected - 1;
        }
        else{
            this.wheel.getObjectives().add(objective);
            objectivesSelected = objectivesSelected + 1;
        }
        checkSelectAll();
    }

    @Override
    public void addOrRemoveAllObjectives(boolean shouldAdd) {
        if (this.wheel != null && this.objectives != null) {
            if (shouldAdd) {
                for (Objective objective : this.objectives) {
                    if (!this.wheel.getObjectives().contains(objective)) {
                        this.wheel.getObjectives().add(objective);
                    }
                }
            } else {
                this.wheel.getObjectives().clear();
            }
            WheelModel wheelModel = WheelModel.fromWheel(wheel);
            wheelModel.setEditing(isEditing);
            wheelModel.setAllObjectives(objectives);
            this.view.initViews(wheelModel);
        }
    }

    /**
     * Checks if a specific objective is in the wheel currently creating
     * @param objective
     * @return
     */
    @Override
    public boolean isObjectiveInWheel(Objective objective) {
        for (Objective objectiveTmp : this.wheel.getObjectives()){
            if (objectiveTmp.getId() == objective.getId()){
                return true;
            }
        }

        return false;
    }

    private void saveWheel() {
        if (isEditing) {
            wheelRepository.updateWheel(wheel, this);
        } else {
            wheelRepository.createWheel(wheel, this);
        }

    }

    private void checkSelectAll() {
        if (this.view != null && this.wheel != null && this.wheel.getObjectives() != null && this.objectives != null) {
            this.view.checkOrUncheckSelectAll(objectives.size() == objectivesSelected);
        }
    }

    @Override
    public void onCreateWheelSuccess() {
        view.showCreationSuccess();
    }

    @Override
    public void onCreateWheelFailure() {
        view.showCreationFailure();
    }

    @Override
    public void onUpdateWheelSuccess() {
        view.showCreationSuccess();
    }

    @Override
    public void onUpdateWheelFailure() {
        view.showCreationFailure();
    }
}
