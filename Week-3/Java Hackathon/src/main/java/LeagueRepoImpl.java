import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeagueRepoImpl {
	
	private static List<League> leagueTeams;
	
	public LeagueRepoImpl() {
		leagueTeams = new ArrayList<>();
		leagueTeams = fetchLeagueTeams("resources_league.csv");
	}
	
	public List<League> fetchLeagueTeams(String fileName) {
		List<League> teams = new ArrayList<>();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String item;
			while ((item = br.readLine()) != null) {
				String[] strLeague = item.split(",");
				teams.add(new League(strLeague[0], Integer.parseInt(strLeague[1]), strLeague[2]));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<League>();
		}
		
		return teams;
	}
	
	public League findByLeagueTeamTitle(String teamTitle) {
		return leagueTeams.stream()
				.filter(t -> t.getTeamTitle().equals(teamTitle))
				.findFirst()
				.orElse(null);
	}
	
	public List<League> getAllLeagueTeams() {
		return leagueTeams; 
	}
}
