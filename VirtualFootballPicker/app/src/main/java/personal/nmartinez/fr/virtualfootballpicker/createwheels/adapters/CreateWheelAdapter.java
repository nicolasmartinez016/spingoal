package personal.nmartinez.fr.virtualfootballpicker.createwheels.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.createwheels.core.ICreateWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;

/**
 * Created by Nicolas on 04/01/2018.
 */

public class CreateWheelAdapter extends RecyclerView.Adapter<CreateWheelAdapter.CreateWheelViewHolder> {

    private ICreateWheelCore core;
    private List<Objective> objectives;
    private Context context;

    public CreateWheelAdapter(ICreateWheelCore core, Context context){
        this.core = core;
        this.objectives = core.getAllObjectives();
        this.context = context;
    }

    @Override
    public CreateWheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_wheel_row, parent, false);
        return new CreateWheelAdapter.CreateWheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreateWheelViewHolder holder, int position) {
        final Objective objective = this.objectives.get(position);

        holder.objectiveCheckBox.setChecked(core.isObjectiveInWheel(objective));
        holder.objectiveName.setText(objective.getName());

        if (position % 2 == 0){
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        else{
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }

        // adds or removes the objective to/from the wheel when clicking on the checkbox
        holder.objectiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                core.addOrRemoveObjective(objective);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.objectives.size();
    }

    public class CreateWheelViewHolder extends RecyclerView.ViewHolder{
        private TextView objectiveName;
        private CheckBox objectiveCheckBox;
        private LinearLayout rowLayout;

        public CreateWheelViewHolder(View itemView) {
            super(itemView);
            objectiveName = (TextView) itemView.findViewById(R.id.edit_wheel_objective_name_textview);
            objectiveCheckBox = (CheckBox) itemView.findViewById(R.id.edit_wheel_objective_checkbox);
            rowLayout = (LinearLayout) itemView.findViewById(R.id.create_wheel_objective_row_layout);
            this.setIsRecyclable(false);
        }
    }
}
