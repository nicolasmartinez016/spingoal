package personal.nmartinez.fr.virtualfootballpicker.consultwheels.view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.adapters.EditWheelAdapter;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.EditWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.IConsultWheelsCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.core.IEditWheelCore;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.dialogs.EditWheelKoDialog;
import personal.nmartinez.fr.virtualfootballpicker.consultwheels.view.dialogs.EditWheelOkDialog;
import personal.nmartinez.fr.virtualfootballpicker.models.Wheel;

/**
 * Created by Nicolas on 16/12/2017.
 */

public class EditWheelDialog extends DialogFragment implements IEditWheelView {

    private IEditWheelCore core;
    private IConsultWheelsCore consultWheelsCore;

    private TextView unsavedChangesTextView;
    private EditText wheelNameEditText;
    private TextView editWheelNameValueTextView;

    public static EditWheelDialog newInstance(Wheel wheel){
        EditWheelDialog dialog = new EditWheelDialog();
        Bundle args = new Bundle();
        args.putSerializable("wheel", wheel);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Wheel wheel = (Wheel)getArguments().getSerializable("wheel");
        this.core = new EditWheelCore(getActivity(), wheel, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_wheel_layout, container, false);
        RecyclerView objectivesRecyclerView = (RecyclerView) view.findViewById(R.id.edit_wheel_recyclerview);
        objectivesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        objectivesRecyclerView.setHasFixedSize(true);
        EditWheelAdapter adapter = new EditWheelAdapter(this.core);
        objectivesRecyclerView.setAdapter(adapter);

        this.unsavedChangesTextView = (TextView) view.findViewById(R.id.unsaved_changes_textview);
        this.wheelNameEditText = (EditText) view.findViewById(R.id.edit_wheel_name_edittext);
        this.editWheelNameValueTextView = (TextView) view.findViewById(R.id.edit_wheel_name_value_textview);
        this.wheelNameEditText.setText(core.getWheel().getName());
        this.editWheelNameValueTextView.setText(" " + core.getWheel().getName());

        Button applyChangesButton = (Button) view.findViewById(R.id.edit_wheel_apply_button);
        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (core.saveChanges()){
                    // this is meh
                    consultWheelsCore.getView().applyChangesInWheels();
                    dismissAllowingStateLoss();
                    new EditWheelOkDialog().show(getFragmentManager(), "");
                }
                else{
                    new EditWheelKoDialog().show(getFragmentManager(), "");
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        dismissAllowingStateLoss();
        super.onPause();
    }

    /**
     * Displays the textview warning the user that there are unsaved changes in the wheel
     */
    @Override
    public void displayUnsavedChanges() {
        this.unsavedChangesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void displaySaveSuccess() {
    }

    /**
     * Hides the textview warning the user that there are unsaved changes in the wheel
     */
    @Override
    public void hideUnsavedChanges() {
        this.unsavedChangesTextView.setVisibility(View.GONE);
    }

    /**
     * Gets the name written by the user for the wheel
     * @return
     */
    @Override
    public String getEditedWheelName() {
        return this.wheelNameEditText.getText().toString();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    /**
     * Sets the core
     * @param core
     */
    public void setConsultWheelsCore(IConsultWheelsCore core){
        this.consultWheelsCore = core;
    }
}
