package project_d7i1y_q3d6f_z8h1l.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class SelectionCriteria {
    private final int criteriaID;
    private final String major;
    private final float GPA;
    private final int familyIncome;

    public SelectionCriteria(int criteriaID, String major, float GPA, int familyIncome) {
        this.criteriaID = criteriaID;
        this.major = major;
        this.GPA = GPA;
        this.familyIncome = familyIncome;
    }

    public int getCriteriaID() {
        return criteriaID;
    }
    public String getMajor() {
        return major;
    }
    public float getGPA() {
        return GPA;
    }
    public int getFamilyIncome() { return familyIncome; }
}
