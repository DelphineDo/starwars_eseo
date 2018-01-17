package com.delphine.starwars;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delphine.starwars.models.Personnage;

/**
 * Created by Delphine on 15/01/2018.
 */

public class InfoPersoActivity extends AppCompatActivity {
    public static Intent getStartIntent(final Context context) {
        return new Intent(context, InfoPersoActivity.class);
    }

    private TextView namePerso;
    private TextView heightPerso;
    private TextView massPerso;
    private TextView hair_colorPerso;
    private TextView skin_colorPerso;
    private TextView eye_colorPerso;
    private TextView birth_yearPerso;
    private ImageView genderPerso;
    private TextView homeworldPerso;
    private TextView films;
    private TextView species;
    private TextView vehicles;
    private TextView starships;
    private ImageView surprisePerso;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_personnages);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Personnage personnage = getIntent().getParcelableExtra("personnage");

        namePerso = findViewById(R.id.namePerso);
        namePerso.setText(getString(R.string.nom_1_s, personnage.getName()));

        heightPerso = findViewById(R.id.heightPerso);
        heightPerso.setText(getString(R.string.taille_1_s, personnage.getHeight()));

        massPerso = findViewById(R.id.massPerso);
        massPerso .setText(getString(R.string.poids_1_s, personnage.getMass()));

        hair_colorPerso = findViewById(R.id.hair_colorPerso);
        hair_colorPerso .setText(getString(R.string.couleur_de_cheveux_1_s, personnage.getHair_color()));

        skin_colorPerso = findViewById(R.id.skin_colorPerso);
        skin_colorPerso .setText(getString(R.string.couleur_de_peau_1_s, personnage.getSkin_color()));

        eye_colorPerso = findViewById(R.id.eye_colorPerso);
        eye_colorPerso .setText(getString(R.string.couleur_des_yeux_1_s, personnage.getEye_color()));

        birth_yearPerso = findViewById(R.id.birth_yearPerso);
        birth_yearPerso.setText(getString(R.string.ann_e_de_naissance_1_s, personnage.getBirth_year()));

        homeworldPerso = findViewById(R.id.homeworldPerso);
        homeworldPerso.setText(getString(R.string.monde_d_origine_1_s, personnage.getHomeworld()));

        genderPerso = findViewById(R.id.genderPerso);
        if(personnage.getGender().equals("male")){
            genderPerso.setImageResource(R.drawable.icon_male);
        }
        else if(personnage.getGender().equals("female")){
            genderPerso.setImageResource(R.drawable.icon_woman);
        }
        else{
            genderPerso.setImageResource(R.drawable.icon_robot);
        }

        films = findViewById(R.id.films);
        int i;
        for(i=0; i<=personnage.getFilms().size(); i++){
            films.setText(getString(R.string.films_1_s, personnage.getFilms()));
        }

        species=findViewById(R.id.species);
        for(i=0; i<=personnage.getSpecies().size(); i++){
            species.setText(getString(R.string.esp_ces_1_s, personnage.getSpecies()));
        }

        vehicles = findViewById(R.id.vehicles);
        for(i=0; i<=personnage.getVehicles().size(); i++){
            vehicles.setText(getString(R.string.vehicules_1_s, personnage.getVehicles()));
        }
        starships = findViewById(R.id.starships);
        for(i=0; i<=personnage.getStarships().size(); i++){
            starships.setText(getString(R.string.vaisseaux_1_s, personnage.getStarships()));
        }
        /**
         * Characters' surprise
         */
        surprisePerso = findViewById(R.id.surprisePerso);

        if(personnage.getName().equals("Darth Vader")) {
            Toast.makeText(InfoPersoActivity.this, "Non, je suis ton père", Toast.LENGTH_LONG).show();
            surprisePerso.setImageResource(R.drawable.darth_vader_icon);
        }
        if(personnage.getName().equals("Chewbacca")) {
            Toast.makeText(InfoPersoActivity.this, "Rrraarrwhhgwwr", Toast.LENGTH_LONG).show();
            surprisePerso.setImageResource(R.drawable.icon_chewbacca);
        }
        if(personnage.getName().equals("Yoda")) {
            Toast.makeText(InfoPersoActivity.this, "La peur est le chemin vers le côté obscur: la peur mène à la colère,  le colère mène à la haine, la haine mène à la souffrance.", Toast.LENGTH_LONG).show();
            surprisePerso.setImageResource(R.drawable.icon_yoda);
        }
        if(personnage.getName().equals("C-3PO")) {
            Toast.makeText(InfoPersoActivity.this, "Dieu me débranche ! Des machines qui créent des machines !", Toast.LENGTH_LONG).show();
            surprisePerso.setImageResource(R.drawable.icon_c3po);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}