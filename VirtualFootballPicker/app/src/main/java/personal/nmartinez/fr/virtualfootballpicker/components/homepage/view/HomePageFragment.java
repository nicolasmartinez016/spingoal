package personal.nmartinez.fr.virtualfootballpicker.components.homepage.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import personal.nmartinez.fr.virtualfootballpicker.navigation.HideShowIconInterface;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.components.homepage.core.HomePagePresenterImpl;
import personal.nmartinez.fr.virtualfootballpicker.components.homepage.core.HomePagePresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    public static final String TAG = HomePageFragment.class.getName();

    private Button playSimpleScreenButton;

    private HomePagePresenter core;

    public HomePageFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.core = new HomePagePresenterImpl();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        this.playSimpleScreenButton = view.findViewById(R.id.play_simple_game_button);

        this.playSimpleScreenButton.setOnClickListener(view1 -> core.playSimpleScreen());

        ((HideShowIconInterface) getActivity()).showHamburgerIcon();

        return view;
    }

}
