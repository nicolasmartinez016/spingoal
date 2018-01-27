package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
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
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.IConsultObjectivesCore;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.IEditObjectiveCore;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.dialogs.EditObjectiveKoDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.dialogs.EditObjectiveOkDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 29/12/2017.
 */

public class EditObjectiveDialog extends DialogFragment implements IEditObjectiveView {

    private static String OBJECTIVE_KEY = "objective";

    private IEditObjectiveCore core;
    private IConsultObjectivesCore consultObjectivesCore;

    private EditText objectiveNameEditText;
    private Button objective1stPeriodButton;
    private Button objective2ndPeriodButton;
    private Button objectiveBothPeriodsButton;
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
        objective1stPeriodButton = (Button) view.findViewById(R.id.edit_objective_first_period_button);
        objective2ndPeriodButton = (Button) view.findViewById(R.id.edit_objective_second_period_button);
        objectiveBothPeriodsButton = (Button) view.findViewById(R.id.edit_objective_both_periods_button);
        editObjectiveButton = (Button) view.findViewById(R.id.edit_objective_apply_button);
        unsavedChangesTextView = (TextView) view.findViewById(R.id.objective_unsaved_changes_textview);
        editObjectiveValueTextview = (TextView) view.findViewById(R.id.edit_objective_value_textview);

        core.checkObjectivePeriod();
        core.checkObjectiveName();

        objective1stPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.BOTH_PERIODS);
                displayFirstPeriodButtonChecked();
            }
        });

        objective2ndPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.BOTH_PERIODS);
                displaySecondPeriodButtonChecked();
            }
        });

        objectiveBothPeriodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectivePeriod(Objective.BOTH_PERIODS);
                displayBothPeriodsButtonChecked();

            }
        });

        editObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.setObjectiveName(objectiveNameEditText.getText().toString());
                if (core.saveObjective()){
                    dismissAllowingStateLoss();
                    consultObjectivesCore.retrieveObjectives();
                    new EditObjectiveOkDialog().show(getFragmentManager(), "");
                }
                else{
                    new EditObjectiveKoDialog().show(getFragmentManager(), "");
                }
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
            displayFirstPeriodButtonChecked();
        }
        else if (period == Objective.SECOND_PERIOD){
            displaySecondPeriodButtonChecked();
        }
        else if (period == Objective.BOTH_PERIODS){
            displayBothPeriodsButtonChecked();
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

    public void setConsultObjectivesCore(IConsultObjectivesCore consultObjectivesCore) {
        this.consultObjectivesCore = consultObjectivesCore;
    }

    private void displayFirstPeriodButtonChecked(){
        Resources resources = getActivity().getResources();

        objective1stPeriodButton.setTextColor(resources.getColor(R.color.white));
        objective2ndPeriodButton.setTextColor(resources.getColor(R.color.secondPeriodColor));
        objectiveBothPeriodsButton.setTextColor(resources.getColor(R.color.bothPeriodsColor));

        objective1stPeriodButton.setBackground(null);
        objective1stPeriodButton.setBackgroundColor(resources.getColor(R.color.firstPeriodColor));
        objective2ndPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
        objectiveBothPeriodsButton.setBackground(resources.getDrawable(R.drawable.button_border));
    }

    private void displaySecondPeriodButtonChecked(){
        Resources resources = getActivity().getResources();

        objective2ndPeriodButton.setTextColor(resources.getColor(R.color.white));
        objectiveBothPeriodsButton.setTextColor(resources.getColor(R.color.bothPeriodsColor));
        objective1stPeriodButton.setTextColor(resources.getColor(R.color.firstPeriodColor));

        objective2ndPeriodButton.setBackground(null);
        objective2ndPeriodButton.setBackgroundColor(resources.getColor(R.color.secondPeriodColor));
        objectiveBothPeriodsButton.setBackground(resources.getDrawable(R.drawable.button_border));
        objective1stPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
    }

    private void displayBothPeriodsButtonChecked(){
        Resources resources = getActivity().getResources();

        objectiveBothPeriodsButton.setTextColor(resources.getColor(R.color.white));
        objective2ndPeriodButton.setTextColor(resources.getColor(R.color.secondPeriodColor));
        objective1stPeriodButton.setTextColor(resources.getColor(R.color.firstPeriodColor));

        objectiveBothPeriodsButton.setBackground(null);
        objectiveBothPeriodsButton.setBackgroundColor(resources.getColor(R.color.bothPeriodsColor));
        objective2ndPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
        objective1stPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
    }
}
