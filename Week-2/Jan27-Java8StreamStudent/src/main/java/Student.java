
public class Student {
	private String name;
	private String subject;
	private String collegeName;
	private int rank;
	private University uni;
	
	public Student(String name, String subject, String collegeName, int rank, University uni) {
		setName(name);
		setSubject(subject);
		setCollegeName(collegeName);
		setRank(rank);
		setUni(uni);
	}

	public University getUni() {
		return uni;
	}

	public void setUni(University uni) {
		this.uni = uni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
    public String toString() {
        return "Name: " + name + ", Major: " + subject + ", College: " + collegeName + ", Rank: " + rank + ", University: " + uni.getUniversityName();
    }
	
}
