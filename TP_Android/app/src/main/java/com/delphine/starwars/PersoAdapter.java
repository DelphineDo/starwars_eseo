package com.delphine.starwars;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.delphine.starwars.models.Personnage;

import java.util.List;

/**
 * Created by Delphine on 13/01/2018.
 */

public class PersoAdapter extends ArrayAdapter<Personnage> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnPersonnageSelectedListener {
        void handle(final Personnage personnage);
    }

    private final OnPersonnageSelectedListener onPersonnageSelectedListener;

    PersoAdapter(@NonNull final Context context, final List<Personnage> devices, final OnPersonnageSelectedListener listener) {
        super(context, R.layout.perso_list_item, devices);
        onPersonnageSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.perso_list_item, null);
        }

        final Personnage personnage = getItem(position);
        if (personnage == null) {
            return holder;
        }

        // display the name
        final TextView deviceName = holder.findViewById(R.id.persoName);
        if (deviceName != null) {
            deviceName.setText(personnage.getName());
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onPersonnageSelectedListener != null) {
                    onPersonnageSelectedListener.handle(personnage);
                }
            }
        });
        return holder;
    }

}
