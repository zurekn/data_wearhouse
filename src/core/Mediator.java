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

	public void decodeTriple(ArrayList<Triple> triples, ArrayList<String> display) {
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
				System.err.println(t.getAttribute()+" not found in decodeTriple");
				break;
			}
		}
		
		String sql = QueryConstructor.createOracleQuery(oracle, display);
		//TODO create query for LOD
		
		ResultSet SQLresult = executeQuery(sql);
		try {
			for(String s : display)
				System.out.println(s+"\t\t");
			while(SQLresult.next()){
				for(String s : display){
					System.out.println(SQLresult.getString(s));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ResultSet executeQuery(String sql) {
		return oh.executeQuery(sql);
	}

}
