package personal.nmartinez.fr.virtualfootballpicker.homepage.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import personal.nmartinez.fr.virtualfootballpicker.MainActivity;
import personal.nmartinez.fr.virtualfootballpicker.R;
import personal.nmartinez.fr.virtualfootballpicker.homepage.core.HomePageCore;
import personal.nmartinez.fr.virtualfootballpicker.homepage.core.IHomePageCore;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private Button playSimpleScreenButton;
    private Button playSplitScreenButton;

    private IHomePageCore core;

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.core = new HomePageCore((MainActivity)getActivity());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        this.playSimpleScreenButton = (Button) view.findViewById(R.id.play_simple_game_button);
        this.playSplitScreenButton = (Button) view.findViewById(R.id.play_split_game_button);

        this.playSimpleScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.playSimpleScreen();
            }
        });

        this.playSplitScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.playSplitScreen();
            }
        });

        return view;
    }

}
