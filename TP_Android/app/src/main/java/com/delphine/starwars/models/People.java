package com.delphine.starwars.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Delphine on 15/01/2018.
 */

public class People implements Parcelable {
    private int count;
    private List<Personnage> results;

    public People(int count) {
        this.count = count;
        this.results = new ArrayList<>(this.count);
    }

    public int getCount() {
        return count;
    }

    public List<Personnage> getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<Personnage> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.count);
        out.writeTypedList(this.results);
        /*for(int i = 0; i < this.results.size(); i++) {
            this.results.get(i).writeToParcel(out, flags);
        }*/
    }

    public static final Parcelable.Creator<People> CREATOR = new Parcelable.Creator<People>() {
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        public People[] newArray(int size) {
            return new People[size];
        }
    };

    private People(Parcel in) {
        this.count = in.readInt();
        this.results = new ArrayList<>();
        in.readTypedList(this.results, Personnage.CREATOR);
        /*for(int i = 0; i < this.results.size(); i++) {
            this.getResults().set(i, (Personnage) in.readParcelable(Personnage.class.getClassLoader()));
        }*/
    }
}
