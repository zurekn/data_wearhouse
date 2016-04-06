package core;

import java.util.ArrayList;

public class QueryConstructor {

	public static String createOracleQuery(ArrayList<Triple> oracle, ArrayList<String> display) {
		String query = "";
		String select = "";
		String from = "";
		String where = "";
		String sort = "";
		ArrayList<String> fromList = new ArrayList<>();
		for (Triple t : oracle) {
			System.out.println("Check for "+t.toString());
			// WHERE
			if (t.getAttribute().isEmpty())
				continue;
			
			if(!fromList.contains(t.getAttribute()))
				fromList.add(t.getAttribute());// For the from list
			

			
			if (!t.getOperator().isEmpty() && !t.getValue().isEmpty())
				if (where.isEmpty()) {
					where += "WHERE " + t.getAttribute() + " " + t.getOperator() + " " + t.getValue();
				} else {
					where += "," + t.getAttribute() + " " + t.getOperator() + " " + t.getValue();
				}
		}

		//SELECT
		for(String s : display){
			if(!fromList.contains(s))
				fromList.add(s);
			switch(s){
			case "Genre":
				s = "g."+s;
				break;
			case "Studio":
				s = "s."+s;
				break;
			default :
				s = "m."+s;
				break;
			}
			if (select.isEmpty()) {
				select += "Select DISTINCT " + s;
			} else {
				select += ", " + s;
			}
		}
		
		// FROM
		where = createFrom(fromList);

		query = select + " " + from + " " + where + " " + sort;
		return query;
	}

	private static String createFrom(ArrayList<String> fromList) {
		String from = " FROM MOVIEBUDGET m";
		ArrayList<String> list = new ArrayList<>();
		for (String s : fromList) {
			switch (s) {
			case "Genre":
				from += " LEFT JOIN GENRE g on m.movie = g.movie";
				break;
			case "Studio":
				from += " LEFT JOIN STUDIO s on m.movie = s.movie";
			}
		}
		return from;
	}

}
