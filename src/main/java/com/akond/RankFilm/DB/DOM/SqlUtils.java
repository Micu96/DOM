package com.akond.RankFilm.DB.DOM;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class SqlUtils {

    public static void createTableOscars(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        String sqlCreate = "CREATE TABLE IF NOT EXISTS `database`.`Oscars` " +
                "(`imdb_id` VARCHAR(120) NOT NULL," +
                "`title` VARCHAR(255) NOT NULL," +
                "`w_year`VARCHAR(120) NOT NULL, "+
                "`nomination` VARCHAR (120) NOT NULL, "+
                "`win` VARCHAR (120) NOT NULL);";

        statement.executeUpdate(sqlCreate);

    }
    public static void insertIntoOscarsTable(Connection connection, List<AcademyAward> academyAwardList) throws SQLException {

        String sqlInsert = "INSERT INTO `database`.`Oscars` (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);

        Iterator<AcademyAward> iterator = academyAwardList.iterator();
        while(iterator.hasNext()){
            AcademyAward academyAward = iterator.next();

            preparedStatement.setString(1,academyAward.getImdb_id());
            preparedStatement.setString(2,academyAward.getTitle());
            preparedStatement.setString(3,academyAward.getWinning_year());
            preparedStatement.setString(4,academyAward.getNomination());
            preparedStatement.setString(5,academyAward.getWin());

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("Executed!");

    }
    public static void createTableBoxOffice(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        String sqlCreate = "CREATE TABLE IF NOT EXISTS `database`.`BoxOffice` (" +
                "`imdb_id` VARCHAR(120) NOT NULL," +
                "`budget` BIGINT (120) NULL," +
                "`gross` BIGINT (120) NULL,"+
                "`budget_crnc` VARCHAR(120) NULL,"+
                "`gross_crnc` VARCHAR(120) NULL,"+
                "PRIMARY KEY(`imdb_id`));";

        statement.executeUpdate(sqlCreate);

    }

    public static void insertIntoBoxOfficeTable(List<BoxOffice> boxOfficeList, Connection connection) throws SQLException {

        String sqlInsert = "INSERT INTO database.BoxOffice VALUES (?,?,?,?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);

            Iterator<BoxOffice> iterator = boxOfficeList.iterator();

            while (iterator.hasNext()) {

                try {
                    BoxOffice boxOffice = iterator.next();

                        preparedStatement.setString(1, boxOffice.getImdbId());
                        preparedStatement.setObject(2, boxOffice.getBudget());
                        preparedStatement.setObject(3, boxOffice.getGross());
                        preparedStatement.setString(4,boxOffice.getBudgetCurrency());
                        preparedStatement.setString(5,boxOffice.getGrossCurrency());

                        preparedStatement.addBatch();


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        preparedStatement.executeBatch();

    }



}
