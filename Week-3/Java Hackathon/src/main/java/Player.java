import java.util.Comparator;

public class Player implements Comparator<Player>{
	
	private String playerId;
	private String playerName;
	private String password;
	private int yearOfExpr;
	private String teamTitle;
	
	public Player(String playerId, String playerName, String password, int yearOfExpr, String teamTitle) {
		setPlayerId(playerId);
		setPlayerName(playerName);
		setPassword(password);
		setYearOfExpr(yearOfExpr);
		setTeamTitle(teamTitle);
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getYearOfExpr() {
		return yearOfExpr;
	}

	public void setYearOfExpr(int yearOfExpr) {
		this.yearOfExpr = yearOfExpr;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", password=" + password
				+ ", yearOfExpr=" + yearOfExpr + ", teamTitle=" + teamTitle + "]";
	}
	
	public int compare(Player p1, Player p2) {
		return p1.getPlayerId().compareTo(p2.getPlayerId());
	}
}
