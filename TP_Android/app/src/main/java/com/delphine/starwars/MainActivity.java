package com.delphine.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.delphine.starwars.models.HttpError;
import com.delphine.starwars.models.People;
import com.delphine.starwars.models.Personnage;
import com.delphine.starwars.remote.ApiService;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final ApiService apiService = ApiService.Builder.getInstance();
    Button listButton;
    People people = new People(0);
    boolean asked = false;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(onListButtonClicked);

        loader=findViewById(R.id.loader);
    }

    private final View.OnClickListener onListButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            listButton.setEnabled(false); // Pour ne pas cliquer deux fois sur le bouton
            loader.setVisibility(View.VISIBLE);
            people.setCount(0);
            people.getResults().clear();
            httpRequest(1);
            asked = false;
        }
    };

    /**
     * Http request to read a page
     */
    private void httpRequest(int page) {
        apiService.readPeople(Integer.toString(page)).enqueue(new Callback<People>() {
            @Override
            public void onResponse(final Call<People> call, final Response<People> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleResponse(response);
                    }
                });
            }

            @Override
            public void onFailure(final Call<People> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loader.setVisibility(View.INVISIBLE);
                        listButton.setEnabled(true);
                        Toast.makeText(MainActivity.this, R.string.status_error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * Handle default response for both GET/POST methods
     */
    private void handleResponse(final Response<People> response) {
        //Attend la reponse
        if (response.isSuccessful()) {
            if(people.getCount() == 0) {
                people.setCount(response.body().getCount());
            }
            for(int i = 0; i < response.body().getResults().size(); i++) {
                Personnage personnage = response.body().getResults().get(i);
                people.getResults().add(personnage);
            }

            if(people.getResults().size() == people.getCount()) {
                final Intent i = PersoActivity.getStartIntent(MainActivity.this);
                i.putExtra("people", people);
                startActivity(i);
                listButton.setEnabled(true); // Permet de réactiver le bouton
                loader.setVisibility(View.INVISIBLE);
            } else if(!asked) {
                // Warning: we consider 10 characters per page
                for(int i = 2; i <= (people.getCount() / 10) + 1; i++) {
                    httpRequest(i);
                }
                asked = true;
            }

        } else { // error HTTP
            try {
                //Essayer de transformer l'erreur de la  gson et on va chercher le erreur body qu'on transforme en string
                final HttpError error = new Gson().fromJson(response.errorBody().string(), HttpError.class);
                //Message d'erreur en cas de problème
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (final IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
