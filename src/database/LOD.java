package database;

import java.util.ArrayList;

import org.apache.http.HttpException;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;

import core.Triple;

public class LOD {
	private final String prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
			+ "PREFIX oddlinker: <http://data.linkedmdb.org/resource/oddlinker/>\n"
			+ "PREFIX map: <file:/C:/d2r-server-0.4/mapping.n3#>\n"
			+ "PREFIX db: <http://data.linkedmdb.org/resource/>\n" + "PREFIX dbpedia: <http://dbpedia.org/property/>\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + "PREFIX dc: <http://purl.org/dc/terms/>\n"
			+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\n" + "\n";;

	public static void main(String[] args) {
		LOD lod = new LOD();
		// lod.performQuery("SELECT ?title ?pers WHERE {\n" + "?x a
		// movie:actor;\n" + "movie:actor_name 'Johnny Depp';\n"
		// + "movie:performance ?mov.\n" + "?mov movie:performance_film
		// ?title.\n" + "OPTIONAL{\n"
		// + "?mov movie:performance_character ?pers.\n" + "}\n" + "}\n");

		// lod.performQuery(
		// "SELECT ?acname WHERE {\n?actor movie:performance_actor ?acname.
		// \nFILTER regex(?acname ,\"Orlando Bloom\", \"i\")\n}");

		ArrayList<Triple> LC = new ArrayList<Triple>();
		// LC.add(new Triple("Actor","","Johnny Depp"));
		// LC.add(new Triple("Actor","","Orlando Bloom"));
		
		lod.selectMovies(null, true, true, true, LC);
	}

	public void performQuery(String query) {
		String queryString = prefix + query;
		System.out.println(queryString);
		Query queryObj = QueryFactory.create(queryString);
		QueryExecution qExe = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", queryObj);
		ResultSet results = qExe.execSelect();
		// ResultSetFormatter.out(System.out, results, query) ;
		while (results.hasNext()) {
			QuerySolution s = results.nextSolution();
			RDFNode toto = s.get("acname");
			RDFNode toti = s.get("pers");
			System.out.println(toto);

			// System.out.println(s);
		}
		System.out.println("done");
		qExe.close();
	}

	/**
	 * 
	 * @param movieName
	 *            null to select all movies
	 * @param showDirector
	 * @param showActors
	 * @param showCharacters
	 * @param LC
	 * @return
	 */
	public ArrayList<ArrayList<String>> selectMovies(String movieName, boolean showDirector, boolean showActors,
			boolean showCharacters, ArrayList<Triple> LC) {
		String query = "SELECT ?mname ?date ";
		if (showDirector)
			query += "?dname ";
		if (showActors)
			query += "?aname ";
		if (showCharacters)
			query += "?cname ";
		query += "\nWHERE {\n";

		// Start conditions
		if (movieName != null)
			query += "\n?movie dc:title \"" + movieName + "\".\n";

		int i = 0;
		for (Triple t : LC) {
			switch (t.getAttribute()) {
			case "Director":
				query += "\n?director" + i + " movie:director_name \"" + t.getValue() + "\".\n"
						+ "?movie movie:director ?director" + i + " .\n";
				i++;
				break;
			case "Actor":
				query += "\n?actor" + i + " movie:actor_name \"" + t.getValue() + "\".\n" + "?movie movie:actor ?actor"
						+ i + " .\n";
				i++;
				break;
			case "Character":
				query += "\n?character" + i + " movie:performance_character \"" + t.getValue() + "\".\n"
						+ "?movie movie:performance ?character" + i + " .\n";
				i++;
				break;
			default:
				break;
			}
		}

		// end conditions

		query += "\n?movie dc:title ?mname.\n";
		if (showActors || showCharacters)
			query += "?movie movie:performance ?perf.\n";
		if (showDirector)
			query += "?movie movie:director ?dir.\n";

		query += "?movie movie:initial_release_date ?date .\n"
				+ "FILTER ((?date >= \"1995-01-01\" && ?date < \"2015-01-01\") || ?date = \"\")\n";

		if (showDirector)
			query += "\n?dir movie:director_name ?dname.\n";
		if (showActors)
			query += "?perf movie:performance_actor ?aname.\n";
		if (showCharacters)
			query += "?perf movie:performance_character ?cname.\n";

		query += "}ORDER BY ?mname\n";

		String queryString = prefix + query;

		System.out.println(query);
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		try {
		Query queryObj = QueryFactory.create(queryString);
		QueryExecution exe ; 
		QueryExecution qExe = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", queryObj);
		ResultSet results = qExe.execSelect();

		while (results.hasNext()) {
			ArrayList<String> row = new ArrayList<String>();

			QuerySolution s = results.nextSolution();
			RDFNode movie = s.get("mname");
			row.add(movie.toString());
			RDFNode date = s.get("date");
			row.add(date.toString());

			if (showDirector) {
				RDFNode director = s.get("dname");
				row.add(director.toString());
			}

			if (showActors) {
				RDFNode actors = s.get("aname");
				row.add(actors.toString());
			}

			if (showCharacters) {
				RDFNode characters = s.get("cname");
				row.add(characters.toString());
			}

			result.add(row);
		}
		qExe.close();
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		return result;
	}
}
