package database;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;


public class LOD {
	private static final String prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
			"PREFIX oddlinker: <http://data.linkedmdb.org/resource/oddlinker/>\n" +
			"PREFIX map: <file:/C:/d2r-server-0.4/mapping.n3#>\n" +
			"PREFIX db: <http://data.linkedmdb.org/resource/>\n" +
			"PREFIX dbpedia: <http://dbpedia.org/property/>\n" +
			"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
			"PREFIX dc: <http://purl.org/dc/terms/>\n" +
			"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\n" +
			"\n";;
	
	public static void main(String[] args) {
		LOD lod = new LOD();
		lod.performQuery("SELECT ?title ?pers WHERE {\n" +
				"?x a movie:actor;\n" +
				"movie:actor_name 'Johnny Depp';\n" +
				"movie:performance ?mov.\n" +
				"?mov movie:performance_film ?title.\n" +
				"OPTIONAL{\n" +
					"?mov movie:performance_character ?pers.\n" +
				"}\n" +
			  "}\n");
	}
	
	public void performQuery(String query){
				String queryString = prefix + query;
				System.out.println(queryString);
				Query queryObj = QueryFactory.create(queryString); 
				QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://data.linkedmdb.org/sparql", queryObj );
				ResultSet results = qExe.execSelect();
		        //ResultSetFormatter.out(System.out, results, query) ;
				while ( results.hasNext() ) {
					QuerySolution s = results.nextSolution(); 
					RDFNode toto = s.get("title");
					RDFNode toti = s.get("pers");
					System.out.println(toto + " || " + toti);
					
					//System.out.println(s);
				} qExe.close();
					
			
	}
}
