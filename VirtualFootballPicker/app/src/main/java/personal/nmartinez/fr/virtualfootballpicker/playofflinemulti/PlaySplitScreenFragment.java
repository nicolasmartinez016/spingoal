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
import personal.nmartinez.fr.virtualfootballpicker.playofflinemulti.dialogs.AllObjectivesUsedDialog;

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

    private ProgressBar firstPlayerFirstPeriodObjectiveProgressBar;
    private ProgressBar firstPlayerSecondPeriodObjectiveProgressBar;
    private ProgressBar secondPlayerFirstPeriodObjectiveProgressBar;
    private ProgressBar secondPlayerSecondPeriodObjectiveProgressBar;

    private ImageView pickFirstPlayerStarsButton;
    private ImageView pickSecondPlayerStarsButton;
    private ImageView pickFirstPlayerFirstPeriodObjectiveButton;
    private ImageView pickSecondPlayerFirstPeriodObjectiveButton;
    private ImageView pickFirstPlayerSecondPeriodObjectiveButton;
    private ImageView pickSecondPlayerSecondPeriodObjectiveButton;
    private ImageView hideFirstPlayerFirstPeriodObjectiveButton;
    private ImageView hideFirstPlayerSecondPeriodObjectiveButton;
    private ImageView hideSecondPlayerFirstPeriodObjectiveButton;
    private ImageView hideSecondPlayerSecondPeriodObjectiveButton;

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

        this.firstPlayerFirstPeriodObjectiveProgressBar = (ProgressBar) view.findViewById(R.id.first_player_first_objective_progressbar);
        this.firstPlayerSecondPeriodObjectiveProgressBar = (ProgressBar) view.findViewById(R.id.first_player_second_objective_progressbar);
        this.secondPlayerFirstPeriodObjectiveProgressBar = (ProgressBar) view.findViewById(R.id.second_player_first_objective_progressbar);
        this.secondPlayerSecondPeriodObjectiveProgressBar = (ProgressBar) view.findViewById(R.id.second_player_second_objective_progressbar);

        this.pickFirstPlayerFirstPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_1_pick_period_1_objective_button);
        this.pickFirstPlayerSecondPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_1_pick_period_2_objective_button);
        this.pickSecondPlayerFirstPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_2_pick_period_1_objective_button);
        this.pickSecondPlayerSecondPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_2_pick_period_2_objective_button);

        this.hideFirstPlayerFirstPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_1_period_1_display_or_hide_objective_button);
        this.hideFirstPlayerSecondPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_1_period_2_display_or_hide_objective_button);
        this.hideSecondPlayerFirstPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_2_period_1_display_or_hide_objective_button);
        this.hideSecondPlayerSecondPeriodObjectiveButton = (ImageView) view.findViewById(R.id.player_2_period_2_display_or_hide_objective_button);

        this.pickFirstPlayerStarsButton = (ImageView) view.findViewById(R.id.player_1_pick_stars_button);
        this.pickSecondPlayerStarsButton = (ImageView) view.findViewById(R.id.player_2_pick_stars_button);

        this.firstPlayerStarsImageView = (ImageView) view.findViewById(R.id.player_1_stars_imageview);
        this.secondPlayerStarsImageView = (ImageView) view.findViewById(R.id.player_2_stars_imageview);

        this.firstPlayerStarsProgressbar = (ProgressBar) view.findViewById(R.id.first_player_stars_progressbar);
        this.secondPlayerStarsProgressbar = (ProgressBar) view.findViewById(R.id.second_player_stars_progressbar);

        this.hideFirstPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.GONE);
                    hideFirstPlayerFirstPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
                    pickFirstPlayerFirstPeriodObjectiveButton.setVisibility(View.GONE);
                }
                else if (firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        firstPlayerFirstPeriodObjectiveTextView.getVisibility() == View.GONE){
                    firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    pickFirstPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
                    hideFirstPlayerFirstPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
                }
            }
        });

        this.hideFirstPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.GONE);
                    hideFirstPlayerSecondPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
                    pickFirstPlayerSecondPeriodObjectiveButton.setVisibility(View.GONE);

                }
                else if (firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        firstPlayerSecondPeriodObjectiveTextView.getVisibility() == View.GONE){
                    firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideFirstPlayerSecondPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
                    pickFirstPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
                }
            }
        });

        this.hideSecondPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.GONE);
                    hideSecondPlayerFirstPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
                    pickSecondPlayerFirstPeriodObjectiveButton.setVisibility(View.GONE);

                }
                else if (secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        secondPlayerFirstPeriodObjectiveTextView.getVisibility() == View.GONE){
                    secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideSecondPlayerFirstPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
                    pickSecondPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
                }
            }
        });

        this.hideSecondPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.VISIBLE){
                    secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.GONE);
                    hideSecondPlayerSecondPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.show_icon));
                    pickSecondPlayerSecondPeriodObjectiveButton.setVisibility(View.GONE);
                }
                else if (secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.INVISIBLE ||
                        secondPlayerSecondPeriodObjectiveTextView.getVisibility() == View.GONE){
                    secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                    hideSecondPlayerSecondPeriodObjectiveButton.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.hide_icon));
                    pickSecondPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
                }
            }
        });

        this.pickFirstPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.GONE);
                firstPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        core.pickFirstPlayerFirstPeriodObjective();
                        firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                        firstPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.GONE);
                    }
                }, 500);
            }
        });

        this.pickSecondPlayerFirstPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.GONE);
                secondPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        core.pickSecondPlayerFirstPeriodObjective();
                        secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                        secondPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.GONE);
                    }
                }, 500);
            }
        });

        this.pickFirstPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.GONE);
                firstPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        core.pickFirstPlayerSecondPeriodObjective();
                        firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                        firstPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.GONE);
                    }
                }, 500);
            }
        });

        this.pickSecondPlayerSecondPeriodObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.GONE);
                secondPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        core.pickSecondPlayerSecondPeriodObjective();
                        secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
                        secondPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.GONE);
                    }
                }, 500);
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
        this.firstPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
        this.firstPlayerFirstPeriodObjectiveTextView.setText(objective);
        this.hideFirstPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickFirstPlayerSecondPeriodObjective(String objective) {
        this.firstPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
        this.firstPlayerSecondPeriodObjectiveTextView.setText(objective);
        this.hideFirstPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickSecondPlayerFirstPeriodObjective(String objective) {
        this.secondPlayerFirstPeriodObjectiveTextView.setVisibility(View.VISIBLE);
        this.secondPlayerFirstPeriodObjectiveTextView.setText(objective);
        this.hideSecondPlayerFirstPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickSecondPlayerSecondPeriodObjective(String objective) {
        this.secondPlayerSecondPeriodObjectiveTextView.setVisibility(View.VISIBLE);
        this.secondPlayerSecondPeriodObjectiveTextView.setText(objective);
        this.hideSecondPlayerSecondPeriodObjectiveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void pickFirstPlayerStars(final int stars) {
        this.firstPlayerStarsImageView.setVisibility(View.GONE);
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
        this.secondPlayerStarsImageView.setVisibility(View.GONE);
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

    @Override
    public void displayAllObjectivesUsedDialog() {
        new AllObjectivesUsedDialog().show(getFragmentManager(), "");
        this.firstPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.GONE);
        this.firstPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.GONE);
        this.secondPlayerFirstPeriodObjectiveProgressBar.setVisibility(View.GONE);
        this.secondPlayerSecondPeriodObjectiveProgressBar.setVisibility(View.GONE);
    }

    private void setObjectiveVisibility(TextView textView, Button button){
        if (textView.getVisibility() == View.VISIBLE){
            textView.setVisibility(View.GONE);
        }
    }
}
