package com.calendarfootball.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.calendarfootball.model.CalendarFootball;
import com.calendarfootball.model.Round;
import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		List<String> teams = Arrays.asList("Barcelona", "Real Madrid", "Boca", "River", "BVB", "Millonarios");
		List<CalendarFootball> calendar = new ArrayList<CalendarFootball>();
		int rnd = 1;
		List<Round> lsRound = new ArrayList<Round>();
		for (int k = 0; k < teams.size(); k++) {
			for (int j = 0; j < teams.size(); j++) {

				if (!teams.get(k).equals(teams.get(j))) {
					CalendarFootball match = new CalendarFootball();
					String local = teams.get(k);
					String visitor = teams.get(j);

					if (!calendar.isEmpty()) {
						Boolean aux = false;
						Boolean auxLoca = false;
						for (CalendarFootball ca : calendar) {
							if (ca.getLocal().equals(visitor) && ca.getVisitor().equals(local)
									|| ca.getLocal().equals(local) && ca.getVisitor().equals(visitor)) {
								aux = true;
							}
							if (ca.getLocal().equals(local)) {
								auxLoca = true;
							}
						}
						if (!aux) {
							if (auxLoca) {
								match.setLocal(visitor);
								match.setVisitor(local);
							} else {
								match.setLocal(local);
								match.setVisitor(visitor);
							}
						}
					} else {
						match.setLocal(local);
						match.setVisitor(visitor);
					}

					if (match.visitor != null && match.local != null) {
						calendar.add(match);
						
						
						Round r = new Round();
						r.setRound(rnd);
						r.setCalendarFootball(match);
						lsRound.add(r);
						
						if (rnd == 2) {
							rnd = 1;
						} else {
							rnd = 2;
						}

					}
				}
			}
		}
		

		String json = new Gson().toJson(lsRound);
		System.out.println(json);
	}
}

