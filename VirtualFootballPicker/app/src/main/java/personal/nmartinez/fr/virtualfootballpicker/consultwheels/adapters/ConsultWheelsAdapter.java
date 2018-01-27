package personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.IConsultWheelsCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.EditWheelDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Objective;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * Created by Nicolas on 03/12/2017.
 */

public class ConsultWheelsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private IConsultWheelsCore core;
    private List<Wheel> wheels;
    private int selectedWheelId;

    public ConsultWheelsAdapter(Context context, IConsultWheelsCore core){
        this.wheels = core.getData();
        this.context = context;
        this.core = core;
        Wheel wheel = this.core.getSelectedWheel();
        if (wheel != null){
            this.selectedWheelId = wheel.getId();
        }
    }

    @Override
    public int getGroupCount() {
        return this.wheels.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.wheels.get(i).getObjectives().size();
    }

    @Override
    public Object getGroup(int i) {
        return this.wheels.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.wheels.get(i).getObjectives().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, final ViewGroup viewGroup) {
        //if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.consult_wheel_row, null);
        //}
            //final Wheel wheel = this.wheels.get(i);
            final Wheel wheel = (Wheel)getGroup(i);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.consult_wheel_row_layout);
            TextView wheelName = (TextView) view.findViewById(R.id.consult_wheel_title_textview);
            wheelName.setText(wheel.getName());
            ImageView editButton = (ImageView) view.findViewById(R.id.consult_wheel_edit_button);
            ImageView uploadButton = (ImageView) view.findViewById(R.id.consult_wheel_upload_button);
            ImageView deleteButton = (ImageView) view.findViewById(R.id.consult_wheel_delete_button);
            ImageView favButton = (ImageView) view.findViewById(R.id.consult_wheel_fav_button);

            // have to set focusable to false otherwise the listview doesnt expand anymore due to buttons
            // catching the focus instead of the list
            editButton.setFocusable(false);
            deleteButton.setFocusable(false);
            uploadButton.setFocusable(false);
            favButton.setFocusable(false);

            if (i % 2 == 0 ){
                layout.setBackgroundColor(context.getResources().getColor(R.color.gray));
            }
            else{
                layout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            }

            if (wheel.getId() == this.selectedWheelId){
                favButton.setImageDrawable(context.getDrawable(R.drawable.ic_star_black_24dp));
            }

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(viewGroup.getContext(), "edit", Toast.LENGTH_LONG).show();
                    EditWheelDialog dialog = core.openEdition(wheel);
                    dialog.setConsultWheelsCore(core);
                }
            });

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    core.setWheelToUse(wheel);
                }
            });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.objective_row_in_wheel, null);
        //}

        Objective objective = (Objective)getChild(i, i1);
        TextView objectiveName = (TextView) view.findViewById(R.id.objective_title_in_wheels_textview);
        TextView objectivePeriod = (TextView) view.findViewById(R.id.objective_period_in_wheels_textview);

        if (objective.getPeriod() == Objective.FIRST_PERIOD){
            objectivePeriod.setText(context.getResources().getString(R.string.first_period_inline));
            objectivePeriod.setTextColor(context.getResources().getColor(R.color.firstPeriodColor));
        }

        else if (objective.getPeriod() == Objective.SECOND_PERIOD){
            objectivePeriod.setText(context.getResources().getString(R.string.second_period_inline));
            objectivePeriod.setTextColor(context.getResources().getColor(R.color.secondPeriodColor));
        }

        else if (objective.getPeriod() == Objective.BOTH_PERIODS){
            objectivePeriod.setText(context.getResources().getString(R.string.both_periods_inline));
            objectivePeriod.setTextColor(context.getResources().getColor(R.color.bothPeriodsColor));
        }

        objectiveName.setText(objective.getName());
        return view;
    }



    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
