package cz.jtek.jokeactivitylib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class JokeDisplayActivity extends AppCompatActivity {

    // Bundle keys
    public static String KEY_JOKE = "joke";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }

    /**
     * Intent to start this activity
     *
     * @param context Context
     * @param joke Joke text
     * @return Intent
     */
    public static Intent newIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(KEY_JOKE, joke);
        return intent;
    }
}
