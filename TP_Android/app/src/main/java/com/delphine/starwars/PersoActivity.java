package com.delphine.starwars;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ListView;


import com.delphine.starwars.models.People;
import com.delphine.starwars.models.Personnage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Delphine on 13/01/2018.
 */

public class PersoActivity extends AppCompatActivity{
    public static Intent getStartIntent(final Context context) {
        return new Intent(context, PersoActivity.class);
    }

    private List<Personnage> listOfPersonnnages = new ArrayList<>();
    private PersoAdapter persoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnages);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        People people = getIntent().getParcelableExtra("people");
        this.listOfPersonnnages = people.getResults();

        final ListView personnagesView = findViewById(R.id.listView);

        persoAdapter = new PersoAdapter(this, this.listOfPersonnnages, personnageSelectedListener);
        personnagesView.setAdapter(persoAdapter);
    }

    /**
     * Triggered when a device is selected
     */
    private final PersoAdapter.OnPersonnageSelectedListener personnageSelectedListener = new PersoAdapter.OnPersonnageSelectedListener() {
        @Override
        public void handle(final Personnage personnage) {
            final Intent i = InfoPersoActivity.getStartIntent(PersoActivity.this);
            i.putExtra("personnage", personnage);
            startActivity(i);
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
