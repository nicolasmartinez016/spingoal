package personal.nmartinez.fr.virtualfootballpicker.consultwheeldetail.view.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.utils.ImageUtils;

public class ConsultWheelDetailAdapter extends RecyclerView.Adapter<ConsultWheelDetailAdapter.ConsultWheelDetailViewHolder> {

    private List<Objective> objectives;
    private Context context;

    public ConsultWheelDetailAdapter(Context context, List<Objective> objectives) {
        this.objectives = objectives;
        this.context = context;
    }

    @Override
    public ConsultWheelDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_wheel_detail_row, parent, false);
        return new ConsultWheelDetailAdapter.ConsultWheelDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConsultWheelDetailViewHolder holder, int position) {
        if (objectives != null && !objectives.isEmpty()) {
            Objective objective = objectives.get(holder.getAdapterPosition());
            if (objective != null) {
                if (holder.mainLayout != null) {
                    if (position % 2 == 0){
                        holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    }
                    else{
                        holder.mainLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                    }
                }

                if (holder.objectiveNameTextView != null) {
                    holder.objectiveNameTextView.setText(objective.getName());
                }

                if (holder.objectivePeriodImageView != null) {
                    holder.objectivePeriodImageView.setImageDrawable(ImageUtils.getPeriodImage(context, objective.getPeriod()));
                }

                if (holder.objectivePeriodTextView != null) {
                    String period = "";
                    int color = context.getResources().getColor(R.color.firstPeriodColor);
                    switch (objective.getPeriod()) {
                        case Objective.FIRST_PERIOD :
                            period = context.getString(R.string.first_period_inline);
                            color = context.getResources().getColor(R.color.firstPeriodColor);
                            break;
                        case Objective.SECOND_PERIOD :
                            period = context.getString(R.string.second_period_inline);
                            color = context.getResources().getColor(R.color.secondPeriodColor);
                            break;
                        case Objective.BOTH_PERIODS :
                            period = context.getString(R.string.both_periods_inline);
                            color = context.getResources().getColor(R.color.bothPeriodsColor);
                            break;
                        default:
                            break;
                    }

                    holder.objectivePeriodTextView.setTextColor(color);
                    holder.objectivePeriodTextView.setText(period);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return objectives != null ? objectives.size() : 0;
    }

    public class ConsultWheelDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cl_consult_wheel_detail_adapter) ConstraintLayout mainLayout;
        @BindView(R.id.tv_consult_wheel_detail_objective_name) TextView objectiveNameTextView;
        @BindView(R.id.tv_consult_wheel_detail_period) TextView objectivePeriodTextView;
        @BindView(R.id.iv_consult_wheel_detail_period) ImageView objectivePeriodImageView;

        public ConsultWheelDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
