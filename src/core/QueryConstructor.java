package core;

import java.util.ArrayList;

import database.OracleHandler;

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
			

			
			if (!t.getOperator().isEmpty() && !t.getValue().isEmpty()){
				String value; 
				try{
				 value = ""+Integer.parseInt(t.getValue());
				}catch(NumberFormatException e){
					value = "'"+t.getValue()+"'";
				}
				
				if (where.isEmpty()) {
					where += "WHERE " + OracleHandler.getRealColName(t.getAttribute()) + " " + t.getOperator() + " " + value;
				} else {
					where += " AND " + OracleHandler.getRealColName(t.getAttribute()) + " " + t.getOperator() + " " + value;
				}
			}else{
				System.out.println(" No case about "+t.toString());
			}
		}

		//SELECT
		for(String s : display){
			if(!fromList.contains(s))
				fromList.add(s);
			s = OracleHandler.getRealColName(s);
			if (select.isEmpty()) {
				select += "Select DISTINCT " + s;
			} else {
				select += ", " + s;
			}
		}
		
		// FROM
		from = createFrom(fromList);

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
