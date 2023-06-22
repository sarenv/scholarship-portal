package model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Donor {
    private final int donorID;

    public Donor(int donorID) {
        this.donorID = donorID;
    }

    public int getDonorID() {
        return donorID;
    }

}
