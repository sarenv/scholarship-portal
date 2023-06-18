package project_d7i1y_q3d6f_z8h1l.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Application {
    private final int applicationID;
    private final int applicantID;
    private final float deadline; // no date

    public Application(int applicationID, int applicantID, float deadline) {
        this.applicationID = applicationID;
        this.applicantID = applicantID;
        this.deadline = deadline;
    }

    public int getApplicationID() {
        return applicationID;
    }
    public int getApplicantID() { return applicantID; }
    public int getDeadline() { return deadline; }
}
