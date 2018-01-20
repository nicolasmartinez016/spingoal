package personal.nmartinez.fr.virtualfootballpicker.playofflinemulti;


import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import personal.nmartinez.fr.virtualfootballpicker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaySplitScreenFragment extends Fragment implements IPlaySplitScreenView {

    private IPlaySplitScreenCore core;

    private TextView selectedWheelTextview;

    private TextView firstPlayerFirstPeriodObjectiveTextView;
    private TextView firstPlayerSecondPeriodObjectiveTextView;
    private TextView secondPlayerFirstPeriodObjectiveTextView;
    private TextView secondPlayerSecondPeriodObjectiveTextView;

    private Button pickFirstPlayerStarsButton;
    private Button pickSecondPlayerStarsButton;
    private Button pickFirstPlayerFirstPeriodObjectiveButton;
    private Button pickSecondPlayerFirstPeriodObjectiveButton;
    private Button pickFirstPlayerSecondPeriodObjectiveButton;
    private Button pickSecondPlayerSecondPeriodObjectiveButton;
    private Button hideFirstPlayerFirstPeriodObjectiveButton;
    private Button hideFirstPlayerSecondPeriodObjectiveButton;
    private Button hideSecondPlayerFirstPeriodObjectiveButton;
    private Button hideSecondPlayerSecondPeriodObjectiveButton;

    private ImageView firstPlayerStarsImageView;
    private ImageView secondPlayerStarsImageView;


    public PlaySplitScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_play_split_screen, container, false);

        this.selectedWheelTextview = (TextView) view.findViewById(R.id.selected_wheel_splitscreen_label_textview);

        this.firstPlayerFirstPeriodObjectiveTextView = (TextView) view.findViewById(R.id.player_1_period_1_objective_textview);
        this.firstPlayerSecondPeriodObjectiveTextView = (TextView) view.findViewById(R.id.player_1_period_2_objective_textview);
        this.secondPlayerFirstPeriodObjectiveTextView = (TextView) view.findViewById(R.id.player_2_period_1_objective_textview);
        this.secondPlayerSecondPeriodObjectiveTextView = (TextView) view.findViewById(R.id.player_2_period_2_objective_textview);

        this.pickFirstPlayerFirstPeriodObjectiveButton = (Button) view.findViewById(R.id.player_1_pick_period_1_objective_button);
        this.pickFirstPlayerSecondPeriodObjectiveButton = (Button) view.findViewById(R.id.player_1_pick_period_2_objective_button);
        this.pickSecondPlayerFirstPeriodObjectiveButton = (Button) view.findViewById(R.id.player_2_pick_period_1_objective_button);
        this.pickSecondPlayerSecondPeriodObjectiveButton = (Button) view.findViewById(R.id.player_2_pick_period_2_objective_button);

        this.hideFirstPlayerFirstPeriodObjectiveButton = (Button) view.findViewById(R.id.player_1_period_1_display_or_hide_objective_button);
        this.hideFirstPlayerSecondPeriodObjectiveButton = (Button) view.findViewById(R.id.player_1_period_2_display_or_hide_objective_button);
        this.hideSecondPlayerFirstPeriodObjectiveButton = (Button) view.findViewById(R.id.player_2_period_1_display_or_hide_objective_button);
        this.hideSecondPlayerSecondPeriodObjectiveButton = (Button) view.findViewById(R.id.player_2_period_2_display_or_hide_objective_button);

        this.pickFirstPlayerStarsButton = (Button) view.findViewById(R.id.player_1_pick_stars_button);
        this.pickSecondPlayerStarsButton = (Button) view.findViewById(R.id.player_2_pick_stars_button);

        this.firstPlayerStarsImageView = (ImageView) view.findViewById(R.id.player_1_stars_imageview);
        this.secondPlayerStarsImageView = (ImageView) view.findViewById(R.id.player_2_stars_imageview);

        this.pickFirstPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickFirstPlayerFirstPeriodObjective();
            }
        });

        this.pickSecondPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickSecondPlayerFirstPeriodObjective();
            }
        });

        this.pickFirstPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickFirstPlayerSecondPeriodObjective();
            }
        });

        this.pickSecondPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickSecondPlayerSecondPeriodObjective();
            }
        });

        this.pickFirstPlayerStarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickFirstPlayerStars();
            }
        });

        this.pickSecondPlayerStarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.pickSecondPlayerStars();
            }
        });

        this.core = new PlaySplitScreenCore(getActivity(), this);

        return view;
    }

    @Override
    public void pickFirstPlayerFirstPeriodObjective(String objective) {
        this.firstPlayerFirstPeriodObjectiveTextView.setText(objective);
        this.hideFirstPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickFirstPlayerSecondPeriodObjective(String objective) {
        this.firstPlayerSecondPeriodObjectiveTextView.setText(objective);
        this.hideFirstPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickSecondPlayerFirstPeriodObjective(String objective) {
        this.secondPlayerFirstPeriodObjectiveTextView.setText(objective);
        this.hideSecondPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickSecondPlayerSecondPeriodObjective(String objective) {
        this.secondPlayerSecondPeriodObjectiveTextView.setText(objective);
        this.hideSecondPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickFirstPlayerStars(int stars) {
        this.firstPlayerStarsImageView.setVisibility(View.VISIBLE);
        if (stars == 1) {
            this.firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.onestar));
        }
        else if (stars == 2){
            this.firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.twostars));
        }
        else if (stars == 3){
            this.firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.threestars));
        }
        else if (stars == 4){
            this.firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fourstars));

        }
        else if (stars == 5){
            this.firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fivestars));
        }
    }

    @Override
    public void pickSecondPlayerStars(int stars) {
        this.secondPlayerStarsImageView.setVisibility(View.VISIBLE);
        if (stars == 1) {
            this.secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.onestar));
        }
        else if (stars == 2){
            this.secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.twostars));
        }
        else if (stars == 3){
            this.secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.threestars));
        }
        else if (stars == 4){
            this.secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fourstars));

        }
        else if (stars == 5){
            this.secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fivestars));
        }
    }

    @Override
    public void setWheelToBeUsed(String wheel) {
        this.selectedWheelTextview.setText(wheel);
    }

    @Override
    public void showError(String message) {

    }

    private void setObjectiveVisibility(TextView textView, Button button){
        if (textView.getVisibility() == View.VISIBLE){
            textView.setVisibility(View.GONE);
        }
    }
}
