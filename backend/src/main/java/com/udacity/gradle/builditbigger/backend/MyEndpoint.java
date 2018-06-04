package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import cz.jtek.jokeprovider.JokeProvider;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "fetchJoke")
    public MyBean fetchJoke() {
        JokeProvider jokeProvider = new JokeProvider();
        String jokeText = jokeProvider.getJoke();

        MyBean response = new MyBean();
        response.setData(jokeText);

        return response;
    }
}
