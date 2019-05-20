package personal.nmartinez.fr.spingoal.components.createwheels.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.spingoal.R;
import personal.nmartinez.fr.spingoal.components.createwheels.presenter.CreateWheelPresenter;
import personal.nmartinez.fr.spingoal.data.models.Objective;

/**
 * Created by Nicolas on 04/01/2018.
 */

public class CreateWheelAdapter extends RecyclerView.Adapter<CreateWheelAdapter.CreateWheelViewHolder> {

    private CreateWheelPresenter presenter;
    private List<Objective> objectives;
    private Context context;
    private boolean isSelectAll;

    public CreateWheelAdapter(CreateWheelPresenter presenter, Context context, List<Objective> allObjectives){
        this.presenter = presenter;
        this.objectives = allObjectives;
        this.context = context;
        isSelectAll = false;
    }

    @Override
    public CreateWheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_wheel_row, parent, false);
        return new CreateWheelAdapter.CreateWheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreateWheelViewHolder holder, int position) {
        final Objective objective = this.objectives.get(holder.getAdapterPosition());

        holder.objectiveCheckBox.setOnCheckedChangeListener(null);
        holder.objectiveCheckBox.setChecked(presenter.isObjectiveInWheel(objective));
        holder.objectiveName.setText(objective.getName());

        if (holder.getAdapterPosition() % 2 == 0){
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        else{
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }

        if (isSelectAll) {
            holder.objectiveCheckBox.setChecked(true);
        }

        // adds or removes the objective to/from the wheel when clicking on the checkbox
        holder.objectiveCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            presenter.addOrRemoveObjective(objective);
        });

    }


    @Override
    public int getItemCount() {
        return this.objectives.size();
    }

    public class CreateWheelViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.edit_wheel_objective_name_textview) TextView objectiveName;
        @BindView(R.id.edit_wheel_objective_checkbox) CheckBox objectiveCheckBox;
        @BindView(R.id.create_wheel_objective_row_layout) LinearLayout rowLayout;

        public CreateWheelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
