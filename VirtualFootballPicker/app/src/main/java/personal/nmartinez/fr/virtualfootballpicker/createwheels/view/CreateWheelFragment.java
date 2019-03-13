package personal.nmartinez.fr.virtualfootballpicker.createwheels.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.AlertUtils;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.adapters.CreateWheelAdapter;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.core.CreateWheelPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.core.CreateWheelPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.models.WheelModel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateWheelFragment extends Fragment implements CreateWheelView {

    public static final String TAG = CreateWheelFragment.class.getName();
    private static final String WHEEL_KEY = "wheel_key";
    private CreateWheelPresenter presenter;
    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.create_wheel_label) TextView createOrEditWheelTextView;
    @BindView(R.id.create_wheel_name_edit_text) TextInputEditText createWheelNameEditText;
    @BindView(R.id.create_wheel_objectives_recyclerview) RecyclerView createWheelObjectivesRecyclerView;
    @BindView(R.id.tv_create_wheel_invalid_name) TextView nameUnavailableTextView;
    @BindView(R.id.edit_wheel_icon_layout) LinearLayout editWheelTitleLayout;
    @BindView(R.id.create_wheel_icon_layout) LinearLayout createWheelTitleLayout;
    @BindView(R.id.fl_create_wheel_main_layout) ConstraintLayout mainLayout;
    @BindView(R.id.cb_create_wheel_select_all_objectives) CheckBox selectAllObjectivesCheckbox;
    @BindView(R.id.iv_create_or_edit_wheel) ImageView createOrEditWheelImageView;

    private WheelModel wheelModel;

    public static CreateWheelFragment newInstance(Wheel wheel) {
        CreateWheelFragment fragment = new CreateWheelFragment();
        if (wheel != null) {
            Bundle args = new Bundle();
            args.putSerializable(WHEEL_KEY, wheel);
            fragment.setArguments(args);
        }

        return fragment;
    }

    public CreateWheelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        Wheel wheel = null;
        if (getArguments() != null) {
            wheel = (Wheel) getArguments().getSerializable(WHEEL_KEY);
        }

        this.presenter = new CreateWheelPresenterImpl(this, wheel);
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_wheel, container, false);
        ButterKnife.bind(this, view);

        createWheelObjectivesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        createWheelObjectivesRecyclerView.setHasFixedSize(true);

        presenter.initViews();
        ((HideShowIconInterface) getActivity()).showBackIcon();
        return view;
    }

    @Override
    public void initViews(WheelModel wheelModel) {
        if (wheelModel != null) {
            this.wheelModel = wheelModel;
            if (!StringUtils.isNullOrEmpty(wheelModel.getName())) {
                createWheelNameEditText.setText(wheelModel.getName());
            }

            CreateWheelAdapter adapter = new CreateWheelAdapter(this.presenter, getActivity(), wheelModel.getAllObjectives());
            createWheelObjectivesRecyclerView.setAdapter(adapter);

            selectAllObjectivesCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
                    presenter.addOrRemoveAllObjectives(b);
                }
            );

            if (wheelModel.isEditing()) {
                showEditingLayout();
            } else {
                showCreatingLayout();
            }
        }
    }

    private void showEditingLayout() {
        createOrEditWheelTextView.setText(getResources().getString(R.string.edit_wheel_label));
        createOrEditWheelImageView.setImageDrawable(getResources().getDrawable(R.drawable.edit_wheel_icon));
    }

    private void showCreatingLayout() {
        createOrEditWheelTextView.setText(getResources().getString(R.string.create_wheel_label));
        createOrEditWheelImageView.setImageDrawable(getResources().getDrawable(R.drawable.add_wheel_icon));
    }

    @Override
    public void showCreationSuccess() {
        NavigationManager.getInstance().consultWheels(true, wheelModel.isEditing());
    }

    @Override
    public void showCreationFailure() {
        String message = wheelModel.isEditing() ? "Le gage n'a pas été modifié." : "Le gage n'a pas été créé";
        AlertUtils.displayErrorCrouton(getActivity(), message, mainLayout);
    }

    @Override
    public void showNameNotAvailable() {
        nameUnavailableTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.validate_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.iv_validate_objective) {
            wheelModel.setName(createWheelNameEditText.getText().toString());
            presenter.createWheel(this.wheelModel);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.RESUMED);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycleRegistry != null) {
            lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        }
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void checkOrUncheckSelectAll(boolean shouldCheck) {
        if (this.selectAllObjectivesCheckbox != null) {
            this.selectAllObjectivesCheckbox.setOnCheckedChangeListener(null);
            this.selectAllObjectivesCheckbox.setChecked(shouldCheck);
            this.selectAllObjectivesCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
                        presenter.addOrRemoveAllObjectives(b);
                    }
            );
        }
    }
}
