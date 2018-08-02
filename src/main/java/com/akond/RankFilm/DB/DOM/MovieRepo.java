package com.akond.RankFilm.DB.DOM;

import java.io.Serializable;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MovieRepo {


    public static Set<String> readImdbIdsFromBoxOffice(Connection connection) throws SQLException {

        Set<String> imdbids = new HashSet<>();
        Statement statement = connection.createStatement();
        String sqlSelect = "SELECT * FROM `database`.`Movies` m LEFT JOIN `database`.`BoxOffice` b ON (b.imdb_id = m.imdb_id) WHERE b.imdb_id IS NULL LIMIT 1;";

        ResultSet resultSet = statement.executeQuery(sqlSelect);

        while(resultSet.next()){
            String imdbId = resultSet.getString(1);
            imdbids.add(imdbId);
        }
        return imdbids;
    }
    public static Set<String> readImdbIdsFromOscars(Connection connection) throws SQLException {
        Set<String> imdbIds = new HashSet<>();
        Statement statement = connection.createStatement();

        String sqlSelect = "SELECT * FROM `database`.`Movies` m LEFT JOIN `database`.`Oscars` o ON (o.imdb_id = m.imdb_id) WHERE o.imdb_id IS NULL LIMIT 1;";

        ResultSet resultSet = statement.executeQuery(sqlSelect);
        while(resultSet.next()){
            String imdbId = resultSet.getString(1);
            imdbIds.add(imdbId);
        }
        return imdbIds;
    }

}
