package personal.nmartinez.fr.virtualfootballpicker.createwheels.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters.EditWheelAdapter;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.dialogs.CreationOkDialog;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.adapters.CreateWheelAdapter;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.core.CreateWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.core.ICreateWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.view.dialogs.CreationWheelKoDialog;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.view.dialogs.CreationWheelOkDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateWheelFragment extends Fragment implements ICreateWheelView {

    private ICreateWheelCore core;

    private EditText createWheelNameEditText;
    private Button createWheelButton;
    private RecyclerView createWheelObjectivesRecyclerView;
    private TextView createWheelUnsavedChangesTextView;

    public CreateWheelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.core = new CreateWheelCore(getActivity(), this);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_wheel, container, false);

        this.createWheelButton = (Button) view.findViewById(R.id.create_wheel_button);
        this.createWheelNameEditText = (EditText) view.findViewById(R.id.create_wheel_name_edittext);
        this.createWheelObjectivesRecyclerView = (RecyclerView) view.findViewById(R.id.create_wheel_objectives_recyclerview);
        this.createWheelUnsavedChangesTextView = (TextView) view.findViewById(R.id.create_wheel_unsaved_changees_textview);

        createWheelObjectivesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        createWheelObjectivesRecyclerView.setHasFixedSize(true);
        CreateWheelAdapter adapter = new CreateWheelAdapter(this.core, getActivity());
        createWheelObjectivesRecyclerView.setAdapter(adapter);

        this.createWheelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.applyCreation();
            }
        });

        return view;
    }

    /**
     * Displays the textview warning the user that there are some unsaved changes
     * @param isDisplayed
     */
    @Override
    public void displayUnsavedChanges(boolean isDisplayed) {
        if (isDisplayed){
            this.createWheelUnsavedChangesTextView.setVisibility(View.VISIBLE);
        }
        else{
            this.createWheelUnsavedChangesTextView.setVisibility(View.GONE);
        }
    }

    /**
     * Gets what the user wrote as the name of the wheel
     * @return
     */
    @Override
    public String getNameToCreate() {
        return this.createWheelNameEditText.getText().toString();
    }

    @Override
    public void showCreationSuccess() {
        new CreationWheelOkDialog().show(getFragmentManager(), "");
    }

    @Override
    public void showCreationFailure() {
        new CreationWheelKoDialog().show(getFragmentManager(), "");
    }
}
