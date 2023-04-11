import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		StreamCollectorsExercise sce = new StreamCollectorsExercise();
		
		Map<String, List<Player>> f1 = sce.getPlayersByCountry();
		f1.forEach((key, value) -> System.out.println(key + ":" + value));
		
		Map<String, List<String>> f2 = sce.getPlayerNamesByCountry();
		f2.forEach((key, value) -> System.out.println(key + ":" + value));
		
		LinkedHashMap<String, Integer> f3 = sce.getTotalPlayersByCountry();
		f3.forEach((key, value) -> System.out.println(key + ":" + value));
		
		Map<String, Integer> f4 = sce.getTotalRunsByCountry();
		f4.forEach((key, value) -> System.out.println(key + ":" + value));
		
		Map<String, Integer> f5 = sce.getMaxScoreByCountry();
		f5.forEach((key, value) -> System.out.println(key + ":" + value));
		
		Map<String, String> f6 = sce.getPlayerNamesStringByCountry();
		f6.forEach((key, value) -> System.out.println(key + ":" + value));
	
	}

}
