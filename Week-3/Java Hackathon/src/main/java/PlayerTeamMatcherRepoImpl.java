import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTeamMatcherRepoImpl {
	
	private List<PlayerTeamMatcher> playerTeamMatcherList;
	private PlayerRepoImpl playerRepo;
	
	public PlayerTeamMatcherRepoImpl() {
		playerTeamMatcherList = new ArrayList<>();
		playerRepo = new PlayerRepoImpl();
	}

	public List<PlayerTeamMatcher> getAllPlayerTeams() {
		return playerTeamMatcherList;
	}
	
	public boolean allotPlayerToTeam(String playerId, String teamTitle, String year) {
		Player player = playerRepo.findPlayer(playerId);
		if (player == null) {
			return false;
		}
		
		long count = playerTeamMatcherList.stream()
				.filter(ptm -> ptm.getTeamTitle().equals(teamTitle))
				.count();
		
		if (count > 3) {
			return false;
		}
		
		playerTeamMatcherList.add(new PlayerTeamMatcher(playerId, teamTitle, year));
		return true;
	}
	
	public List<PlayerTeamMatcher> getPlayersByTeamTitle(String teamTitle) {
		return playerTeamMatcherList.stream()
				.filter(ptm -> ptm.getTeamTitle().equals(teamTitle))
				.collect(Collectors.toList());
	}
}
