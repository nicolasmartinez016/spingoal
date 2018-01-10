package personal.nmartinez.fr.virtualfootballpicker.playofflinemulti;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import personal.nmartinez.fr.virtualfootballpicker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaySplitScreenFragment extends Fragment {


    public PlaySplitScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_split_screen, container, false);
    }

}
