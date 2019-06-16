package com.example.recipeapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IngredientsList implements Parcelable {

    public static final Creator<IngredientsList> CREATOR = new Creator<IngredientsList>() {
        @Override
        public IngredientsList createFromParcel(Parcel in) {
            return new IngredientsList(in);
        }

        @Override
        public IngredientsList[] newArray(int size) {
            return new IngredientsList[size];
        }
    };
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    protected IngredientsList(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "IngredientsList{" +
                "quantity='" + quantity + '\'' +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }
}
