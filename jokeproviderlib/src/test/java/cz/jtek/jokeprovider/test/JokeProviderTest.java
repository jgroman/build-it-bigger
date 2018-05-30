package cz.jtek.jokeprovider.test;

import org.junit.Test;

import cz.jtek.jokeprovider.JokeProvider;

public class JokeProviderTest {

    @Test
    public void test() {
        JokeProvider jokeProvider = new JokeProvider();
        assert jokeProvider.getJoke().length() != 0;
    }

}
