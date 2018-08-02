package com.akond.RankFilm.DB.DOM;

public class AcademyAward {
    private String imdb_id;
    private String title;
    private String winning_year;
    private String nomination;
    private String win;

    public AcademyAward(String imdb_id, String title, String winning_year, String nomination, String win) {
        this.imdb_id = imdb_id;
        this.title = title;
        this.winning_year = winning_year;
        this.nomination = nomination;
        this.win = win;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWinning_year() {
        return winning_year;
    }

    public void setWinning_year(String winning_year) {
        this.winning_year = winning_year;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
