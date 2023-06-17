package model;

public class Renewable {
    private final int scholarshipID;
    private final int amount;
    private final float dateOfRenewal;
    private final int donorID;

    public Renewable(int scholarshipID, int amount, float dateOfRenewal, int donorID) {
        this.scholarshipID = scholarshipID;
        this.amount = amount;
        this.dateOfRenewal = dateOfRenewal;
        this.donorID = donorID;
    }

    public int getScholarshipID() {
        return scholarshipID;
    }

    public int getAmount() {
        return amount;
    }

    public float getDateOfRenewal() {
        return dateOfRenewal;
    }

    public int getDonorID() {
        return donorID;
    }
}
