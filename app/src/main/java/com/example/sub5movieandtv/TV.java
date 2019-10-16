package com.example.sub5movieandtv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TV implements Parcelable {
    @SerializedName("original_name")
    @Expose
    private String title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private String user_score;

    @SerializedName("popularity")
    @Expose
    private String popularity;

    @SerializedName("original_language")
    @Expose
    private String original_language;

    @SerializedName("vote_count")
    @Expose
    private String vote_count;

    @SerializedName("poster_path")
    @Expose
    private String photo;

    @SerializedName("title")
    @Expose
    private String genre;

    @SerializedName("first_air_date")
    @Expose
    private String date_released;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String status) {
        this.popularity = status;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(String runtime) {
        this.vote_count = runtime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate_released() {
        return date_released;
    }

    public void setDate_released(String date_released) {
        this.date_released = date_released;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.user_score);
        dest.writeString(this.popularity);
        dest.writeString(this.original_language);
        dest.writeString(this.vote_count);
        dest.writeString(this.photo);
        dest.writeString(this.genre);
        dest.writeString(this.date_released);

    }

    public TV() {
    }

    protected TV(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.user_score = in.readString();
        this.popularity = in.readString();
        this.original_language = in.readString();
        this.vote_count = in.readString();
        this.photo = in.readString();
        this.genre = in.readString();
        this.date_released = in.readString();

    }

    public static final Parcelable.Creator<TV> CREATOR = new Parcelable.Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel source) {
            return new TV(source);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };


}
