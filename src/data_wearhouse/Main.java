package data_wearhouse;

import java.util.ArrayList;

import core.Mediator;
import core.Triple;
import database.OracleConnexion;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello is the data wearhouse project!");
		Mediator media = new Mediator();
		
		Triple t = new Triple("Movie", "", "");
		ArrayList<Triple> ts = new ArrayList<>();
		ts.add(t);
		
		ArrayList<String> display = new ArrayList<String>();
		display.add("Movie");
		media.decodeTriple(ts, display);
	}

}
