import java.util.ArrayList;
import java.util.List;

public class PlayerRepoImpl {
	
	private List<Player> playerList;
	
	public PlayerRepoImpl() {
		playerList = new ArrayList<>();
	}
	
	public List<Player> addPlayer(Player p) {
		playerList.add(p);
		return playerList;
	}
	
	public Player findPlayer(String playerId) {
		return playerList.stream()
				.filter(p -> p.getPlayerId().equals(playerId))
				.findFirst()
				.orElse(null);
	}
	
	public List<Player> getAllPlayers() {
		return playerList;
	}
}
