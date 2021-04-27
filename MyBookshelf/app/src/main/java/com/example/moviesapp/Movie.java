package com.example.moviesapp;

public class Movie {

    String title;
    String year;
    String Rated;
    String Released;
    String Runtime;
    String Genre;
    String Director;
    String Actors;
    String Plot;
    String Language;
    String Country;
    String Awards;
    String Poster;
    String Metascore;
    Float imdbRating;

    Boolean favorito = false;

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }


    public Movie() {
    }

    public Movie(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String plot, String language, String country, String awards, String poster, String metascore, Float imdbRating) {
        this.title = title;
        this.year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Awards = awards;
        Poster = poster;
        Metascore = metascore;
        this.imdbRating = imdbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }
}
