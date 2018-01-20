package personal.nmartinez.fr.virtualfootballpicker.createwheels.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.createwheels.view.ICreateWheelView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.utils.SharedPreferencesUtil;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.OBJECTIVES_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEELS_KEY;

/**
 * Created by Nicolas on 04/01/2018.
 */

public class CreateWheelCore implements ICreateWheelCore {

    private Wheel wheel;

    private Context context;
    private ICreateWheelView view;
    private List<Objective> objectives;
    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;

    public CreateWheelCore(Context context, ICreateWheelView view){
        this.context = context;
        this.view = view;
        this.objectMapper = new ObjectMapper();
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        this.wheel = new Wheel();
        setupObjectives();
    }

    /**
     * Gets the name from the view, checks if it is available
     * if yes, saves the wheel in the shared preferences
     * else, notifies the view that the name is already taken
     */
    @Override
    public void applyCreation() {
        String nameToCreate = this.view.getNameToCreate();
        List<Wheel> wheels = getWheels();

        boolean isNameAvailable = true;
        if (nameToCreate.isEmpty()){
            isNameAvailable = false;
        }
        else{
            for (Wheel wheel : wheels){
                if (wheel.getName().equalsIgnoreCase(nameToCreate)){
                    isNameAvailable = false;
                    break;
                }
            }
        }

        if (isNameAvailable){
            this.wheel.setName(nameToCreate);
            this.wheel.setId(wheels.size() + 1);
            wheels.add(this.wheel);
            saveWheels(wheels);
            view.displayUnsavedChanges(false);
            view.showCreationSuccess();
        }
        else{
            view.showCreationFailure();
        }
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
        }
        else{
            this.wheel.getObjectives().add(objective);
        }

        this.view.displayUnsavedChanges(true);
    }

    @Override
    public List<Objective> getAllObjectives() {
        return this.objectives;
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

    /**
     * Retrieves all the objectives from the shared preferences
     */
    private void setupObjectives(){
        String objectivesJson = this.sharedPreferences.getString(OBJECTIVES_KEY, "");
        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Objective.class);
            List<Objective> objectives = objectMapper.readValue(objectivesJson, javaType);
            if (objectives != null){
                this.objectives = objectives;
            }
            else{
                this.objectives = new ArrayList<>();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the wheels of the user from the shared preference
     * @return
     */
    private List<Wheel> getWheels(){
        List<Wheel> wheels = new ArrayList<>();
        String wheelsJson = this.sharedPreferences.getString(WHEELS_KEY, "");
        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Wheel.class);
            wheels = objectMapper.readValue(wheelsJson, javaType);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return wheels;
    }

    /**
     * Saves the wheels in the shared preferences
     * @param wheels
     */
    private void saveWheels(List<Wheel> wheels){
        try {
            String wheelsJson = this.objectMapper.writeValueAsString(wheels);
            SharedPreferencesUtil.commitPreferenceChange(this.sharedPreferences, WHEELS_KEY, wheelsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
