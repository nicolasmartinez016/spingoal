package personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.IEditWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 16/12/2017.
 */

public class EditWheelAdapter extends RecyclerView.Adapter<EditWheelAdapter.EditWheelViewHolder> {

    private IEditWheelCore core;
    private List<Objective> objectives;

    public EditWheelAdapter(IEditWheelCore core){
        this.core = core;
        this.objectives = core.getObjectives();
    }

    @Override
    public EditWheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_wheel_row, parent, false);
        return new EditWheelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EditWheelViewHolder holder, int position) {
        final Objective objective = this.objectives.get(position);
        holder.objectiveName.setText(objective.getName());

        holder.objectiveCheckBox.setChecked(core.isObjectiveInWheel(objective));

        holder.objectiveCheckBox.setOnCheckedChangeListener(null);

        if (core.isObjectiveInWheel(objective)) {
            holder.objectiveCheckBox.setChecked(true);
        }

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

    public class EditWheelViewHolder extends RecyclerView.ViewHolder{

        private TextView objectiveName;
        private CheckBox objectiveCheckBox;

        public EditWheelViewHolder(View itemView) {
            super(itemView);
            objectiveName = (TextView) itemView.findViewById(R.id.edit_wheel_objective_name_textview);
            objectiveCheckBox = (CheckBox) itemView.findViewById(R.id.edit_wheel_objective_checkbox);
            this.setIsRecyclable(false);
        }
    }
}
