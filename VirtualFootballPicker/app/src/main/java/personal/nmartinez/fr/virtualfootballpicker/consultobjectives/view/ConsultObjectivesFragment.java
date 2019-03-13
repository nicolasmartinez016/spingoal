package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.AlertUtils;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.adapters.ConsultObjectivesAdapter;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesPresenter;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesPresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultObjectivesFragment extends Fragment implements ConsultObjectivesView {

    public static final String TAG = ConsultObjectivesFragment.class.getName();

    private static final String FROM_OBJECTIVE_CREATED_KEY = "FROM_OBJECTIVE_CREATED_KEY";
    private static final String IS_EDITING_KEY = "IS_EDITING_KEY";

    private ConsultObjectivesPresenter presenter;

    private LifecycleRegistry lifecycleRegistry;

    @BindView(R.id.consult_objectives_recycler_view) RecyclerView consultObjectivesRecyclerView;
    @BindView(R.id.consult_objetives_progressbar) ProgressBar consultObjectivesProgressBar;
    @BindView(R.id.consult_objetives_waiting_message) TextView consultObjectivesWaitingTextView;
    @BindView(R.id.create_objective_fab) TextView createObjectiveFab;
    @BindView(R.id.rl_consult_objectives_layout) RelativeLayout mainLayout;

    public static ConsultObjectivesFragment newInstance(boolean fromObjectiveCreated, boolean isEditing) {
        ConsultObjectivesFragment fragment = new ConsultObjectivesFragment();
        Bundle args = new Bundle();
        args.putBoolean(FROM_OBJECTIVE_CREATED_KEY, fromObjectiveCreated);
        args.putBoolean(IS_EDITING_KEY, isEditing);
        fragment.setArguments(args);
        return fragment;
    }

    public ConsultObjectivesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry.markState(Lifecycle.State.CREATED);
        this.presenter = new ConsultObjectivesPresenterImpl(getActivity(), this);

        View view =  inflater.inflate(R.layout.fragment_consult_objectives, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        this.consultObjectivesRecyclerView.setLayoutManager(layoutManager);
        this.consultObjectivesRecyclerView.setHasFixedSize(true);

        this.presenter.getData();
        this.createObjectiveFab.setOnClickListener(view1 -> NavigationManager.getInstance().createObjective());
        if (getArguments() != null && getArguments().getBoolean(FROM_OBJECTIVE_CREATED_KEY)) {
            String message = getArguments().getBoolean(IS_EDITING_KEY) ? "Le gage a été modifié." : "Le gage a été créé.";
            AlertUtils.displaySuccessCrouton(getActivity(), message, mainLayout);
        }
        ((HideShowIconInterface) getActivity()).showHamburgerIcon();

        return view;
    }

    @Override
    public void displayWaitingForData(boolean wait) {
        if (wait){
            this.consultObjectivesRecyclerView.setVisibility(View.GONE);
            this.consultObjectivesProgressBar.setVisibility(View.VISIBLE);
            this.consultObjectivesWaitingTextView.setVisibility(View.VISIBLE);
        }
        else{
            this.consultObjectivesRecyclerView.setVisibility(View.VISIBLE);
            this.consultObjectivesProgressBar.setVisibility(View.GONE);
            this.consultObjectivesWaitingTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showObjectives(List<Objective> objectives) {
        this.consultObjectivesRecyclerView.setAdapter(new ConsultObjectivesAdapter(objectives, this.presenter, getActivity()));
        displayWaitingForData(false);
    }

    @Override
    public void showObjectiveDeletedSuccess() {
        AlertUtils.displaySuccessCrouton(getActivity(), "Le gage a été supprimé", mainLayout);
    }

    @Override
    public void showObjectiveDeletedFailure() {
        AlertUtils.displayErrorCrouton(getActivity(), "Le gage n'a pas été supprimé", mainLayout);
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
        return lifecycleRegistry;
    }
}
