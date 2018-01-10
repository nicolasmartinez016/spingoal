package personal.nmartinez.fr.virtualfootballpicker.createobjective.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.core.CreateObjectiveCore;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.core.ICreateObjectiveCore;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateObjectiveFragment extends Fragment implements ICreateObjectiveView {

    private ICreateObjectiveCore core;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private Button validateButton;
    private ToggleButton isEditableToggleButton;
    private RadioButton firstHalfRadioButton;
    private RadioButton secondHalfRadioButton;
    private RadioButton bothHalvesRadioButton;
    private TextView errorNameTextView;
    private TextView errorPeriodTextView;



    public CreateObjectiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.core = new CreateObjectiveCore(getActivity(), this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_objective, container, false);

        this.nameEditText = (EditText) view.findViewById(R.id.create_objective_name_edittext);
        this.descriptionEditText = (EditText) view.findViewById(R.id.create_objective_description_edittext);
        this.validateButton = (Button) view.findViewById(R.id.create_objective_validate_button);
        this.isEditableToggleButton = (ToggleButton) view.findViewById(R.id.create_objective_editable_togglebutton);
        this.errorNameTextView = (TextView) view.findViewById(R.id.create_objective_name_error_textview);
        this.errorPeriodTextView = (TextView) view.findViewById(R.id.create_objective_period_error_textview);
        this.firstHalfRadioButton = (RadioButton) view.findViewById(R.id.create_objective_first_period_radiobutton);
        this.secondHalfRadioButton = (RadioButton) view.findViewById(R.id.create_objective_second_period_radiobutton);
        this.bothHalvesRadioButton = (RadioButton) view.findViewById(R.id.create_objective_both_periods_radiobutton);

        this.firstHalfRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    secondHalfRadioButton.setChecked(false);
                    bothHalvesRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.FIRST_PERIOD);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        this.secondHalfRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    firstHalfRadioButton.setChecked(false);
                    bothHalvesRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.SECOND_PERIOD);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        this.bothHalvesRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    secondHalfRadioButton.setChecked(false);
                    firstHalfRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.BOTH_PERIODS);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        this.isEditableToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                core.setObjectiveEditable(b);
            }
        });

        this.validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectiveName(nameEditText.getText().toString());
                core.createObjective();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Displays the warning message that the name the user chose is unavailable
     */
    @Override
    public void displayNameUnavailableError() {
        this.errorNameTextView.setText("Ce nom est déjà utilisé par un autre gage (ou vide)");
        this.errorNameTextView.setVisibility(View.VISIBLE);
    }

    /**
     * Notifies the user that his objective has been created
     */
    @Override
    public void displayObjectiveCreationSucces() {
        Toast.makeText(getActivity(), "Gage cree", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayObjectiveCreationFailure() {

    }

    @Override
    public void showError(String message) {

    }

    /**
     * Displays the warning message that the user did not pick a period
     */
    @Override
    public void displayPickAPeriod() {
        this.errorPeriodTextView.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the warning message that the user chose an unavailable name
     */
    @Override
    public void hideNameUnavailableError() {
        this.errorNameTextView.setVisibility(View.GONE);
    }

    /**
     * Hides the warning message that the user did not pick a period
     */
    @Override
    public void hidePickAPeriod() {
        this.errorPeriodTextView.setVisibility(View.GONE);
    }
}
