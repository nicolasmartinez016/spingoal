package personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.view;


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
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.presenter.FirstPlayerSecondObjectiveView;
import personal.nmartinez.fr.virtualfootballpicker.game.firstplayersecondobjective.presenter.FirstPlayerSecondObjectivePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.models.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerOneSelectSecondObjectiveFragment extends Fragment implements FirstPlayerSecondObjectiveView {

    public static final String TAG = PlayerOneSelectSecondObjectiveFragment.class.getName();
    private static final String GAME_KEY = "gameKey";

    @BindView(R.id.first_player_second_period_objective_textview) TextView objectiveTextView;
    @BindView(R.id.pick_first_player_second_objective_button) ImageView selectObjectiveButton;
    @BindView(R.id.first_player_second_objective_next_button) TextView nextButton;
    @BindView(R.id.iv_first_player_second_objective_hat) ImageView hatImageView;

    private FirstPlayerSecondObjectivePresenter presenter;

    public PlayerOneSelectSecondObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Game game = (Game) getArguments().getSerializable(GAME_KEY);
            this.presenter = new FirstPlayerSecondObjectivePresenterImpl(this, game);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_one_select_second_objective, container, false);
        ButterKnife.bind(this, view);

        if (selectObjectiveButton != null) {
            selectObjectiveButton.setOnClickListener(view1 -> {
                if (presenter != null) {
                    presenter.selectObjective();
                }
            });
        }

        if (nextButton != null) {
            nextButton.setOnClickListener(view12 -> {
                if (presenter != null) {
                    presenter.validateStepFive();
                }
            });
        }
        ((HideShowIconInterface) getActivity()).showHamburgerIcon();
        return view;
    }

    public static PlayerOneSelectSecondObjectiveFragment newInstance(Game game) {
        PlayerOneSelectSecondObjectiveFragment fragment = new PlayerOneSelectSecondObjectiveFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME_KEY, game);
        fragment.setArguments(bundle);
        return  fragment;
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
                    hatImageView.setVisibility(View.GONE);
                    objectiveTextView.setVisibility(View.VISIBLE);
                    objectiveTextView.setText(objective);
                    selectObjectiveButton.setVisibility(View.GONE);
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
    public void goToStepSix(Game game) {
        NavigationManager.getInstance().selectSecondPlayerSecondObjective(game);
    }
}
