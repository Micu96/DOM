package com.akond.RankFilm.DB.DOM;

public class BoxOffice {

    private String imdbId;
    private Long budget;
    private Long gross;
    private String budgetCurrency;
    private String grossCurrency;

    public BoxOffice(String imdbId, Long budget, Long gross, String budgetCurrency,String grossCurrency) {
        this.imdbId = imdbId;
        this.budget = budget;
        this.gross = gross;
        this.budgetCurrency = budgetCurrency;
        this.grossCurrency = grossCurrency;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getGross() {
        return gross;
    }

    public void setGross(Long gross) {
        this.gross = gross;
    }

    public String getBudgetCurrency() {
        return budgetCurrency;
    }

    public void setBudgetCurrency(String budgetCurrency) {
        this.budgetCurrency = budgetCurrency;
    }

    public String getGrossCurrency() {
        return grossCurrency;
    }

    public void setGrossCurrency(String grossCurrency) {
        this.grossCurrency = grossCurrency;
    }
}