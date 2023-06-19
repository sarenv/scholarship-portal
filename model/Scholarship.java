package project_d7i1y_q3d6f_z8h1l.model;

/**
 * The intent for this class is to update/store information about a single branch
 */

// TODO : WE NEED TO ADD THE SQL DDL FOR THIS !!!!
public class Scholarship {
    private final int donorID;
    private final int scholarshipID;
    private final int amount;

    public Scholarship(int donorID, int scholarshipID, int amount) {
        this.donorID = donorID;
        this.scholarshipID = scholarshipID;
        this.amount = amount;
    }

    public int getDonorID() {
        return donorID;
    }
    public int getScholarshipID() { return scholarshipID; }
    public int getAmount() { return amount; }
}
