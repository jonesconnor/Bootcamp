
public class PlayerTeamMatcher {
	
	private String playerId;
	private String teamTitle;
	private String year;
	
	public PlayerTeamMatcher(String playerId, String teamTitle, String year) {
		setPlayerId(playerId);
		setTeamTitle(teamTitle);
		setYear(year);
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "PlayerTeamMatcher [playerId=" + playerId + ", teamTitle=" + teamTitle + ", year=" + year + "]";
	}
	
	
}
