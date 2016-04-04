package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class OracleHandler {

	public static ArrayList<String> tableGenre = new ArrayList<>();
	public static ArrayList<String> tableStudio = new ArrayList<>();
	public static ArrayList<String> tableMovieBudget = new ArrayList<>();

	private OracleConnexion oracleCon;
	
	public OracleHandler(){
		oracleCon = new OracleConnexion();
		init();
	}
	
	private void init(){
		tableGenre.addAll(Arrays.asList("Movie", "Release_date", "Distributor", "Rating", "Total_gross", "Inflation_gross", "Genre"));
		tableStudio.addAll(Arrays.asList("Movie", "Release_date", "Genre", "Rating", "Total_gross", "Inflation_gross", "Studio"));
		tableMovieBudget.addAll(Arrays.asList("Release_date", "Movie", "Production_budget"," Domestic_gross", "Worldwide_gross"));
	}
	
	public ResultSet executeQuery(String query){
		System.out.println("Execute SQL query : "+query);
		ResultSet result = null;
		try {
			Statement stat = oracleCon.getConnection().createStatement();
			result = stat.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
