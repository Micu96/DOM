package com.akond.RankFilm.DB.DOM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class Builder {

    private static String url = "https://www.imdb.com/title";


    public static void buildOscarsTable(Connection connection) throws SQLException {
        System.out.println("createTableOscars");
        SqlUtils.createTableOscars(connection);
        while(true) {
            System.out.println("readImdbIdsFromMovies");
            Set<String> imdbIds = MovieRepo.readImdbIdsFromOscars(connection);
            System.out.println("readAwardsFromUrl");
            List<AcademyAward> academyAwards = Reader.readAwardsFromUrl(imdbIds, url);
            if(academyAwards.size()==0){
                break;
            }
            System.out.println("insertIntoOscarsTable");
            SqlUtils.insertIntoOscarsTable(connection, academyAwards);
            connection.commit();
        }
    }
    public static void buildBoxOfficeTable(Connection connection) throws SQLException {

        System.out.println("createTableBoxOffice");
        SqlUtils.createTableBoxOffice(connection);
        while(true) {
            System.out.println("readImdbIdsFromMovies");
            Set<String> imdbIds = MovieRepo.readImdbIdsFromBoxOffice(connection);
            if(imdbIds.size()==0){
                break;
            }
            System.out.println("readBoxOfficeFromUrl");
            List<BoxOffice> boxOffices = Reader.readBoxOfficeFromUrl(imdbIds, url);
            System.out.println("insertIntoBoxOfficeTable");
            SqlUtils.insertIntoBoxOfficeTable(boxOffices, connection);
            connection.commit();
        }

    }




}
