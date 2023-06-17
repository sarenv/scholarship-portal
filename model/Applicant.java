package project_d7i1y_q3d6f_z8h1l.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Applicant {
	private final int applicantID;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String school;
	private final double GPA; // there is no decimal
	
	public Applicant(int applicantID, String firstName, String lastName, String email, String school, double GPA) {
		this.applicantID = applicantID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.school = school;
		this.GPA = GPA;
	}

	public int getApplicantID() {
		return applicantID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() { return email; }

	public String getSchool() { return school; }

	public double getGPA() { return GPA; }
}
