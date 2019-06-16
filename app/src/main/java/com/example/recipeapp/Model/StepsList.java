package com.example.recipeapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StepsList implements Parcelable {

    public static final Creator<StepsList> CREATOR = new Creator<StepsList>() {
        @Override
        public StepsList createFromParcel(Parcel in) {
            return new StepsList(in);
        }

        @Override
        public StepsList[] newArray(int size) {
            return new StepsList[size];
        }
    };
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;

    protected StepsList(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public String toString() {
        return "StepsList{" +
                "shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
    }
}
