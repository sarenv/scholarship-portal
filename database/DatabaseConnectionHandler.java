// Utilized the DatabaseConnectionHandler sample file and changed to accommodate our project
// Citing CPSC 304 java project as primary source!!!
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    // View applicant table
    public ArrayList<String[]> applicantTable() {
        ArrayList<String[]> res = new ArrayList<>();
        try {
            String query = "SELECT * FROM Applicant";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String temp1 = String.valueOf(rs.getInt("ApplicantID"));
                String temp2 = rs.getString("firstName");
                String temp3 = String.valueOf(rs.getInt("lastName"));
                String temp4 = String.valueOf(rs.getInt("applicantEmail"));
                String temp5 = String.valueOf(rs.getInt("applicantSchool"));
                String temp6 = String.valueOf(rs.getFloat("applicantGPA"));
                String[] temp7 = new String[6];
                temp7[0] = temp1;
                temp7[1] = temp2;
                temp7[2] = temp3;
                temp7[3] = temp4;
                temp7[4] = temp5;
                temp7[5] = temp6;
                res.add(temp7);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return res;

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

    // DELETE QUERY
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
            ps.setInt(1, application.getApplicationID());
            ps.setInt(2, application.getApplicantID());
            ps.setString(3, application.getDeadline());

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
                Application model = new Application(rs.getInt("applicationID"), rs.getInt("applicantID"), rs.getString("deadline"));
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
            String query = "UPDATE SelectionCriteria SET minimumGPA = ?, SET major = ?, SET familyIncome = ?,  WHERE criteriaID = ?";
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

    // PROJECTION QUERY
    // choose any number of attributes (or columns) from a table (or relation)
    public ArrayList<String[]> projectionTable(ArrayList<String> columns, String relation) {
        ArrayList<String[]> result = new ArrayList<>();

        try {
            String colStr = String.join(", " + columns); // different types of columns
            String query = "SELECT " + colStr + " FROM " + relation;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] elements = new String[columns.size()]; // have an emtpy array of selected columns
                for (int i = 0; i < columns.size(); i++) { // iterate through all the possible columns, i is the current column index
                    elements[i] = rs.getString(columns.get(i)); // using the query, put the string of particular column and put it in the array of selected columns
                }
                result.add(elements);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result;
    }
    // JOIN QUERY
    public ArrayList<String[]> findApplicationStatus(int applicantID) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String query = "SELECT  AT.applicantID, AC.applicationID, E.status " +
                    "FROM Applicant AT, Application AC, EVALUATES E " +
                    "WHERE AT.applicantID = AC.applicantID and AC.applicationID = E.applicationID and AT.applicantID = ?";

            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query),query);
            ps.setInt(1, applicantID);

            ResultSet rs = ps.executeQuery();
            String applicationID = "";
            String status = "";
            while (rs.next()) {
                String[] dummy = new String[2];
                applicationID = String.valueOf(rs.getInt("applicationID"));
                status = rs.getString("status");
                dummy[0] = applicationID;
                dummy[1] = status;
                result.add(dummy);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

    // Aggregation with HAVING
    // For each major requirement that has more than one scholarship, find the minimum GPA
    public ArrayList<String[]> findMinGPAForEachMajor (int inputGPA) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String query = "SELECT major, MIN(minimumGPA) AS GPA " +
                            "FROM SELECTIONCRITERIA SC " +
                            "GROUP BY major " +
                            "HAVING COUNT(*) > 1 AND min(MINIMUMGPA) <= ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query),query);
            ps.setInt(1, inputGPA);

            ResultSet rs = ps.executeQuery();
            String major = "";
            String GPA = "";


            while (rs.next()) {
                    String[] dummy = new String[2];
                    major = rs.getString("major");
                    GPA = String.valueOf(rs.getInt("GPA"));
                    dummy[0] = major;
                    dummy[1] = GPA;
                    result.add(dummy);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }


    // SELECTION QUERY
    // choose which table and attributes (columns) to select on + values of the specific condition
    public ArrayList<String[]> selectionTable(ArrayList<String> relations, ArrayList<String> columns) {
        ArrayList<String[]> result = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>(); // need to populate the map in main function?

        try {
            StringBuilder allcolumns = new StringBuilder();
            for (String column: columns) {
                if (map.containsKey(column)) {
                    String tablename = map.get(column); // take in the column's table
                    allcolumns.append(tablename).append(".").append(column).append(", ");
                }
                if (allcolumns.length() <= 1) { // if there is only one column or less to choose from, no need the commas
                    allcolumns.delete(allcolumns.length() - 2, allcolumns.length()); // u delete from the end to the last 2 spaces
                }
            }

            String tableString = String.join(", " + relations); // different types of tables
            String query = "SELECT " + allcolumns + " FROM " + tableString;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String[] elements = new String[columns.size()]; // have an emtpy array of selected columns
                for (int i = 0; i < columns.size(); i++) { // iterate through all the possible columns, i is the current column index
                    elements[i] = rs.getString(columns.get(i)); // using the query, put the string of particular column and put it in the array of selected columns
                }
                result.add(elements);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }


    // Division using WHERE EXISTS
    // Finding the applicants who submitted an application
    public ArrayList<Integer> findAllApplied() {
        ArrayList<Integer> res = new ArrayList<>();
        try {
            String query = "SELECT ApplicantID FROM Applicant APP WHERE EXISTS (SELECT A.ApplicantID FROM Application A WHERE APP.ApplicantID = A.ApplicantID)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int temp = rs.getInt("ApplicantID");
                res.add(temp);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return res;

    }

    // Aggregation with GROUP BY
    // Finding the minimum GPA required for each major in SelectionCriteria table
    public ArrayList<String[]> findminGPAforMajor() {
        ArrayList<String[]> res = new ArrayList<>();
        try {
            String query = "SELECT major, MIN(minimumGPA) FROM SELECTIONCRITERIA GROUP BY major";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String temp1 = rs.getString("major");
                String temp2 = String.valueOf(rs.getInt("minimumGPA"));
                String[] temp3 = new String[2];
                temp3[0] = temp1;
                temp3[1] = temp2;
                res.add(temp3);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return res;

    }


    // Nested Aggregation with GROUP BY
    // Finding the average GPA for each school in the Applicant table and retrieving schools where the average GPA is higher than the overall average GPA across all schools
    public ArrayList<String[]> findAvgGPAWhereHigher() {
        ArrayList<String[]> res = new ArrayList<>();
        try {
            String query = "SELECT applicantSchool, Avg(applicantGPA) FROM Applicant GROUP BY applicantSchool HAVING Avg(applicantGPA) > (SELECT Avg(applicantGPA) FROM Applicant)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String temp1 = rs.getString("applicantSchool");
                String temp2 = String.valueOf(rs.getInt("applicantGPA"));
                String[] temp3 = new String[2];
                temp3[0] = temp1;
                temp3[1] = temp2;
                res.add(temp3);
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
    }

        

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

