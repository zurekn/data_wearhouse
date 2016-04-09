package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import database.LOD;
import database.OracleConnexion;
import database.OracleHandler;

public class Mediator {
	private OracleHandler oh;
	private LOD lod;

	public Mediator() {
		oh = new OracleHandler();
		lod = new LOD();
	}

	public String[][] decodeTriple(ArrayList<Triple> triples, ArrayList<String> display) {

		return secondScenario(triples, display);
	}

	private String[][] firstScenario() {
		String[][] result = null;

		return result;
	}

	private String[][] secondScenario(ArrayList<Triple> triples, ArrayList<String> display) {
		ArrayList<Triple> oracle = new ArrayList<Triple>();
		ArrayList<String> oracleDisplay = new ArrayList<>();
		ArrayList<Triple> lodTriples = new ArrayList<Triple>();
		
		int movieDisplay = 1;
		
		for (Triple t : triples) {
			switch (t.getAttribute()) {
			case "Movie":	
				lodTriples.add(t);
				oracle.add(t);
				break;
			case "Director":
			case "Actor":
			case "Charactere":
				lodTriples.add(t);
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
		boolean director = false, character = false, actor = false;
		String movie = null;
		for (String s : display) {
			switch (s) {
			case "Director":
				director = true;
				break;
			case "Character":
				character = true;
				break;
			case "Actor":
				actor = true;
				break;
			case "Movie":
				movieDisplay = 0;
				movie = s;
				oracleDisplay.add(s);
				break;
			default:
				oracleDisplay.add(s);
			}
		}

		if(!display.contains("Movie"))
			oracleDisplay.add(0, "Movie");
		
		System.out.println("SQL triple : " + oracle.toString());
		System.out.println("LOD triple : " + lodTriples.toString());
		String sql = QueryConstructor.createOracleQuery(oracle, oracleDisplay);

		ArrayList<ArrayList<String>> lodResult = lod.selectMovies(movie, director, actor, character, lodTriples);
		System.out.println("lod found "+lodResult.size()+" elements");
		// ============SQL process=============
		ResultSet SQLresult = executeQuery(sql);
		ArrayList<ArrayList<String>> resultSQL = new ArrayList<>();
		try {
			while (SQLresult.next()) {
				ArrayList<String> line = new ArrayList<String>();
				for (int i = 0; i < oracleDisplay.size(); i++) {
					String s = oracleDisplay.get(i);
					line.add(SQLresult.getString(s));
				}
				resultSQL.add(line);
			}
		} catch (SQLException e) {
			System.err.println("Catch a sql exeption in mediator [" + e.getMessage() + "]");
		} catch (NullPointerException e) {
			System.err.println("Catch a exeption in the mediator [" + e.getMessage() + "]");
		}

		// JOIN SQL LIST AND LOD LIST

		String[][] result = null;
		if (!resultSQL.isEmpty()) {
			result = new String[resultSQL.size()][display.size()];
			for (int i = 0; i < resultSQL.size(); i++) {
				String name = resultSQL.get(i).get(0);
				for (int j = 0 + movieDisplay; j < resultSQL.get(0).size(); j++) {
					result[i][j - movieDisplay] = resultSQL.get(i).get(j);
					// System.out.println(resultSQL.get(i).get(j));
				}
				Iterator<ArrayList<String>> it = lodResult.iterator();
				while (it.hasNext()) {
					ArrayList<String> line = it.next();
					if (!line.isEmpty()) {
						String m = line.get(0);
						System.out.println(m);
						if (m.equals(name)){
							for (int j = 1; j < line.size(); j++) {
								result[i][oracleDisplay.size() - movieDisplay + j - 1] = line.get(j);
							}
						it.remove();
						}
					}
				}
			}
			// System.out.println();
		}

		// ============END OF SQL process=============

		return result;
	}

	private ResultSet executeQuery(String sql) {
		return oh.executeQuery(sql);
	}

}
