import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		UserRepository.addStudent(new Student("James","Electrical","ABC college",4,new University("Topone","US")));
		UserRepository.addStudent(new Student("Tim","Electrical","RAC college",3,new University("Topone","US")));
		UserRepository.addStudent(new Student("Richard","ComputeScience","Queen college",12,new University("WestAll","UK")));
		UserRepository.addStudent(new Student("Sim","Civil","RAC college",8,new University("Topone","US")));
		UserRepository.addStudent(new Student("Daniel","Electrical","Justpark college",15,new University("WestAll","UK")));
		
		List<Student> studentList = UserRepository.getStudents();
		
	}
	
	static void listStudentDetailsBelongingtoUni (List<Student> studentList, String uni) {
		Predicate<Student> filterByUniName = s -> s.getUni().getUniversityName().equals(uni);
		studentList.stream()
			.filter(filterByUniName)
			.collect(Collectors.toList())
			.forEach(s -> System.out.println(s.toString()));
	}
	
	static void countNumOfStudentsWithLessThanGivenRank (List<Student> studentList, String uni, int rank) {
		Predicate<Student> filterByUniName = s -> s.getUni().getUniversityName().equals(uni);
		Predicate<Student> filterByRank = s -> s.getRank() < rank;
		double count = studentList.stream()
			.filter(filterByUniName)
			.filter(filterByRank)
			.count();
		System.out.println(count);
	}
	
	static void listStudentsThatSecuredLessThanGivenRankInGivenSubject(List<Student> studentList, String subject, int rank) {
		Predicate<Student> filterByRankInSubject = s -> s.getRank() < rank && s.getSubject().equals(subject);
		studentList.stream()
			.filter(filterByRankInSubject)
			.collect(Collectors.toList())
			.forEach(s -> System.out.println(s.getName()));
	}
	
	static void findAverageRankForGivenSubject(List<Student> studentList, String subject) {
		Predicate<Student> filterbySubject = s -> s.getSubject().equals(subject);
		OptionalDouble avgRank = studentList.stream()
			.filter(filterbySubject)
			.mapToInt(Student::getRank)
			.average();
		
		if (avgRank.isPresent()) {
			System.out.println("Avg Rank for " + subject + ": " + Math.round(avgRank.getAsDouble()));
		} else {
			System.out.println("No " + subject + " students");
		}
	}
	
	static void bestPerformer(List<Student> studentList) {
		Optional<Student> bestRankedStudent = studentList.stream()
				.min(Comparator.comparingInt(Student::getRank));
		
		if (bestRankedStudent.isPresent()) {
			System.out.println(bestRankedStudent.get());
		}
	}
	
	static void findRankInDescendingOrder(List<Student> studentList, String college) {
		studentList.stream()
			.filter(s -> s.getCollegeName().equals(college))
			.sorted(Comparator.comparingInt(Student::getRank).reversed())
			.map(Student::getName)
			.forEach(System.out::println);
	}
}
