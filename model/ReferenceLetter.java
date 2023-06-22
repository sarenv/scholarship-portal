package model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class ReferenceLetter {
    private final int referenceID;
    private final int applicationID; // foreign key to Applicant.java
    private final String name;
    private final String email;
    private final String school;
    private final String position;

    public ReferenceLetter(int referenceID, int applicationID, String name, String email, String school, String position) {
        this.referenceID = referenceID;
        this.applicationID = applicationID; // foreign key!!
        this.name = name;
        this.email = email;
        this.school = school;
        this.position = position;
    }

    public int getReferenceID() {
        return referenceID;
    }
    public int getApplicationID() { return applicationID; }
    public String getName() {
        return name;
    }
    public String getEmail() { return email; }
    public String getSchool() { return school; }
    public String getPosition() { return position; }
}
