package personal.nmartinez.fr.virtualfootballpicker.createobjective.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.AlertUtils;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.presenter.CreateObjectivePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.createobjective.presenter.CreateObjectivePresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.ObjectiveModel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateObjectiveFragment extends Fragment implements CreateObjectiveView {

    public static final String TAG = CreateObjectiveFragment.class.getName();
    public static final String CONSULT_OBJECTIVE_KEY = "consult_objective_key";

    private LifecycleRegistry lifecycleRegistry;
    private CreateObjectivePresenter presenter;

    @BindView(R.id.create_objective_name_edittext) AppCompatEditText nameEditText;
    @BindView(R.id.create_objective_name_error_textview) TextView errorNameTextView;
    @BindView(R.id.create_objective_period_error_textview) TextView errorPeriodTextView;
    @BindView(R.id.create_objective_first_period_button) Button firstPeriodButton;
    @BindView(R.id.create_objective_second_period_button) Button secondPeriodButton;
    @BindView(R.id.create_objective_both_periods_button) Button bothPeriodsButton;
    @BindView(R.id.create_objective_editable_button) Button objectiveEditableButton;
    @BindView(R.id.create_objective_not_editable_button) Button objectiveNotEditableButton;
    @BindView(R.id.edit_objective_layout) View editingLayout;
    @BindView(R.id.create_objective_layout) View creatingLayout;
    @BindView(R.id.rl_create_objective_main_layout) RelativeLayout mainLayout;

    private ObjectiveModel objectiveModel;



    public CreateObjectiveFragment() {
        // Required empty public constructor
    }

    public static CreateObjectiveFragment newInstance(Objective objective) {
        CreateObjectiveFragment fragment = new CreateObjectiveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CONSULT_OBJECTIVE_KEY, objective);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        if (getArguments() != null) {
            Objective objective = (Objective) getArguments().getSerializable(CONSULT_OBJECTIVE_KEY);
            this.presenter = new CreateObjectivePresenterImpl(this, objective);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_objective, container, false);
        ButterKnife.bind(this, view);

        this.firstPeriodButton.setOnClickListener(view16 -> selectObjectivePeriod(Objective.FIRST_PERIOD));

        this.secondPeriodButton.setOnClickListener(view15 ->
            selectObjectivePeriod(Objective.SECOND_PERIOD)
        );

        this.bothPeriodsButton.setOnClickListener(view14 ->
            selectObjectivePeriod(Objective.BOTH_PERIODS)
        );


        this.objectiveEditableButton.setOnClickListener(view13 -> {
            selectObjectiveEditable(true);
        });

        this.objectiveNotEditableButton.setOnClickListener(view12 -> {
            selectObjectiveEditable(false);
        });

        //this.validateButton.setOnClickListener(view1 -> presenter.createObjective(nameEditText.getText().toString()));

        presenter.initViews();
        ((HideShowIconInterface) getActivity()).showBackIcon();
        return view;
    }

    private void selectObjectivePeriod(int period) {
        //presenter.setObjectivePeriod(period);
        objectiveModel.setPeriod(period);

        Resources resources = getActivity().getResources();

        bothPeriodsButton.setTextColor(Objective.BOTH_PERIODS == period ? resources.getColor(R.color.white) : resources.getColor(R.color.bothPeriodsColor));
        secondPeriodButton.setTextColor(Objective.SECOND_PERIOD == period ? resources.getColor(R.color.white) : resources.getColor(R.color.secondPeriodColor));
        firstPeriodButton.setTextColor(Objective.FIRST_PERIOD == period ? resources.getColor(R.color.white) : resources.getColor(R.color.firstPeriodColor));

        if (Objective.BOTH_PERIODS == period) {
            bothPeriodsButton.setBackground(null);
            bothPeriodsButton.setBackgroundColor(resources.getColor(R.color.bothPeriodsColor));
        } else {
            bothPeriodsButton.setBackground(resources.getDrawable(R.drawable.button_border));
        }

        if (Objective.FIRST_PERIOD == period) {
            firstPeriodButton.setBackground(null);
            firstPeriodButton.setBackgroundColor(resources.getColor(R.color.firstPeriodColor));
        } else {
            firstPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
        }

        if (Objective.SECOND_PERIOD == period) {
            secondPeriodButton.setBackground(null);
            secondPeriodButton.setBackgroundColor(resources.getColor(R.color.secondPeriodColor));
        } else {
            secondPeriodButton.setBackground(resources.getDrawable(R.drawable.button_border));
        }
    }

    private void selectObjectiveEditable(boolean isObjectiveEditable) {
        //presenter.setObjectiveEditable(isObjectiveEditable);
        this.objectiveModel.setEditable(isObjectiveEditable);
        Resources resources = getActivity().getResources();
        objectiveNotEditableButton.setTextColor(isObjectiveEditable ? resources.getColor(R.color.main_green) : resources.getColor(R.color.white));
        objectiveEditableButton.setTextColor(isObjectiveEditable ? resources.getColor(R.color.white) : resources.getColor(R.color.main_green));

        if (isObjectiveEditable) {
            objectiveEditableButton.setBackground(null);
            objectiveEditableButton.setBackgroundColor(resources.getColor(R.color.main_green));
            objectiveNotEditableButton.setBackground(resources.getDrawable(R.drawable.button_border));
        } else {
            objectiveNotEditableButton.setBackground(null);
            objectiveNotEditableButton.setBackgroundColor(resources.getColor(R.color.main_green));
            objectiveEditableButton.setBackground(resources.getDrawable(R.drawable.button_border));
        }
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
    public void displayObjectiveCreationSucces(boolean isEditing) {
        NavigationManager.getInstance().consultObjectives(true, isEditing);
    }

    @Override
    public void displayObjectiveCreationFailure(boolean isEditing) {
        String message = isEditing ? "Le gage n'a pas été modifié." : "Le gage n'a pas été créé";
        AlertUtils.displayErrorCrouton(getActivity(), message, mainLayout);
    }

    @Override
    public void showError(String message) {

    }

    /**
     * Displays the warning message that the user did not pick selectObjectivePeriod period
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
     * Hides the warning message that the user did not pick selectObjectivePeriod period
     */
    @Override
    public void hidePickAPeriod() {
        this.errorPeriodTextView.setVisibility(View.GONE);
    }

    @Override
    public void initViews(ObjectiveModel objective, boolean isEditing) {
        this.objectiveModel = objective;
        if (objective != null) {
            if (!StringUtils.isNullOrEmpty(objective.getName())) {
                this.nameEditText.setText(objective.getName());
            }

            if (objective.getPeriod() != 0) {
                selectObjectivePeriod(objective.getPeriod());
            }

            selectObjectiveEditable(objective.isEditable());
            if (isEditing) {
                //nameEditText.setInputType(InputType.TYPE_NULL);
                showEditingLayout();
            } else {
                showCreatingLayout();
            }
        }
    }

    private void showEditingLayout() {
        if (this.editingLayout != null) {
            this.editingLayout.setVisibility(View.VISIBLE);
        }

        if (this.creatingLayout != null) {
            this.creatingLayout.setVisibility(View.GONE);
        }
    }

    private void showCreatingLayout() {
        if (this.editingLayout != null) {
            this.editingLayout.setVisibility(View.GONE);
        }

        if (this.creatingLayout != null) {
            this.creatingLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.validate_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.iv_validate_objective) {
            this.objectiveModel.setName(nameEditText.getText().toString());
            presenter.createObjective(this.objectiveModel);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.lifecycleRegistry != null) {
            this.lifecycleRegistry.markState(Lifecycle.State.RESUMED);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.lifecycleRegistry != null) {
            this.lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        }
    }
}
