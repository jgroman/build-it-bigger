package cz.jtek.jokeactivitylib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeDisplayFragment extends Fragment {

    public JokeDisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        Intent intent = getActivity().getIntent();

        String jokeText = intent.getStringExtra(JokeDisplayActivity.KEY_JOKE);
        TextView jokeTextView = root.findViewById(R.id.tv_joke);

        if (jokeText != null && jokeText.length() != 0) {
            jokeTextView.setText(jokeText);
        }

        return root;
    }
}
