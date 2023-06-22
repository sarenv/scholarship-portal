package model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Applicant {
	private final int applicantID;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String school;
	private final float GPA; // there is no decimal
	
	public Applicant(int applicantID, String firstName, String lastName, String email, String school, float GPA) {
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

	public float getGPA() { return GPA; }
}
