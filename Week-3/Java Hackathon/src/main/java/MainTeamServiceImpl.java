import java.util.List;
import java.util.Scanner;

public class MainTeamServiceImpl {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		PlayerRepoImpl playerRepo = new PlayerRepoImpl();
		LeagueRepoImpl leagueRepo = new LeagueRepoImpl();
		PlayerTeamMatcherRepoImpl matcherRepo = new PlayerTeamMatcherRepoImpl();
		
		List<Player> players;
		List<League> leagues;
		
		String choice;
		
		
		// implement a do while loop so user can repeat
		outerloop:
		do {
			choice = displayMenu(scan);
			
			switch(choice) {
			case "a":
				players = registerNewPlayer(playerRepo, matcherRepo, scan);
				System.out.println("New player confirmed: " + players.get(0).toString());
				List<Player> fullPlayerList = playerRepo.getAllPlayers();
				System.out.println("All registered players: ");
				for (Player p : fullPlayerList) {
					System.out.println(p.toString());
				}
				continue outerloop;
			case "b":
				leagues = getAllLeagues(leagueRepo);
				System.out.println("Here are available leagues: \n");
				for (League L : leagues) {
					System.out.println(L.toString());
				}
				System.out.println("Enter a team title to bring it up individually: ");
				String title = scan.nextLine();
				League searchLeague = fetchByTeam(leagueRepo, title);
				System.out.println(searchLeague.toString());
				continue outerloop;
			case "c":
				System.out.println("Here is a list of all registered players: ");
				for (Player p : playerRepo.getAllPlayers()) {
					System.out.println(p.toString());
				}
				System.out.println("Please enter a player ID: ");
				String id = scan.nextLine();
				Player p = playerRepo.findPlayer(id);
				if (p == null) {
					System.out.println("Player not found");
					continue outerloop;
				}
				System.out.println("Attempting to allot Player " + p.getPlayerName() + " to team " + p.getTeamTitle());
				boolean allotted = allotPlayerToLeagueTeam(matcherRepo, p.getPlayerId(), p.getTeamTitle(), Integer.toString(p.getYearOfExpr()));
				if (!allotted) {
					System.out.println("Too many players on team already");
					System.out.println("Displaying final team");
					for (PlayerTeamMatcher finalPlayer : fetchFinalTeam(matcherRepo, p.getTeamTitle())) {
						System.out.println(finalPlayer.toString());
					}
					continue outerloop;
				}
				System.out.println("Successfully allotted");
				continue outerloop;
			default:
				System.out.println("Invalid choice, terminating program");
			}
			
			
		} while (choice.equals("a") || choice.equals("b") || choice.equals("c"));

	}
	
	public static String displayMenu(Scanner sc) {
		System.out.println("***********************************************************************");
		System.out.println("a) Player Process\nb) League Process\nc) Transaction");
		System.out.println("***********************************************************************");
		System.out.println("Select an option from the menu (a/b/c): ");
		String choice = sc.nextLine();
		return choice;
	}
	
	public static List<Player> registerNewPlayer(PlayerRepoImpl playerRepo, PlayerTeamMatcherRepoImpl matcherRepo, Scanner scan) {
		System.out.println("Enter player ID: ");
		String playerId = scan.nextLine();
		
		System.out.println("Enter player name: ");
		String playerName = scan.nextLine();
		
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		// validate user input here
		System.out.println("Enter years of experience: ");
		int yearOfExpr = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter team title: ");
		String teamTitle = scan.nextLine();
		if (matcherRepo.getPlayersByTeamTitle(teamTitle).size() >= 3) {
			System.out.println("Team already full -- allocation will fail. Please enter a different team: ");
			teamTitle = scan.nextLine();
		}
		
		List<Player> player = playerRepo.addPlayer(new Player(playerId, playerName, password, yearOfExpr, teamTitle));
		
		return player;
	}
	
	public static List<League> getAllLeagues(LeagueRepoImpl leagueRepo) {
		return leagueRepo.getAllLeagueTeams();
	}
	
	public static League fetchByTeam(LeagueRepoImpl leagueRepo, String teamTitle) {
		return leagueRepo.findByLeagueTeamTitle(teamTitle);
	}
	
	public static boolean allotPlayerToLeagueTeam(PlayerTeamMatcherRepoImpl matcherRepo, String playerId, String teamTitle, String year) {
		return matcherRepo.allotPlayerToTeam(playerId, teamTitle, year);
	}
	
	public static int getExistingNumberOfPlayersInTeam(PlayerTeamMatcherRepoImpl matcherRepo, String teamTitle) {
		return matcherRepo.getPlayersByTeamTitle(teamTitle).size();
	}
	
	public static List<PlayerTeamMatcher> fetchFinalTeam(PlayerTeamMatcherRepoImpl matcherRepo, String teamTitle) {
		return matcherRepo.getPlayersByTeamTitle(teamTitle);
	}
}
