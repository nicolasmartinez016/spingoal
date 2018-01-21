package personal.nmartinez.fr.virtualfootballpicker.createobjective.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.ICreateObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.utils.SharedPreferencesUtil;

import static personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsCore.SERIALIZATION_ERROR;

/**
 * Created by Nicolas on 09/12/2017.
 */

public class CreateObjectiveCore implements ICreateObjectiveCore {

    private static final String OBJECTIVES_KEY = "objectives_key";
    private ICreateObjectiveView view;
    private Context context;
    private List<Objective> objectives;
    private Objective objective;
    private SharedPreferences sharedPreferences;

    public CreateObjectiveCore(Context context, ICreateObjectiveView view){
        this.context = context;
        this.view = view;
        this.objective = new Objective();
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        loadObjectives();
        objective.setEditable(true);
    }

    /**
     * Checks if the objective we are creating has an available name
     * @param name
     * @return
     */
    @Override
    public boolean isNameAvailable(String name) {
        if (name != null && !name.isEmpty()){
            for (Objective objective : this.objectives){
                if (name.equalsIgnoreCase(objective.getName())){
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
    public void createObjective() {
        if (this.isNameAvailable(this.objective.getName())){
            this.view.hideNameUnavailableError();
            if (objective.getPeriod() == Objective.FIRST_PERIOD ||
                    objective.getPeriod() == Objective.SECOND_PERIOD ||
                    objective.getPeriod() == Objective.BOTH_PERIODS){
                this.view.hidePickAPeriod();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    if (this.objectives != null){
                        this.objective.setId(this.objectives.size() + 1);
                        this.objectives.add(this.objective);
                        String newObjectives = objectMapper.writeValueAsString(objectives);
                        SharedPreferencesUtil.commitPreferenceChange(this.sharedPreferences, OBJECTIVES_KEY, newObjectives);
                        this.view.displayObjectiveCreationSucces();
                    }
                } catch (JsonProcessingException e) {
                    this.view.showError(SERIALIZATION_ERROR);
                }
            }
            else{
                this.view.displayPickAPeriod();
                if (objective.getPeriod() == Objective.FIRST_PERIOD ||
                        objective.getPeriod() == Objective.SECOND_PERIOD ||
                        objective.getPeriod() == Objective.BOTH_PERIODS){
                    this.view.hidePickAPeriod();
                }
                else{
                    this.view.displayPickAPeriod();
                }
            }

        }
        else{
            this.view.displayObjectiveCreationFailure();
            this.view.displayNameUnavailableError();
        }
    }

    /**
     * Retrieves all the objectives from the shared preferences
     * (to add the new one to the list and save it again)
     */
    private void loadObjectives(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Objective.class);
            String objectivesJson = sharedPreferences.getString(OBJECTIVES_KEY,"");
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
     * Sets the name of the objective currently creating
     * @param name
     */
    @Override
    public void setObjectiveName(String name) {
        this.objective.setName(name);
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
}
