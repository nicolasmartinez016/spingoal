package personal.nmartinez.fr.virtualfootballpicker.consultwheels.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.IEditWheelView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.utils.SharedPreferencesUtil;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.OBJECTIVES_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEELS_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEEL_TO_USE;

/**
 * Created by Nicolas on 16/12/2017.
 */

public class EditWheelCore implements IEditWheelCore {
    private Wheel wheel;
    private List<Objective> objectives;
    private IEditWheelView view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;


    public EditWheelCore(Context context, Wheel wheel, IEditWheelView view){
        this.context = context;
        this.wheel = wheel;
        this.view = view;
        this.sharedPreferences = this.context.getSharedPreferences("", Context.MODE_PRIVATE);
        this.objectMapper = new ObjectMapper();
        setupObjectives();
    }

    /**
     * If the given objective already is in the wheel's objectives, removes it
     * Else, adds it
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

        this.view.displayUnsavedChanges();
    }

    /**
     * Saves the edited wheel in the shared preferences
     * Hides the text warning the user about the unsaved changes
     */
    @Override
    public boolean saveChanges() {
        this.wheel.setName(this.view.getEditedWheelName());
        if (isNameAvailable(getWheels())){
            this.applyChanges();
            this.view.hideUnsavedChanges();
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Gets the wheel object
     * @return
     */
    @Override
    public Wheel getWheel() {
        return this.wheel;
    }


    /**
     * Gets the list of objectives
     * @return
     */
    @Override
    public List<Objective> getObjectives() {
        return this.objectives;
    }

    /**
     * Checks whether a given objective is in the wheel to edit
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
     * Updates the shared preferences to set the edited wheel in both
     * the list of all wheels and the selected wheel if needed
     */
    private void applyChanges(){
        applyChangesWheelToUse();
        applyChangesWheels();
    }


    /**
     * Saves the changes about the wheel to edit
     * in the shared preference about all the wheels
     */
    private void applyChangesWheels(){
        List<Wheel> wheels = getWheels();
        for (int i = 0; i < wheels.size(); i++){
            if (wheels.get(i).getId() == this.wheel.getId()){
                wheels.set(i, this.wheel);
            }
        }

        try {
            String wheelsJson = this.objectMapper.writeValueAsString(wheels);
            SharedPreferencesUtil.commitPreferenceChange(this.sharedPreferences, WHEELS_KEY, wheelsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the changes about the wheel to edit
     * in the shared preference about the wheel to use
     */
    private void applyChangesWheelToUse(){
        String wheelToUseJson = this.sharedPreferences.getString(WHEEL_TO_USE, "");

        try {
            Wheel wheelToUse = this.objectMapper.readValue(wheelToUseJson, Wheel.class);
            if (wheelToUse.getId() == this.wheel.getId()){
                wheelToUseJson = this.objectMapper.writeValueAsString(wheelToUse);
                SharedPreferencesUtil.commitPreferenceChange(this.sharedPreferences, WHEEL_TO_USE, wheelToUseJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instanciates the list of objetives object with all the objectives the user has
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

    private boolean isNameAvailable(List<Wheel> wheels){
        String name = this.wheel.getName();
        if (name.isEmpty()){
            return false;
        }

        for (Wheel localWheel : wheels){
            if (localWheel.getName().equalsIgnoreCase(name)){
                if (localWheel.getId() != this.wheel.getId()){
                    return false;
                }
            }
        }

        return true;
    }
}
