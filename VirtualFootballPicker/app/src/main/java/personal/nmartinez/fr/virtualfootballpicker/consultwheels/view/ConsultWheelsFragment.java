package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.AlertUtils;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters.ConsultWheelAdapter;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.ConsultWheelsPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultWheelsFragment extends Fragment implements ConsultWheelsView {

    public static final String TAG = ConsultWheelsFragment.class.getName();
    private static final String FROM_WHEEL_CREATED_KEY = "FROM_WHEEL_CREATED_KEY";
    private static final String IS_EDITING_KEY = "IS_EDITING_KEY";

    private ConsultWheelsPresenter presenter;
    private LifecycleRegistry lifecycleRegistry;

    @BindView
   (R.id.rv_consult_wheels) RecyclerView consultWheelsRecyclerView;
    @BindView(R.id.add_wheel_fab) TextView addWheelFab;
    @BindView(R.id.fl_consult_wheels) ConstraintLayout mainLayout;
    @BindView(R.id.pb_consult_wheels) ProgressBar consultWheelsProgressBar;
    @BindView(R.id.tv_consult_wheels_waiting_for_data) TextView consultWheelsWaitingTextView;

    public static ConsultWheelsFragment newInstance(boolean fromWheelCreated, boolean isEditing) {
        ConsultWheelsFragment fragment = new ConsultWheelsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(FROM_WHEEL_CREATED_KEY, fromWheelCreated);
        bundle.putBoolean(IS_EDITING_KEY, isEditing);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ConsultWheelsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new ConsultWheelsPresenterImpl(this, this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        View view = inflater.inflate(R.layout.fragment_consult_wheels, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        this.consultWheelsRecyclerView.setLayoutManager(layoutManager);
        this.consultWheelsRecyclerView.setHasFixedSize(true);
        this.addWheelFab.setOnClickListener(view1 -> NavigationManager.getInstance().createWheel(null));
        presenter.getData();
        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        if (getArguments() != null && getArguments().getBoolean(FROM_WHEEL_CREATED_KEY)) {
            String message = getArguments().getBoolean(IS_EDITING_KEY) ? "La roue a été modifiée." : "La roue a été créée.";
            AlertUtils.displaySuccessCrouton(getActivity(), message, mainLayout);
            getArguments().putBoolean(FROM_WHEEL_CREATED_KEY, false);
        }
        return view;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showWheels(List<Wheel> wheels, int favoriteWheelid) {
        if (wheels != null && !wheels.isEmpty()) {
            consultWheelsProgressBar.setVisibility(View.GONE);
            consultWheelsWaitingTextView.setVisibility(View.GONE);
            consultWheelsRecyclerView.setVisibility(View.VISIBLE);
            ConsultWheelAdapter adapter = new ConsultWheelAdapter(wheels, getActivity(), this.presenter, favoriteWheelid);
            this.consultWheelsRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showFavoriteWheelChangedSuccess() {
        AlertUtils.displaySuccessCrouton(getActivity(), "La roue par défaut a été modifiée", mainLayout);
    }

    @Override
    public void showFavoriteWheelChangedFailure() {
        AlertUtils.displayErrorCrouton(getActivity(), "La roue par défaut n'a pas été modifiée", mainLayout);
    }

    @Override
    public void showWheelRemovedSuccess() {
        AlertUtils.displaySuccessCrouton(getActivity(), "La roue a été supprimée", mainLayout);
    }

    @Override
    public void showWheelRemovedFailure() {
        AlertUtils.displayErrorCrouton(getActivity(), "La roue n'a pas été supprimée. Vérifiez qu'il ne s'agit pas de la roue par défaut.", mainLayout);
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

    @Override
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }
}
