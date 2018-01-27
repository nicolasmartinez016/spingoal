package personal.nmartinez.fr.virtualfootballpicker.createobjective.view;


import android.content.res.Resources;
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
import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.dialogs.CreationKoDialog;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.view.dialogs.CreationOkDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateObjectiveFragment extends Fragment implements ICreateObjectiveView {

    private ICreateObjectiveCore core;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private Button validateButton;
    //private ToggleButton isEditableToggleButton;
    private RadioButton firstHalfRadioButton;
    private RadioButton secondHalfRadioButton;
    private RadioButton bothHalvesRadioButton;
    private TextView errorNameTextView;
    private TextView errorPeriodTextView;
    private Button firstPeriodButton;
    private Button secondPeriodButton;
    private Button bothPeriodsButton;
    private Button objectiveEditableButton;
    private Button objectiveNotEditableButton;



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
        //this.descriptionEditText = (EditText) view.findViewById(R.id.create_objective_description_edittext);
        this.validateButton = (Button) view.findViewById(R.id.create_objective_validate_button);
        //this.isEditableToggleButton = (ToggleButton) view.findViewById(R.id.create_objective_editable_togglebutton);
        this.errorNameTextView = (TextView) view.findViewById(R.id.create_objective_name_error_textview);
        this.errorPeriodTextView = (TextView) view.findViewById(R.id.create_objective_period_error_textview);
        this.firstPeriodButton = (Button) view.findViewById(R.id.create_objective_first_period_button);
        this.secondPeriodButton = (Button) view.findViewById(R.id.create_objective_second_period_button);
        this.bothPeriodsButton = (Button) view.findViewById(R.id.create_objective_both_periods_button);
        this.objectiveEditableButton = (Button) view.findViewById(R.id.create_objective_editable_button);
        this.objectiveNotEditableButton = (Button) view.findViewById(R.id.create_objective_not_editable_button);

        this.firstPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.FIRST_PERIOD);

                Resources resources = getActivity().getResources();

                firstPeriodButton.setTextColor(resources.getColor(R.color.white));
                secondPeriodButton.setTextColor(resources.getColor(R.color.secondPeriodColor));
                bothPeriodsButton.setTextColor(resources.getColor(R.color.bothPeriodsColor));

                firstPeriodButton.setBackground(null);
                firstPeriodButton.setBackgroundColor(resources.getColor(R.color.firstPeriodColor));
                secondPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
                bothPeriodsButton.setBackground(resources.getDrawable(R.drawable.button_border));
            }
        });

        this.secondPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.SECOND_PERIOD);

                Resources resources = getActivity().getResources();

                secondPeriodButton.setTextColor(resources.getColor(R.color.white));
                firstPeriodButton.setTextColor(resources.getColor(R.color.firstPeriodColor));
                bothPeriodsButton.setTextColor(resources.getColor(R.color.bothPeriodsColor));

                secondPeriodButton.setBackground(null);
                secondPeriodButton.setBackgroundColor(resources.getColor(R.color.secondPeriodColor));
                firstPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
                bothPeriodsButton.setBackground(resources.getDrawable(R.drawable.button_border));
            }
        });

        this.bothPeriodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.BOTH_PERIODS);

                Resources resources = getActivity().getResources();

                bothPeriodsButton.setTextColor(resources.getColor(R.color.white));
                secondPeriodButton.setTextColor(resources.getColor(R.color.secondPeriodColor));
                firstPeriodButton.setTextColor(resources.getColor(R.color.firstPeriodColor));

                bothPeriodsButton.setBackground(null);
                bothPeriodsButton.setBackgroundColor(resources.getColor(R.color.bothPeriodsColor));
                secondPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
                firstPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
            }
        });

//        this.firstHalfRadioButton = (RadioButton) view.findViewById(R.id.create_objective_first_period_radiobutton);
//        this.secondHalfRadioButton = (RadioButton) view.findViewById(R.id.create_objective_second_period_radiobutton);
//        this.bothHalvesRadioButton = (RadioButton) view.findViewById(R.id.create_objective_both_periods_radiobutton);

//        this.firstHalfRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    secondHalfRadioButton.setChecked(false);
//                    bothHalvesRadioButton.setChecked(false);
//                    core.setObjectivePeriod(Objective.FIRST_PERIOD);
//                }
//                else{
//                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
//                }
//            }
//        });
//
//        this.secondHalfRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    firstHalfRadioButton.setChecked(false);
//                    bothHalvesRadioButton.setChecked(false);
//                    core.setObjectivePeriod(Objective.SECOND_PERIOD);
//                }
//                else{
//                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
//                }
//            }
//        });
//
//        this.bothHalvesRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    secondHalfRadioButton.setChecked(false);
//                    firstHalfRadioButton.setChecked(false);
//                    core.setObjectivePeriod(Objective.BOTH_PERIODS);
//                }
//                else{
//                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
//                }
//            }
//        });

//        this.isEditableToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                core.setObjectiveEditable(b);
//            }
//        });

        this.objectiveEditableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectiveEditable(true);
                Resources resources = getActivity().getResources();
                objectiveEditableButton.setTextColor(resources.getColor(R.color.white));
                objectiveEditableButton.setBackground(null);
                objectiveEditableButton.setBackgroundColor(resources.getColor(R.color.main_green));
                objectiveNotEditableButton.setTextColor(resources.getColor(R.color.main_green));
                objectiveNotEditableButton.setBackground(resources.getDrawable(R.drawable.button_border));
            }
        });

        this.objectiveNotEditableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectiveEditable(false);
                Resources resources = getActivity().getResources();
                objectiveNotEditableButton.setTextColor(resources.getColor(R.color.white));
                objectiveNotEditableButton.setBackground(null);
                objectiveNotEditableButton.setBackgroundColor(resources.getColor(R.color.main_green));
                objectiveEditableButton.setTextColor(resources.getColor(R.color.main_green));
                objectiveEditableButton.setBackground(resources.getDrawable(R.drawable.button_border));
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
        new CreationOkDialog().show(getFragmentManager(), "");
    }

    @Override
    public void displayObjectiveCreationFailure() {
        new CreationKoDialog().show(getFragmentManager(), "");
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
