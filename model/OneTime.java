package model;

public class OneTime {
    private final int scholarshipID;
    private final int amount;
    private final int donorID;

    public OneTime(int scholarshipID, int amount, int donorID) {
        this.scholarshipID = scholarshipID;
        this.amount = amount;
        this.donorID = donorID;
    }

    public int getScholarshipID() {
        return scholarshipID;
    }

    public int getAmount() {
        return amount;
    }

    public int getDonorID() {
        return donorID;
    }
}
