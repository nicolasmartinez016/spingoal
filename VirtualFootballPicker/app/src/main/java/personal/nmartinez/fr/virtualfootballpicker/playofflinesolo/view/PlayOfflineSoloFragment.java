package personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.core.IPlayOfflineSoloCore;
import personal.nmartinez.fr.virtualfootballpicker.playofflinesolo.core.PlayOfflineSoloCore;
import personal.nmartinez.fr.virtualfootballpicker.utils.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayOfflineSoloFragment extends Fragment implements IPlayOfflineSoloView {

    private IPlayOfflineSoloCore core;
    private TextView wheelUsedTextView;
    private TextView firstHalfObjectiveTextView;
    private TextView secondHalfObjectiveTextView;

    private ImageView choseFirstHalfObjectiveButton;
    private ImageView choseSecondHalfObjectiveButton;
    private ImageView showFirstHalfObjectiveButton;
    private ImageView showSecondHalfObjectiveButton;
    private ImageView pickStarsButton;

    private ImageView starsImageView;

    private boolean showFirstHalfObjective;
    private boolean showSecondHalfObjective;

    public PlayOfflineSoloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.core = new PlayOfflineSoloCore(this.getActivity(), this);
        this.showFirstHalfObjective = true;
        this.showSecondHalfObjective = true;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_offline_solo, container, false);

        this.wheelUsedTextView = (TextView) view.findViewById(R.id.selected_wheel_textview_single_player);
        this.firstHalfObjectiveTextView = (TextView) view.findViewById(R.id.first_period_objective_textview_single_player);
        this.secondHalfObjectiveTextView = (TextView) view.findViewById(R.id.second_period_objective_textview_single_player);
        this.choseFirstHalfObjectiveButton = (ImageView) view.findViewById(R.id.pick_first_period_objective_single_player);
        this.choseSecondHalfObjectiveButton = (ImageView) view.findViewById(R.id.pick_second_period_objective_single_player);
        this.showFirstHalfObjectiveButton = (ImageView) view.findViewById(R.id.hide_or_display_first_period_objective_single_player);
        this.showSecondHalfObjectiveButton = (ImageView) view.findViewById(R.id.hide_or_display_second_period_objective_single_player);
        this.starsImageView = (ImageView) view.findViewById(R.id.selected_stars_imageview_single_player);
        this.pickStarsButton = (ImageView) view.findViewById(R.id.pick_stars_button);

        this.choseFirstHalfObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.choseFirstHalfObjective();
                showFirstHalfObjectiveButton.setVisibility(View.VISIBLE);
            }
        });

        this.choseSecondHalfObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.choseSecondHalfObjective();
                showSecondHalfObjectiveButton.setVisibility(View.VISIBLE);
            }
        });

        this.showFirstHalfObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayOrHideFirstHalfObjective();
            }
        });

        this.showSecondHalfObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayOrHideSecondHalfObjective();
            }
        });

        this.pickStarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starsImageView.setVisibility(View.VISIBLE);
                core.choseStars();
            }
        });

        this.core.setupWheel();

        return view;
    }

    /**
     * Sets the first half objective textview with the selected objective
     * @param objective
     */
    @Override
    public void setFirstHalfObjectiveTextView(String objective) {
        this.firstHalfObjectiveTextView.setText(StringUtils.cutObjectiveName(objective));
    }

    /**
     * Sets the second half objective textview with the selected objective
     * @param objective
     */
    @Override
    public void setSecondHalfObjectiveTextView(String objective) {
        this.secondHalfObjectiveTextView.setText(StringUtils.cutObjectiveName(objective));
    }

    /**
     * Displays or hides the first period objective textview
     */
    @Override
    public void displayOrHideFirstHalfObjective() {
        this.showFirstHalfObjective = !this.showFirstHalfObjective;
        if (this.showFirstHalfObjective){
            this.firstHalfObjectiveTextView.setVisibility(View.VISIBLE);
            this.showFirstHalfObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
        }
        else {
            this.firstHalfObjectiveTextView.setVisibility(View.INVISIBLE);
            this.showFirstHalfObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
        }
    }

    /**
     * Displays or hides the second period objective textview
     */
    @Override
    public void displayOrHideSecondHalfObjective() {
        this.showSecondHalfObjective = !this.showSecondHalfObjective;
        if (this.showSecondHalfObjective){
            this.secondHalfObjectiveTextView.setVisibility(View.VISIBLE);
            this.showSecondHalfObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
        }
        else {
            this.secondHalfObjectiveTextView.setVisibility(View.INVISIBLE);
            this.showSecondHalfObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
        }
    }

    /**
     * Sets the selected wheel textview's text
     * @param wheel
     */
    @Override
    public void setWheelUsedTextView(String wheel) {
        this.wheelUsedTextView.setText(wheel);
    }

    @Override
    public void showError(String message) {

    }

    /**
     * Displays the right star image according to whats been picked
     * @param stars
     */
    @Override
    public void displayStars(int stars) {
        if (stars == 1) {
            this.starsImageView.setImageDrawable(getResources().getDrawable(R.drawable.onestar));
        }
        else if (stars == 2){
            this.starsImageView.setImageDrawable(getResources().getDrawable(R.drawable.twostars));
        }
        else if (stars == 3){
            this.starsImageView.setImageDrawable(getResources().getDrawable(R.drawable.threestars));
        }
        else if (stars == 4){
            this.starsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fourstars));

        }
        else if (stars == 5){
            this.starsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fivestars));
        }
    }
}
