package personal.nmartinez.fr.spingoal.components.privacypolicy;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.nmartinez.fr.spingoal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyPolicyFragment extends Fragment {

    public static final String TAG = PrivacyPolicyFragment.class.getSimpleName();

    @BindView(R.id.tv_privacy_policy_content)
    TextView privacyPolicyContentTextView;

    public PrivacyPolicyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        ButterKnife.bind(this, view);

        privacyPolicyContentTextView.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://spin-goal.flycricket.io/privacy.html"));
            startActivity(intent);
        });
        return view;
    }

}
