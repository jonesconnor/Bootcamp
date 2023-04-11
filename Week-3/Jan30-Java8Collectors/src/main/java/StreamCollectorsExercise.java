import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExercise {
	
	private static List<Player> playerList = new ArrayList<>();
	
	public StreamCollectorsExercise() {
		playerList.add(new Player("Alphonso", 150, 200, 4600, new Country("CAN", "Canada")));
		playerList.add(new Player("Lionel", 400, 800, 9200, new Country("ARG", "Argentina")));
		playerList.add(new Player("Cristiano", 300, 750, 8400, new Country("POR", "Portugal")));
		playerList.add(new Player("Angel", 320, 600, 7500, new Country("ARG", "Argentina")));
		playerList.add(new Player("Alejandro", 200, 300, 5000, new Country("ARG", "Argentina")));
		playerList.add(new Player("Andre", 250, 400, 5500, new Country("POR", "Portugal")));
	}
	
	public Map<String, List<Player>> getPlayersByCountry() {
		return playerList.stream()
			.filter(p->p.getMatchesPlayed() > 100)
			.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), Collectors.toList()));
	}
	
	public Map<String, List<String>> getPlayerNamesByCountry() {
		return playerList.stream()
			.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), Collectors.mapping(Player::getPlayerName, Collectors.toList())));
	}
	
	public LinkedHashMap<String, Integer> getTotalPlayersByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), LinkedHashMap::new, Collectors.collectingAndThen(Collectors.counting(), c -> (int) c.longValue())));
	}
	
	public Map<String, Integer> getTotalRunsByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), Collectors.summingInt(Player::getRuns)));
	}
	
	public Map<String, Integer> getMaxScoreByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Player::getRuns)), o->o.map(p->p.getRuns()).orElse(0))));
	}
	
	public Map<String, String> getPlayerNamesStringByCountry() {
		return playerList.stream()
				.collect(Collectors.groupingBy(p->p.getCountry().getCountryName(), Collectors.mapping(Player::getPlayerName, Collectors.joining(","))));
	}
}
