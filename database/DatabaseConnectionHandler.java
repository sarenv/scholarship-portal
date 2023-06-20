// Utilized the DatabaseConnectionHandler sample file and changed to accommodate our project
// Citing CPSC 304 java project as primary source!!!
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.util.PrintablePreparedStatement;
import project_d7i1y_q3d6f_z8h1l.model.Application;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void deleteApplication(int applicationID) {
        try {
            String query = "DELETE FROM application WHERE applicationID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, applicationID);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Application " + applicationID + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // INSERT QUERY
    public void insertApplication(Application application) {
        try {
            String query = "INSERT INTO application VALUES (?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, Application.getApplicationID());
            ps.setString(2, Application.getApplicantID());
            ps.setString(3, Application.getDeadline());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public Application[] getApplicationInfo() {
        ArrayList<Application> result = new ArrayList<Application>();

        try {
            String query = "SELECT * FROM Application";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Application model = new Application(rs.getInt("applicationID"),
                        rs.getInt("applicantID"),
                        rs.getString("deadline"));
                result.add(model);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Application[result.size()]);
    }

    // UPDATE QUERY
    public void updateSelectionCriteria(int id, float gpa, String maj, int fi) {
        try {
            String query = "UPDATE SelectionCriteria SET minimumGPA = ?, major = ?, familyIncome = ?  WHERE criteriaID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setFloat(1, gpa);
            ps.setString(2, maj);
            ps.setInt(3, fi);
            ps.setInt(4, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " SelectionCriteria " + id + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // JOIN QUERY
    public void findApplicationStatus(int applicantID) {
        try {
            String query = "SELECT  AT.applicantID, AC.applicationID, E.status " +
                    "FROM Applicant AT, Application AC, EVALUATES E " +
                    "WHERE AT.applicantID = AC.applicantID and AC.applicationID = E.applicationID and AT.applicantID = ?";

            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query),query);
            ps.setInt(1, applicantID);

            ResultSet rs = ps.executeQuery();
            int applicationID = 0;
            String status = "";
            while (rs.next()) {
                applicationID = rs.getInt("applicationID");
                status = rs.getString("status");
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // Aggregation with HAVING
    // For each major requirement that has more than one scholarship, find the minimum GPA
    public void findMinGPAForEachMajor (int inputGPA) {

        try {
            String query = "SELECT major, MIN(minimumGPA) AS GPA " +
                            "FROM SELECTIONCRITERIA SC " +
                            "GROUP BY major " +
                            "HAVING COUNT(*) > 1 AND min(MINIMUMGPA) <= ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query),query);
            ps.setInt(1, inputGPA);

            ResultSet rs = ps.executeQuery();
            String major = "";
            Integer GPA = 0;

            while (rs.next()) {
                    major = rs.getString("major");
                    GPA = rs.getInt("GPA");
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    public List<Integer> findAllApplied() {
        List<Integer> res = new ArrayList<>();
        try {
            String query = "SELECT ApplicantID FROM Applicant APP WHERE EXISTS (SELECT A.ApplicantID FROM Application A WHERE APP.ApplicantID = A.ApplicantID)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int temp = rs.getInt("ApplicantID");
                res.append(temp);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return res;

        }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        dropApplicationTableIfExists();

        try {
            String query = "CREATE TABLE application (ApplicationID integer PRIMARY KEY, ApplicantID integer, deadline Date, FOREIGN KEY (ApplicantID) REFERENCES  Applicant(ApplicantID) ON DELETE CASCADE)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

//        BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//        insertBranch(branch1);
//
//        BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//        insertBranch(branch2);

        

    private void dropApplicationTableIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("application")) {
                    ps.execute("DROP TABLE application");
                    break;
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}

