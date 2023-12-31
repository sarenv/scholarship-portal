package model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Application {
    private final int applicationID;
    private final int applicantID;
    private final String deadline; // no date

    public Application(int applicationID, int applicantID, String deadline) {
        this.applicationID = applicationID;
        this.applicantID = applicantID;
        this.deadline = deadline;
    }

    public int getApplicationID() {
        return applicationID;
    }
    public int getApplicantID() { return applicantID; }
    public String getDeadline() { return deadline; }
}
