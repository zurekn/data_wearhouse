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

	private String[][] firstScenario(ArrayList<Triple> triples, ArrayList<String> display) {
		long timeBegin = System.currentTimeMillis();
		ArrayList<Triple> oracle = new ArrayList<Triple>();
		ArrayList<String> oracleDisplay = new ArrayList<>();
		ArrayList<Triple> lodTriples = new ArrayList<Triple>();
		
		boolean movieDisplay = false;
		String movie = null;
		boolean lodRequest = false;
		for (Triple t : triples) {
			switch (t.getAttribute()) {
			case "Movie":	
				lodTriples.add(t);
				oracle.add(t);
				movie = t.getValue();
				break;
			case "Director":
			case "Actor":
			case "Charactere":
				lodRequest = true;
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
				movieDisplay = true;
				oracleDisplay.add(s);
				break;
			default:
				oracleDisplay.add(s);
			}
		}
		lodRequest |= director || character || actor;
		
		if(!display.contains("Movie"))
			oracleDisplay.add(0, "Movie");
		
		System.out.println("SQL triple : " + oracle.toString());
		System.out.println("LOD triple : " + lodTriples.toString());
		
		long timeLOD = System.currentTimeMillis();
		ArrayList<ArrayList<String>> lodResult = lodRequest ? lod.selectMovies(movie, director, actor, character, lodTriples) : new ArrayList<ArrayList<String>>();
		System.out.println("lod found "+lodResult.size()+" elements in "+(System.currentTimeMillis() - timeLOD)+" ms");
		
		// ============SQL process=============
		long timeSQL = System.currentTimeMillis();
		String query = QueryConstructor.createOracleQuery(oracle, oracleDisplay);
		ResultSet SQLresult = executeQuery(query);
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
		System.out.println("SQL found "+resultSQL.size()+" elements in "+(System.currentTimeMillis() - timeSQL)+"ms");

		// JOIN SQL LIST AND LOD LIST
		long timeJoin = System.currentTimeMillis();
		ArrayList<ArrayList<String>> join = new ArrayList<>();
		for(ArrayList<String> sqlLine : resultSQL){
			String film = sqlLine.get(0);
			if(!movieDisplay)
				sqlLine.remove(0);
			boolean add = false;
			Iterator<ArrayList<String>> it = lodResult.iterator();
			while(it.hasNext()){
				ArrayList<String> lodLine = it.next();
				
				if(lodLine.get(0).equals(film)){
					//Same film;
					lodLine.remove(0);
					ArrayList<String> line = new ArrayList<>();
					line.addAll(sqlLine);
					line.addAll(lodLine);
					join.add(line);
					add = true;
					it.remove();
				}
			}
			if(!add){
				ArrayList<String> line = new ArrayList<>();
				line.addAll(sqlLine);
				for(int i = 1; i < display.size() - oracleDisplay.size(); i++)
					line.add("");
				join.add(line);
			}
		}

		System.out.println("Join size = "+join.size()+", in "+(System.currentTimeMillis() - timeJoin)+" ms");
		String[][] result = new String[join.size()][display.size()];
		for(int i = 0; i < join.size(); i++){
			for(int j = 0; j < join.get(i).size(); j++){
				result[i][j] = join.get(i).get(j);
			}
		}

		// ============END OF SQL process=============
		System.out.println("Second execution plan took "+(System.currentTimeMillis() - timeBegin)+"ms");
		return result;
	}

	private String[][] secondScenario(ArrayList<Triple> triples, ArrayList<String> display) {
		long timeBegin = System.currentTimeMillis();
		ArrayList<Triple> oracle = new ArrayList<Triple>();
		ArrayList<String> oracleDisplay = new ArrayList<>();
		ArrayList<Triple> lodTriples = new ArrayList<Triple>();
		
		boolean movieDisplay = false;
		String movie = null;
		boolean lodRequest = false;
		for (Triple t : triples) {
			switch (t.getAttribute()) {
			case "Movie":	
				lodTriples.add(t);
				oracle.add(t);
				movie = t.getValue();
				break;
			case "Director":
			case "Actor":
			case "Charactere":
				lodRequest = true;
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
				movieDisplay = true;
				oracleDisplay.add(s);
				break;
			default:
				oracleDisplay.add(s);
			}
		}
		lodRequest |= director || character || actor;
		
		if(!display.contains("Movie"))
			oracleDisplay.add(0, "Movie");
		
		System.out.println("SQL triple : " + oracle.toString());
		System.out.println("LOD triple : " + lodTriples.toString());
		
		long timeLOD = System.currentTimeMillis();
		ArrayList<ArrayList<String>> lodResult = lodRequest ? lod.selectMovies(movie, director, actor, character, lodTriples) : new ArrayList<ArrayList<String>>();
		System.out.println("lod found "+lodResult.size()+" elements in "+(System.currentTimeMillis() - timeLOD)+" ms");
		
		// ============SQL process=============
		long timeSQL = System.currentTimeMillis();
		String query = QueryConstructor.createOracleQuery(oracle, oracleDisplay);
		ResultSet SQLresult = executeQuery(query);
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
		System.out.println("SQL found "+resultSQL.size()+" elements in "+(System.currentTimeMillis() - timeSQL)+"ms");

		// JOIN SQL LIST AND LOD LIST
		long timeJoin = System.currentTimeMillis();
		ArrayList<ArrayList<String>> join = new ArrayList<>();
		for(ArrayList<String> sqlLine : resultSQL){
			String film = sqlLine.get(0);
			if(!movieDisplay)
				sqlLine.remove(0);
			boolean add = false;
			Iterator<ArrayList<String>> it = lodResult.iterator();
			while(it.hasNext()){
				ArrayList<String> lodLine = it.next();
				
				if(lodLine.get(0).equals(film)){
					//Same film;
					lodLine.remove(0);
					ArrayList<String> line = new ArrayList<>();
					line.addAll(sqlLine);
					line.addAll(lodLine);
					join.add(line);
					add = true;
					it.remove();
				}
			}
			if(!add){
				ArrayList<String> line = new ArrayList<>();
				line.addAll(sqlLine);
				for(int i = 1; i < display.size() - oracleDisplay.size(); i++)
					line.add("");
				join.add(line);
			}
		}

		System.out.println("Join size = "+join.size()+", in "+(System.currentTimeMillis() - timeJoin)+" ms");
		String[][] result = new String[join.size()][display.size()];
		for(int i = 0; i < join.size(); i++){
			for(int j = 0; j < join.get(i).size(); j++){
				result[i][j] = join.get(i).get(j);
			}
		}

		// ============END OF SQL process=============
		System.out.println("Second execution plan took "+(System.currentTimeMillis() - timeBegin)+"ms");
		return result;
	}

	private ResultSet executeQuery(String sql) {
		return oh.executeQuery(sql);
	}

}
