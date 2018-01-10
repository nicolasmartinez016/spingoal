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
//        if (StringUtils.isNullOrEmpty(this.sharedPreferences.getString(OBJECTIVES_KEY, ""))){
//            SharedPreferences.Editor editor = this.sharedPreferences.edit();
//            for (int i = 0; i < 24; i++){
//                Objective objective = new Objective();
//                objective.setName("Gage " + i);
//                objective.setId(i + 1);
//                if (i%2 == 0){
//                    objective.setEditable(true);
//                }
//                objectives.add(objective);
//            }
//            try {
//                String objectivesJson = this.objectMapper.writeValueAsString(objectives);
//                editor.putString(OBJECTIVES_KEY, objectivesJson);
//                editor.commit();
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }

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
        editDialog.show(ft, "dialog");
        return editDialog;
    }
}
