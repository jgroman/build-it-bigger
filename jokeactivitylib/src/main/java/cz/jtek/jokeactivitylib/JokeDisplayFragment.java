package cz.jtek.jokeactivitylib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class JokeDisplayFragment extends Fragment {

    public JokeDisplayFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        Activity activity = getActivity();

        if (activity != null) {
            Intent intent = activity.getIntent();

            if (intent.hasExtra(JokeDisplayActivity.KEY_JOKE)) {
                String jokeText = intent.getStringExtra(JokeDisplayActivity.KEY_JOKE);
                TextView jokeTextView = root.findViewById(R.id.tv_joke);

                if (!TextUtils.isEmpty(jokeText) ) {
                    jokeTextView.setText(jokeText);
                }
            }
        }

        return root;
    }
}
