package com.akond.RankFilm.DB.DOM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class DomApplication {

	private static Connection connection;

	public static void main(String[] args) {
		//SpringApplication.run(DomApplication.class, args);


		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "Kolega66.");
			connection.setAutoCommit(false);
			Builder.buildOscarsTable(connection);
			Builder.buildBoxOfficeTable(connection);

		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}















	}
}
