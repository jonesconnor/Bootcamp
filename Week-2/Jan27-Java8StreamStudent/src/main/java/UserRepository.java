import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	
	private static List<Student> studentList = new ArrayList<>();
	
	public static void addStudent(Student stu) {
		studentList.add(stu);
	}
	
	public static List<Student> getStudents() {
		return studentList;
	}
}
