package com.akond.RankFilm.DB.DOM.Logic;

import com.akond.RankFilm.DB.DOM.DB.AcademyAward;
import com.akond.RankFilm.DB.DOM.DB.BoxOffice;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public  class Reader {

    public static List<AcademyAward> readAwardsFromUrl(Set<String> imdbIds, String url) {

        List<AcademyAward> academyAwardList = new ArrayList<>();
        for (String imdbId : imdbIds) {
            try {
                String newUrl = new StringBuilder(url)
                        .append("/")
                        .append(imdbId)
                        .append("/")
                        .append("awards")
                        .toString();

                Connection connection = Jsoup.connect(newUrl);
                Document document = connection.get();
                String[] split = document.title().split(" -");
                String title = split[0];
                Element bestPicture = document.select("table").get(0);
                if (document.select("span.award_category").first().ownText().equals("Oscar")) {
                    String eventYear = document.select("a[href].event_year").first().ownText();
                    Elements row = bestPicture.select("td.award_description");
                    int oscarsAmount = Integer.parseInt(bestPicture.select("td.title_award_outcome").attr("rowspan"));

                    for (int i = 0; i < row.size(); i++) {
                        if (bestPicture.select("td.title_award_outcome").first().text().equals("Winner Oscar") && i < oscarsAmount) {
                            academyAwardList.add(new AcademyAward(imdbId, title, eventYear, row.get(i).ownText(), "yes"));
                        } else {
                            academyAwardList.add(new AcademyAward(imdbId, title, eventYear, row.get(i).ownText(), "no"));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return academyAwardList;

    }

    public static List<BoxOffice> readBoxOfficeFromUrl(Set<String> imdbIds, String url) {

        List<BoxOffice> boxOfficeList = new LinkedList<>();
        String budget = "";
        String gross ="";

        for (String imdbId : imdbIds) {
            try {
                String urlAddress = new StringBuilder(url).append("/").append(imdbId).toString();
                Connection connection = Jsoup.connect(urlAddress);
                Document document = connection.get();
                Elements elementsByClass = document.getElementsByClass("txt-block");
                for(Element e : elementsByClass){
                    if(e.text().matches("Budget:.*")){
                        budget = e.ownText();
                        continue;
                    }
                    if(e.text().matches("Cumulative Worldwide Gross:.*")){
                        gross = e.ownText();
                        break;
                    }
                }
                String[] budgetSplit = budget.split("\\d+");
                String budgetCurrency = budgetSplit[0];
                budget = budget.replace(",","").replace(budgetCurrency,"");

                String[] grossSplit = gross.split("\\d+");
                String grossCurrency = grossSplit[0];
                gross = gross.replace(",","").replace(grossCurrency,"");


                if(budget.isEmpty()){
                    budget = "0";
                    budgetCurrency = null;
                }
                if(gross.isEmpty()){
                    gross = "0";
                    grossCurrency =null;
                }

                boxOfficeList.add(new BoxOffice(imdbId, Long.parseLong(budget),Long.parseLong(gross),budgetCurrency,grossCurrency));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return boxOfficeList;
    }

}