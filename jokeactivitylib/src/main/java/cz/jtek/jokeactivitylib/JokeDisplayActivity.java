package cz.jtek.jokeactivitylib;

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
}
