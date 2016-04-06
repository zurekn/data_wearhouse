package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.OracleConnexion;
import database.OracleHandler;

public class Mediator {
	private OracleHandler oh;

	public Mediator() {
		oh = new OracleHandler();
	}

	public String[][] decodeTriple(ArrayList<Triple> triples, ArrayList<String> display) {
		ArrayList<Triple> oracle = new ArrayList<Triple>();
		ArrayList<String> oracleDisplay = new ArrayList<>();
		ArrayList<Triple> lod = new ArrayList<Triple>();

		for (Triple t : triples) {
			switch (t.getAttribute()) {
			case "Movie":
				lod.add(t);
				oracle.add(t);
				break;
			case "Director":
			case "Actor":
			case "Charactere":
				lod.add(t);
				break;
			case "Release_date":
			case "Genre":
			case "Production_budget":
			case "Domestic_gross":
			case "Worldwide_gross":
			case "Studio":
				oracle.add(t);
				break;
			default:
				System.err.println(t.getAttribute() + " not found in decodeTriple");
				break;
			}
		}
		
		for(String s : display){
			switch(s){
			case "Director":
				break;
			case "Character":
				break;
			case "Actor":
				break;
			case "Movie":
				oracleDisplay.add(s);
				break;
				default:
					oracleDisplay.add(s);
			}
		}

		System.out.println("SQL triple : "+oracle.toString());
		System.out.println("LOD triple : "+lod.toString());
		String sql = QueryConstructor.createOracleQuery(oracle, oracleDisplay);
		// TODO create query for LOD
		
		//============SQL process=============
		ResultSet SQLresult = executeQuery(sql);
		ArrayList<ArrayList<String>> resultSQL = new ArrayList<>();
		try {
			
			for (int i = 0; i < oracleDisplay.size(); i++) {
				String s = oracleDisplay.get(i);

				//System.out.print(s + "\t\t");
				resultSQL.add(new ArrayList<String>());
			}
			//System.out.println();
			while (SQLresult.next()) {
				for (int i = 0; i < oracleDisplay.size(); i++) {
					String s = oracleDisplay.get(i);
					resultSQL.get(i).add(SQLresult.getString(s));
				}
			}
		} catch (SQLException e) {
			System.err.println("Catch a sql exeption in mediator [" + e.getMessage() + "]");
		}catch (NullPointerException e){
			System.err.println("Catch a exeption in the mediator ["+e.getMessage()+"]");
		}
		String[][] result = null;
		int nbRow = 0;
		if (!resultSQL.isEmpty()) {
			result = new String[resultSQL.get(0).size()][display.size()];
			nbRow = resultSQL.get(0).size();
			for (int i = 0; i < oracleDisplay.size(); i++)
				for (int j = 0; j < resultSQL.get(0).size(); j++) {
					result[j][i] = resultSQL.get(i).get(j);
					//System.out.println(resultSQL.get(i).get(j));
				}
			//System.out.println();
		}
		
		//============END OF SQL process=============
		for(int i = oracleDisplay.size(); i < display.size(); i++){
			for(int j = 0; j < nbRow; j++){
				result[j][i] = "";
			}
		}
		return result;
	}

	private ResultSet executeQuery(String sql) {
		return oh.executeQuery(sql);
	}

}
