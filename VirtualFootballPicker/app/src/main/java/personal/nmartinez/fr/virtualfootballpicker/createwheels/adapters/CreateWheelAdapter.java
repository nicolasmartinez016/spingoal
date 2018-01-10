package personal.nmartinez.fr.virtualfootballpicker.createwheels.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    public CreateWheelAdapter(ICreateWheelCore core){
        this.core = core;
        this.objectives = core.getAllObjectives();
    }

    @Override
    public CreateWheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_wheel_row, parent, false);
        return new CreateWheelAdapter.CreateWheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreateWheelViewHolder holder, int position) {
        final Objective objective = this.objectives.get(position);
        holder.objectiveName.setText(objective.getName());

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

        public CreateWheelViewHolder(View itemView) {
            super(itemView);
            objectiveName = (TextView) itemView.findViewById(R.id.edit_wheel_objective_name_textview);
            objectiveCheckBox = (CheckBox) itemView.findViewById(R.id.edit_wheel_objective_checkbox);
            this.setIsRecyclable(false);
        }
    }
}
