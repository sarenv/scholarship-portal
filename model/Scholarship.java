package model;

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
