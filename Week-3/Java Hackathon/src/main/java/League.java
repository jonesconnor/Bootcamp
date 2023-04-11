
public class League {
	
	private String teamTitle;
	private int year;
	private String season;
	
	public League(String teamTitle, int year, String season) {
		setTeamTitle(teamTitle);
		setYear(year);
		setSeason(season);
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "League [teamTitle=" + teamTitle + ", year=" + year + ", season=" + season + "]";
	}
	
}
