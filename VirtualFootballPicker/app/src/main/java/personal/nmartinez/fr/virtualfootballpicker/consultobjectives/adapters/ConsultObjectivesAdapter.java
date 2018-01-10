package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.IConsultObjectivesCore;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * Created by Nicolas on 01/12/2017.
 * Adapter for RecyclerView in ConsultObjectives Fragment
 */

public class ConsultObjectivesAdapter extends RecyclerView.Adapter<ConsultObjectivesAdapter.ConsultObjectivesViewHolder> {

    private List<Objective> objectives;
    private IConsultObjectivesCore core;
    private Context context;

    public ConsultObjectivesAdapter(List<Objective> objectives, IConsultObjectivesCore core, Context context){
        this.core = core;
        this.objectives = objectives;
        this.context = context;
    }

    @Override
    public ConsultObjectivesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_objective_row, parent, false);
        return new ConsultObjectivesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConsultObjectivesViewHolder holder, int position) {
        final Objective objective = this.objectives.get(position);
        holder.objectiveTitle.setText(StringUtils.cutObjectiveName(objective.getName()));
        if (!objective.isEditable()){
            holder.editObjectiveButton.setVisibility(View.INVISIBLE);
            holder.deleteObjectiveButton.setVisibility(View.INVISIBLE);
        }
        else{
            holder.editObjectiveButton.setVisibility(View.VISIBLE);
            holder.deleteObjectiveButton.setVisibility(View.VISIBLE);
        }
        holder.editObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.openEdition(objective);
            }
        });

        if (objective.getPeriod() == Objective.FIRST_PERIOD){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.first_period));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.firstPeriodColor));

        }
        else if (objective.getPeriod() == Objective.SECOND_PERIOD){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.second_period));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.secondPeriodColor));
        }
        else if (objective.getPeriod() == Objective.BOTH_PERIODS){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.both_periods));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.bothPeriodsColor));
        }
    }

    @Override
    public int getItemCount() {
        return this.objectives.size();
    }

    public class ConsultObjectivesViewHolder extends RecyclerView.ViewHolder{

        public TextView objectiveTitle;
        TextView objectivePeriod;
        public ImageButton editObjectiveButton;
        public ImageButton deleteObjectiveButton;
        public ImageButton uploadObjectiveButton;

        public ConsultObjectivesViewHolder(View itemView) {
            super(itemView);
            objectiveTitle = (TextView) itemView.findViewById(R.id.consult_objective_title_textview);
            editObjectiveButton = (ImageButton) itemView.findViewById(R.id.consult_objective_edit_button);
            deleteObjectiveButton = (ImageButton) itemView.findViewById(R.id.consult_objective_delete_button);
            objectivePeriod = (TextView) itemView.findViewById(R.id.consult_objective_period_textview);
        }
    }
}


