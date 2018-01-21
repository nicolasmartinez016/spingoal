package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.IEditObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.dialogs.EditObjectiveOkDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.OBJECTIVES_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEELS_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEEL_TO_USE;

/**
 * Created by Nicolas on 29/12/2017.
 */

public class EditObjectiveCore implements IEditObjectiveCore {

    private Objective objective;
    private IEditObjectiveView view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;
    private IConsultObjectivesCore consultObjectivesCore;

    public EditObjectiveCore(Context context, Objective objective, IEditObjectiveView view){
        this.context = context;
        this.view = view;
        this.objective = objective;
        this.objectMapper = new ObjectMapper();
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
    }

    @Override
    public void setObjectivePeriod(int period) {
        objective.setPeriod(period);
    }

    @Override
    public void setObjectiveName(String name) {
        objective.setName(name);
    }

    /**
     * Saves the objective edited in the list of all objectives
     * and in the list of all wheels and in the wheel to use if needed
     * in the shared preferences
     */
    @Override
    public boolean saveObjective() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        List<Objective> objectives = getObjectives();

        if (isNameAvailable(objectives)){
            objectives = editObjectiveInList(objectives);
            try {
                editor.putString(OBJECTIVES_KEY, objectMapper.writeValueAsString(objectives));
                editor.commit();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            List<Wheel> wheels = getWheels();
            editObjectiveInWheels(wheels);

            try {
                editor.putString(WHEELS_KEY, objectMapper.writeValueAsString(wheels));
                editor.commit();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            String wheelToUseJson = this.sharedPreferences.getString(WHEEL_TO_USE, "");

            try {
                Wheel wheelToUse = this.objectMapper.readValue(wheelToUseJson, Wheel.class);
                editObjectiveInWheel(wheelToUse);
                wheelToUseJson = this.objectMapper.writeValueAsString(wheelToUse);
                editor.putString(WHEEL_TO_USE, wheelToUseJson);
                editor.commit();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Changes the objective edited in the wheels in parameter
     * @param wheels
     */
    private void editObjectiveInWheels(List<Wheel> wheels){
        for (Wheel wheel : wheels){
            editObjectiveInWheel(wheel);
        }
    }

    /**
     * Changes the objective edited in the wheel in parameter
     * @param wheel
     */
    private void editObjectiveInWheel(Wheel wheel){
        for (int i = 0; i < wheel.getObjectives().size(); i++){
            if (this.objective.getId() == wheel.getObjectives().get(i).getId()){
                wheel.getObjectives().set(i, this.objective);
            }
        }
    }

    /**
     * Changes the objective edited in the list in parameter
     * @param objectives
     * @return
     */
    private List<Objective> editObjectiveInList(List<Objective> objectives){
        for (int i = 0; i < objectives.size(); i++){
            if (this.objective.getId() == objectives.get(i).getId()){
                objectives.set(i, this.objective);
            }
        }

        return objectives;
    }

    /**
     * Retrieves the list of all objectives from the shared preferences
     * @return
     */
    private List<Objective> getObjectives(){
        List<Objective> objectives = new ArrayList<>();
        String objectivesJson = this.sharedPreferences.getString(OBJECTIVES_KEY, "");
        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Objective.class);
            objectives = objectMapper.readValue(objectivesJson, javaType);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return objectives;
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

    @Override
    public void checkObjectivePeriod() {
        this.view.checkPeriodRadioButton(this.objective.getPeriod());
    }

    @Override
    public void checkObjectiveName() {
        this.view.fillObjectiveName(this.objective.getName());
    }

    private boolean isNameAvailable(List<Objective> objectives){
        String name = this.objective.getName();
        if (name.isEmpty()){
            return false;
        }

        for (Objective localObjective : objectives){
            if (localObjective.getName().equalsIgnoreCase(name)){
                return false;
            }
        }

        return true;
    }
}
