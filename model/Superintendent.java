package model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Superintendent {
    private final int superintendentID;
    private final String firstName;
    private final String lastName;

    public Superintendent(int superintendentID, String firstName, String lastName) {
        this.superintendentID = superintendentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getSuperintendentID() {
        return superintendentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
