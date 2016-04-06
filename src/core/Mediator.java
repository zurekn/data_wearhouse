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
		ArrayList<Triple> lod = new ArrayList<Triple>();

		for (Triple t : triples) {
			switch (t.getAttribute()) {
			case "Movie":
				lod.add(t);
				oracle.add(t);
				break;
			case "Realisator":
			case "Actor":
			case "Caractere":
				lod.add(t);
				break;
			case "Date":
			case "Genre":
			case "Distributor":
			case "Budget":
			case "ChiffreUS":
			case "chiffreMondial":
				oracle.add(t);
				break;
			default:
				System.err.println(t.getAttribute() + " not found in decodeTriple");
				break;
			}
		}

		String sql = QueryConstructor.createOracleQuery(oracle, display);
		// TODO create query for LOD

		ResultSet SQLresult = executeQuery(sql);
		ArrayList<ArrayList<String>> resultSQL = new ArrayList<>();
		try {
			
			for (int i = 0; i < display.size(); i++) {
				String s = display.get(i);

				//System.out.print(s + "\t\t");
				resultSQL.add(new ArrayList<String>());
			}
			//System.out.println();
			while (SQLresult.next()) {
				for (int i = 0; i < display.size(); i++) {
					String s = display.get(i);
					resultSQL.get(i).add(SQLresult.getString(s));
				}
			}
		} catch (SQLException e) {
			System.err.println("Catch a sql exeption in mediator [" + e.getMessage() + "]");
		}catch (NullPointerException e){
			System.err.println("Catch a exeption in the mediator ["+e.getMessage()+"]");
		}
		String[][] result = null;
		if (!resultSQL.isEmpty()) {
			result = new String[resultSQL.get(0).size()][display.size()];

			for (int i = 0; i < display.size(); i++)
				for (int j = 0; j < resultSQL.get(0).size(); j++) {
					result[j][i] = resultSQL.get(i).get(j);
					//System.out.println(resultSQL.get(i).get(j));
				}
			//System.out.println();
		}
		
		return result;
	}

	private ResultSet executeQuery(String sql) {
		return oh.executeQuery(sql);
	}

}
