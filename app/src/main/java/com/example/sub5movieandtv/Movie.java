package com.example.sub5movieandtv;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("title")
    @Expose
    private String name;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private Double user_score;

    @SerializedName("popularity")
    @Expose
    private String popularity;

    @SerializedName("original_language")
    @Expose
    private String language;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("vote_count")
    @Expose
    private String vote_count;

    @SerializedName("poster_path")
    @Expose
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getUser_score() {
        return user_score;
    }

    public void setUser_score(Double user_score) {
        this.user_score = user_score;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String status) {
        this.popularity = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String runtime) {
        this.release_date = runtime;
    }

    public String getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(String revenue) {
        this.vote_count = revenue;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeValue(this.user_score);
        dest.writeString(this.popularity);
        dest.writeString(this.language);
        dest.writeString(this.release_date);
        dest.writeString(this.vote_count);
        dest.writeString(this.photo);

    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.name = in.readString();
        this.overview = in.readString();
        this.user_score = (Double) in.readValue(Double.class.getClassLoader());
        this.popularity = in.readString();
        this.language = in.readString();
        this.release_date = in.readString();
        this.vote_count = in.readString();
        this.photo = in.readString();

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


}
