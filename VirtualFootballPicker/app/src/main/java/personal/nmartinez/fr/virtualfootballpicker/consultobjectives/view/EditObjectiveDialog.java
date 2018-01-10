package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.EditObjectiveCore;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.IEditObjectiveCore;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 29/12/2017.
 */

public class EditObjectiveDialog extends DialogFragment implements IEditObjectiveView {

    private static String OBJECTIVE_KEY = "objective";

    private IEditObjectiveCore core;

    private EditText objectiveNameEditText;
    private RadioButton objective1stPeriodRadioButton;
    private RadioButton objective2ndPeriodRadioButton;
    private RadioButton objectiveBothPeriodsRadioButton;
    private Button editObjectiveButton;
    private TextView editObjectiveValueTextview;
    private TextView unsavedChangesTextView;

    public static EditObjectiveDialog newInstance(Objective objective){
        EditObjectiveDialog dialog = new EditObjectiveDialog();
        Bundle args = new Bundle();
        args.putSerializable(OBJECTIVE_KEY, objective);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objective objective = (Objective)getArguments().getSerializable(OBJECTIVE_KEY);
        this.core = new EditObjectiveCore(getActivity(), objective, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_objective_layout, container, false);

        objectiveNameEditText = (EditText) view.findViewById(R.id.edit_objective_name_edittext);
        objective1stPeriodRadioButton = (RadioButton) view.findViewById(R.id.edit_objective_1st_period_radiobutton);
        objective2ndPeriodRadioButton = (RadioButton) view.findViewById(R.id.edit_objective_2nd_period_radiobutton);
        objectiveBothPeriodsRadioButton = (RadioButton) view.findViewById(R.id.edit_objective_both_periods_radiobutton);
        editObjectiveButton = (Button) view.findViewById(R.id.edit_objective_apply_button);
        unsavedChangesTextView = (TextView) view.findViewById(R.id.objective_unsaved_changes_textview);
        editObjectiveValueTextview = (TextView) view.findViewById(R.id.edit_objective_value_textview);

        core.checkObjectivePeriod();
        core.checkObjectiveName();

        objective1stPeriodRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    objective2ndPeriodRadioButton.setChecked(false);
                    objectiveBothPeriodsRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.FIRST_PERIOD);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        objective2ndPeriodRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    objective1stPeriodRadioButton.setChecked(false);
                    objectiveBothPeriodsRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.SECOND_PERIOD);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        objectiveBothPeriodsRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    objective2ndPeriodRadioButton.setChecked(false);
                    objective1stPeriodRadioButton.setChecked(false);
                    core.setObjectivePeriod(Objective.BOTH_PERIODS);
                }
                else{
                    core.setObjectivePeriod(Objective.PERIOD_ERROR);
                }
            }
        });

        editObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectiveName(objectiveNameEditText.getText().toString());
                core.saveObjective();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        dismissAllowingStateLoss();
        super.onPause();
    }

    /**
     * When initializing the view, checks the right
     * period radiobutton according to the objectives period
     * @param period
     */
    @Override
    public void checkPeriodRadioButton(int period) {
        if (period == Objective.FIRST_PERIOD){
            this.objective1stPeriodRadioButton.setChecked(true);
        }
        else if (period == Objective.SECOND_PERIOD){
            this.objective2ndPeriodRadioButton.setChecked(true);
        }
        else if (period == Objective.BOTH_PERIODS){
            this.objectiveBothPeriodsRadioButton.setChecked(true);
        }
    }

    /**
     * Fills edit text with the objectives current name
     * Same for the title
     * @param name
     */
    @Override
    public void fillObjectiveName(String name) {
        this.objectiveNameEditText.setText(name);
        this.editObjectiveValueTextview.setText(" " + name);
    }
}
