package personal.nmartinez.fr.virtualfootballpicker.consultwheels.core;

import android.app.DialogFragment;
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

import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.EditWheelDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.IConsultWheelsView;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEELS_KEY;
import static personal.nmartinez.fr.virtualfootballpicker.MainActivity.WHEEL_TO_USE;

/**
 * Created by Nicolas on 03/12/2017.
 */

public class ConsultWheelsCore implements IConsultWheelsCore {

    public static final String SERIALIZATION_ERROR = "Un probleme a eu lieu lors de la serialisation";
    private IConsultWheelsView view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ObjectMapper objectMapper;

    public ConsultWheelsCore(IConsultWheelsView view, Context context){
        this.view = view;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Sets the wheel in parameter as the one to use and saves it
     * in the shared preferences
     * @param wheel
     */
    @Override
    public void setWheelToUse(Wheel wheel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(wheel);
        } catch (JsonProcessingException e) {
            this.view.showError(SERIALIZATION_ERROR);
        }

        editor.putString(WHEEL_TO_USE, json);
        editor.commit();
        view.applyChangesInWheels();
    }

    /**
     * Retrieves all the wheels from the shared preferences
     * @return
     */
    @Override
    public List<Wheel> getData() {
        List<Wheel> wheels = new ArrayList<>();
        String wheelsJsonGotten = this.sharedPreferences.getString(WHEELS_KEY, "");
        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Wheel.class);
            wheels = objectMapper.readValue(wheelsJsonGotten, javaType);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return wheels;
    }

    /**
     * Retrieves the selected wheel from the shared preferences
     * @return
     */
    @Override
    public Wheel getSelectedWheel() {
        String wheelJson = sharedPreferences.getString(WHEEL_TO_USE, "");
        ObjectMapper objectMapper = new ObjectMapper();
        Wheel wheel = null;
        try {
            wheel = objectMapper.readValue(wheelJson, Wheel.class);
        } catch (IOException e) {
            this.view.showError(SERIALIZATION_ERROR);
        }

        return wheel;
    }

    /**
     * Opens a dialog with the wheel to edit
     * @param wheel
     * @return
     */
    @Override
    public EditWheelDialog openEdition(Wheel wheel) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = view.getViewActivity().getFragmentManager().beginTransaction();
        Fragment prev = view.getViewActivity().getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        EditWheelDialog editDialog = EditWheelDialog.newInstance(wheel);
        editDialog.show(ft, "dialog");
        return editDialog;
    }

    @Override
    public void removeWheel(Wheel wheel) {
        Wheel favoriteWheel = getSelectedWheel();
        if (favoriteWheel != null && wheel != null && favoriteWheel.getId() == wheel.getId()){
            view.displayCantDeleteFavoriteWheelPopup();
        }
        else{
            List<Wheel> wheels = getData();
            for (int i = 0; i < wheels.size(); i++){
                if (wheel.getId() == wheels.get(i).getId()){
                    wheels.remove(i);
                }
            }

            try {
                String wheelsJson = objectMapper.writeValueAsString(wheels);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(WHEELS_KEY, wheelsJson);
                editor.commit();
                view.displayWheelDeletedPopup(wheel);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Gets the view
     * @return
     */
    @Override
    public IConsultWheelsView getView() {
        return this.view;
    }
}
