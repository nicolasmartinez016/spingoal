package personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.view;


import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.virtualfootballpicker.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.NavigationManager;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.presenter.SecondPlayerFirstObjectivePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.game.secondplayerfirstobjective.presenter.SecondPlayerFirstObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerTwoSelectFirstObjectiveFragment extends Fragment implements SecondPlayerFirstObjectiveView {

    private static final String GAME_KEY = "gameKey";
    public static final String TAG = PlayerTwoSelectFirstObjectiveFragment.class.getName();

    private SecondPlayerFirstObjectivePresenter presenter;

    @BindView(R.id.tv_second_player_first_period_objective_value) TextView objectiveTextView;
    @BindView(R.id.iv_second_first_player_first_objective_button) ImageView selectObjectiveButton;
    @BindView(R.id.tv_second_player_first_objective_next_button) TextView nextButton;
    @BindView(R.id.iv_second_player_first_objective_hat) ImageView hatImageView;


    public PlayerTwoSelectFirstObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            this.presenter = new SecondPlayerFirstObjectivePresenterImpl(this, game);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_player_select_first_objective_layout, container, false);
        ButterKnife.bind(this, view);

        if (nextButton != null) {
            nextButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.validateStepFour();
                }
            });

            if (selectObjectiveButton != null) {
                selectObjectiveButton.setOnClickListener(view12 -> {
                    if (presenter != null) {
                        presenter.selectObjective();
                    }
                });
            }
        }

        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    public static PlayerTwoSelectFirstObjectiveFragment newInstance(Game game) {
        PlayerTwoSelectFirstObjectiveFragment fragment = new PlayerTwoSelectFirstObjectiveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showObjective(String objective) {
        nextButton.setTextColor(getResources().getColor(R.color.white));
        nextButton.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        if (objectiveTextView != null) {
            hatImageView.animate().alpha(0f).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    selectObjectiveButton.setVisibility(View.GONE);
                    hatImageView.setVisibility(View.GONE);
                    objectiveTextView.animate().alpha(1f).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            objectiveTextView.setAlpha(0f);
                            objectiveTextView.setVisibility(View.VISIBLE);
                            objectiveTextView.setText(objective);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }
    }

    @Override
    public void goToStepFive(Game game) {
        NavigationManager.getInstance().selectFirstPlayerSecondObjective(game);
    }
}
