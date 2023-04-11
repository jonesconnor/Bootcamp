package com.stackroute.streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BatsmanService {
	
	public BatsmanService() {
	}
	
	public Optional<Batsman> getBatsman(List<Batsman> batsmanList, String name, String countryCode){
		
		if (batsmanList == null  || name == null || countryCode == null || batsmanList.isEmpty()) {
			return Optional.empty();
		}
		
		Predicate<Batsman> filterByName = b -> b.getName().equalsIgnoreCase(name);
		Optional<Batsman> searchBatsman = batsmanList.stream()
			.filter(filterByName)
			.filter(c -> checkCountryCode(c, countryCode))
			.findFirst();
		return searchBatsman;
	}
	
	private boolean checkCountryCode(Batsman batsman, String countryCode) {
		if (!(batsman.getCountry().getCountryCode().equalsIgnoreCase(countryCode))) {
	        throw new CountryNotFoundException("Invalid Country Code");
	    } 
	    return true;
	}
	
	public String getBatsmanNamesForCountry(List<Batsman> batsmanList, String countryCode) {
		
		if (batsmanList == null || batsmanList.isEmpty() || countryCode == null) {
			return null;
		}
		
		Predicate<Batsman> filterByCountryCode = b -> b.getCountry().getCountryCode().equals(countryCode);
		List<String> searchNames = batsmanList.stream()
			.filter(filterByCountryCode)
			.map(Batsman::getName)
			.sorted()
			.collect(Collectors.toList());
		
		String output = "[" + String.join(",", searchNames) + "]";
		
		return output;
	}
	
	public Map<String,Integer> getPlayerNameWithTotalRuns(List<Batsman> batsmanList) {
		
		if (batsmanList == null || batsmanList.isEmpty()) {
			return Collections.emptyMap();
		}
		
		Map<String, Integer> playerWithRuns = batsmanList.stream()
				.collect(Collectors.groupingBy(Batsman::getName, Collectors.summingInt(Batsman::getTotalRuns)));
				
		return playerWithRuns;
	}
	
	public int getHighestRunsScoredByBatsman(List<Batsman> batsmanList, String countryName) {
		
		if (batsmanList == null || batsmanList.isEmpty()) {
			return 0;
		}
		
		int highestRuns = batsmanList.stream()
				.filter(b->b.getCountry().getName().equalsIgnoreCase(countryName))
				.mapToInt(Batsman::getHighestScore)
				.max()
				.orElse(0);
		
		return highestRuns;
	}
	
	public Optional<LinkedList<String>> getPlayerNamesByCountry(List<Batsman> batsmanList, String countryName) {
		
		if (batsmanList == null || countryName == null || batsmanList.isEmpty()) {
			return Optional.empty();
		}
		
		LinkedList<String> names = batsmanList.stream()
			.filter(b->b.getCountry().getName().equals(countryName))
			.filter(b->b.getTotalRuns() > 5000)
			.map(Batsman::getName)
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toCollection(LinkedList::new));
		
		if (names.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(names);
	}
}
