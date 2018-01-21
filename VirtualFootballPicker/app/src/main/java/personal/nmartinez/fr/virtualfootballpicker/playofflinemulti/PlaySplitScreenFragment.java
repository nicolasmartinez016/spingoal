package personal.nmartinez.fr.virtualfootballpicker.playofflinemulti;


import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    private ProgressBar firstPlayerStarsProgressbar;
    private ProgressBar secondPlayerStarsProgressbar;

    private ImageView firstPlayerStarsImageView;
    private ImageView secondPlayerStarsImageView;


    public PlaySplitScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
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

        this.firstPlayerStarsProgressbar = (ProgressBar) view.findViewById(R.id.first_player_stars_progressbar);
        this.secondPlayerStarsProgressbar = (ProgressBar) view.findViewById(R.id.second_player_stars_progressbar);

        this.hideFirstPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.INVISIBLE);
                    hideFirstPlayerFirstPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.display_objective));
                }
                else if (firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.GONE){
                    firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideFirstPlayerFirstPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.hide_objective));
                }
            }
        });

        this.hideFirstPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.INVISIBLE);
                    hideFirstPlayerSecondPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.display_objective));
                }
                else if (firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.GONE){
                    firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideFirstPlayerSecondPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.hide_objective));
                }
            }
        });

        this.hideSecondPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.INVISIBLE);
                    hideSecondPlayerFirstPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.display_objective));
                }
                else if (secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.GONE){
                    secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideSecondPlayerFirstPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.hide_objective));
                }
            }
        });

        this.hideSecondPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.INVISIBLE);
                    hideSecondPlayerSecondPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.display_objective));
                }
                else if (secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.GONE){
                    secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideSecondPlayerSecondPeriodObjectiveButton.setText(getActivity().getResources().getString(R.string.hide_objective));
                }
            }
        });

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
    public void pickFirstPlayerStars(final int stars) {
        this.firstPlayerStarsImageView.setVisibility(View.INVISIBLE);
        this.firstPlayerStarsProgressbar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                if (stars == 1) {
                    firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.onestar));
                }
                else if (stars == 2){
                    firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.twostars));
                }
                else if (stars == 3){
                    firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.threestars));
                }
                else if (stars == 4){
                    firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fourstars));

                }
                else if (stars == 5){
                    firstPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fivestars));
                }
                firstPlayerStarsProgressbar.setVisibility(View.INVISIBLE);
                firstPlayerStarsImageView.setVisibility(View.VISIBLE);
            }
        }, 500);


    }

    @Override
    public void pickSecondPlayerStars(final int stars) {
        this.secondPlayerStarsImageView.setVisibility(View.INVISIBLE);
        secondPlayerStarsProgressbar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                if (stars == 1) {
                    secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.onestar));
                }
                else if (stars == 2){
                    secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.twostars));
                }
                else if (stars == 3){
                    secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.threestars));
                }
                else if (stars == 4){
                    secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fourstars));

                }
                else if (stars == 5){
                    secondPlayerStarsImageView.setImageDrawable(getResources().getDrawable(R.drawable.fivestars));
                }
                secondPlayerStarsProgressbar.setVisibility(View.INVISIBLE);
                secondPlayerStarsImageView.setVisibility(View.VISIBLE);
            }
        }, 500);

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
