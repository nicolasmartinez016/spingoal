package personal.nmartinez.fr.virtualfootballpicker.consultobjectives.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultobjectives.core.ConsultObjectivesPresenter;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 01/12/2017.
 * Adapter for RecyclerView in ConsultObjectives Fragment
 */

public class ConsultObjectivesAdapter extends RecyclerView.Adapter<ConsultObjectivesAdapter.ConsultObjectivesViewHolder> {

    private List<Objective> objectives;
    private ConsultObjectivesPresenter core;
    private Context context;

    public ConsultObjectivesAdapter(List<Objective> objectives, ConsultObjectivesPresenter core, Context context){
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

        if (position % 2 == 0){
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.swipedLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        else{
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            holder.swipedLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }

        holder.objectiveTitle.setText(objective.getName());

        holder.editObjectiveButton.setOnClickListener(view -> {
            // redirect to edit
            NavigationManager.getInstance().consultObjective(objective);
        });
        holder.deleteObjectiveButton.setOnClickListener(view -> core.removeObjective(objective));

        if (objective.getPeriod() == Objective.FIRST_PERIOD){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.first_period_inline));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.firstPeriodColor));
            holder.periodImageView.setImageResource(R.drawable.field_first_period);

        }
        else if (objective.getPeriod() == Objective.SECOND_PERIOD){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.second_period_inline));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.secondPeriodColor));
            holder.periodImageView.setImageResource(R.drawable.field_second_period);
        }
        else if (objective.getPeriod() == Objective.BOTH_PERIODS){
            holder.objectivePeriod.setText(context.getResources().getString(R.string.both_periods_inline));
            holder.objectivePeriod.setTextColor(context.getResources().getColor(R.color.bothPeriodsColor));
            holder.periodImageView.setImageResource(R.drawable.field);
        }

        if (objective.isEditable()) {
            holder.swipedLayout.setVisibility(View.VISIBLE);
        } else {
            holder.swipedLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.objectives.size();
    }

    public class ConsultObjectivesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.consult_objective_title_textview) TextView objectiveTitle;
        @BindView(R.id.consult_objective_period_textview) TextView objectivePeriod;
        @BindView(R.id.consult_objective_edit_button) ImageView editObjectiveButton;
        @BindView(R.id.consult_objective_delete_button) ImageView deleteObjectiveButton;
        @BindView(R.id.consult_objective_row_period_imageview) ImageView periodImageView;
        @BindView(R.id.consult_objective_row_layout) LinearLayout rowLayout;
        @BindView(R.id.ll_swiped_layout) LinearLayout swipedLayout;

        public ConsultObjectivesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


