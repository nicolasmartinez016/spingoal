package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.EditObjectiveDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.IConsultObjectivesView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.OBJECTIVES_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEELS_KEY;

/**
 * Created by Nicolas on 02/12/2017.
 */

public class ConsultObjectivesCore implements IConsultObjectivesCore {
    private IConsultObjectivesView view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;

    public ConsultObjectivesCore(Context context, IConsultObjectivesView view){
        this.view = view;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void retrieveObjectives() {
        this.view.displayWaitingForData(true);

        this.view.setObjectivesAdapter(getData());

        this.view.displayWaitingForData(false);

    }

    private List<Objective> getData(){
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
    public EditObjectiveDialog openEdition(Objective objective) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = view.getViewActivity().getFragmentManager().beginTransaction();
        Fragment prev = view.getViewActivity().getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        EditObjectiveDialog editDialog = EditObjectiveDialog.newInstance(objective);
        editDialog.setConsultObjectivesCore(this);
        editDialog.show(ft, "dialog");
        return editDialog;
    }

    @Override
    public void removeObjective(Objective objective) {
        List<Wheel> wheels = getWheels();

        boolean canDelete = true;

        for (Wheel wheel : wheels){
            for (Objective objectiveWheel : wheel.getObjectives()){
                if (objectiveWheel.getId() == objective.getId()){
                    canDelete = false;
                    break;
                }
            }
        }

        if (canDelete){
            List<Objective> objectives = getData();
            for (int i = 0; i < objectives.size(); i++){
                if (objectives.get(i).getId() == objective.getId()){
                    objectives.remove(i);
                }
            }

            String objectivesJson = null;
            try {
                objectivesJson = objectMapper.writeValueAsString(objectives);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(OBJECTIVES_KEY, objectivesJson);
            editor.commit();
            view.displayObjectiveDeletedPopup();
        }
        else{
            view.displayCantDeleteObjectivePopup();
        }
    }

    @Override
    public IConsultObjectivesView getView() {
        return this.view;
    }
}
