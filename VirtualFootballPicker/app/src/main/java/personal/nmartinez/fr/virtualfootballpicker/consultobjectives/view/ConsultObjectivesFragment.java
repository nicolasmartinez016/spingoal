package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.adapters.ConsultObjectivesAdapter;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesCore;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.IConsultObjectivesCore;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.dialogs.CantDeleteObjectiveDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.view.dialogs.ObjectiveDeletedDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultObjectivesFragment extends Fragment implements IConsultObjectivesView {

    private IConsultObjectivesCore core;

    private RecyclerView consultObjectivesRecyclerView;
    private ProgressBar consultObjectivesProgressBar;
    private TextView consultObjectivesWaitingTextView;

    public ConsultObjectivesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.core = new ConsultObjectivesCore(getActivity(), this);

        View view =  inflater.inflate(R.layout.fragment_consult_objectives, container, false);

        this.consultObjectivesProgressBar = (ProgressBar) view.findViewById(R.id.consult_objetives_progressbar);
        this.consultObjectivesRecyclerView = (RecyclerView) view.findViewById(R.id.consult_objectives_recycler_view);
        //this.consultObjectivesRecyclerView.getRecycledViewPool().setMaxRecycledViews(0,0);


        this.consultObjectivesWaitingTextView = (TextView) view.findViewById(R.id.consult_objetives_waiting_message);

        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        this.consultObjectivesRecyclerView.setLayoutManager(layoutManager);
        this.consultObjectivesRecyclerView.setHasFixedSize(true);

        this.core.retrieveObjectives();
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
    public void setObjectivesAdapter(List<Objective> objectives) {
        this.consultObjectivesRecyclerView.setAdapter(new ConsultObjectivesAdapter(objectives, this.core, getActivity()));
    }

    @Override
    public void displayObjectiveDeletedPopup() {
        new ObjectiveDeletedDialog().show(getFragmentManager(), "");
    }

    @Override
    public void displayCantDeleteObjectivePopup() {
        new CantDeleteObjectiveDialog().show(getFragmentManager(), "");
    }

    @Override
    public Activity getViewActivity() {
        return getActivity();
    }
}
