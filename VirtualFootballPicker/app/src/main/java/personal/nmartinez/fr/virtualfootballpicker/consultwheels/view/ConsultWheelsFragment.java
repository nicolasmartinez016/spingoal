package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters.ConsultWheelsAdapter;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.IConsultWheelsCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.dialogs.CantDeleteWheelDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.dialogs.WheelDeletedDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultWheelsFragment extends Fragment implements IConsultWheelsView {

    private IConsultWheelsCore core;

    private ExpandableListView consultWheelsExpandableListview;
    private ProgressBar consultWheelsProgressBar;
    private TextView consultWheelsWaitingTextView;

    public ConsultWheelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.core = new ConsultWheelsCore(this, this.getActivity());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consult_wheels, container, false);
        this.consultWheelsExpandableListview = (ExpandableListView) view.findViewById(R.id.consult_wheels_expandable_listview);
        ConsultWheelsAdapter adapter = new ConsultWheelsAdapter(this.getActivity(), this.core);
        this.consultWheelsExpandableListview.setAdapter(adapter);
        return view;
    }

    @Override
    public void showError(String message) {

    }

    /**
     * Reloads the recyclerview with all the wheels
     */
    @Override
    public void applyChangesInWheels() {
        this.consultWheelsExpandableListview.setAdapter(new ConsultWheelsAdapter(this.getActivity(), this.core));
    }

    @Override
    public void displayCantDeleteFavoriteWheelPopup() {
        new CantDeleteWheelDialog().show(getFragmentManager(), "");
        applyChangesInWheels();
    }

    @Override
    public void displayWheelDeletedPopup(Wheel wheel) {
        new WheelDeletedDialog().show(getFragmentManager(), "");
        applyChangesInWheels();
    }

    /**
     * Gets the activity
     * @return
     */
    @Override
    public Activity getViewActivity() {
        return getActivity();
    }
}
