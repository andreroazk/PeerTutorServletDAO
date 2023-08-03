/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * The PeerTutorDAOImpl class implements the PeerTutorDAO interface and provides database operations
 * related to peer tutors and their assigned courses.
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {

    /**
     * Checks if a peer tutor is registered in the database.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor to be checked.
     * @return true if the peer tutor is registered, false otherwise.
     */
    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer peerTutorId = null;
        String qry = "SELECT PeerTutorID FROM PeerTutor WHERE LastName = ? AND FirstName = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2, peerTutor.getFirstName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                peerTutorId = rs.getInt("PeerTutorID");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return peerTutorId != null;
    }
    
    /**
     * Checks if a course is valid and exists in the database.
     *
     * @param courseCode The course code to be checked.
     * @return true if the course is valid, false otherwise.
     */
    @Override
    public boolean isCourseValid(String courseCode) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String courseCodeDB = null;
        String qry = "SELECT CourseCode FROM Course WHERE CourseCode = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                courseCodeDB = rs.getString("CourseCode");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return courseCodeDB != null;
    }

     /**
     * Checks if a peer tutor has taken a specific course based on their last name, first name, and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor to be checked.
     * @param courseCode The course code to be checked.
     * @return true if the peer tutor has taken the course, false otherwise.
     */
    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String courseCodeDB = null;
        String qry = "SELECT Course_CourseCode FROM StudentCourse sc "
                + "INNER JOIN Student s ON s.StudentID = sc.Student_StudentID "
                + "WHERE sc.Course_CourseCode = ? AND s.LastName = ? AND s.FirstName = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, peerTutor.getFirstName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                courseCodeDB = rs.getString("Course_CourseCode");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return courseCodeDB != null;
    }

    /**
     * Retrieves the letter grade received by a peer tutor for a specific course.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code for which to retrieve the letter grade.
     * @return The letter grade as a String for the specified course. Empty String if not available.
     */
    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String grade = "";
        String qry = "SELECT GradeCode FROM Student s "
                + "INNER JOIN Grade g ON s.StudentID = g.Student_StudentID "
                + "WHERE g.Course_CourseCode = ? AND s.LastName = ? AND s.FirstName = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, peerTutor.getFirstName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                grade = rs.getString("GradeCode");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return grade;
    }
    
    /**
     * Checks if a course is already assigned to a peer tutor based on their last name, first name, and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be checked for assignment.
     * @return true if the course is already assigned to the peer tutor, false otherwise.
     */
    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer peerTutorID = null;
        String qry = "SELECT PeerTutor_PeerTutorID FROM PeerTutorCourse ptc "
                + "INNER JOIN PeerTutor pt ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID "
                + "WHERE Course_CourseCode = ? AND LastName = ? AND FirstName = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, peerTutor.getFirstName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                peerTutorID = rs.getInt("PeerTutor_PeerTutorID");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return peerTutorID != null;
    }

    /**
     * Assigns a course to a peer tutor based on their last name, first name, and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be assigned to the peer tutor.
     */
    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        String qry = "INSERT INTO PeerTutorCourse SELECT PeerTutorID, ? "
                        + "FROM PeerTutor WHERE LastName = ? AND FirstName = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, peerTutor.getFirstName());
            pstmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
    }

    /**
     * Retrieves a list of all peer tutors who are assigned to a specific course.
     *
     * @param courseCode The course code for which to retrieve the peer tutors.
     * @return A list of PeerTutor objects representing the assigned peer tutors for the course.
     */
    @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PeerTutor> peerTutors = new ArrayList<>();
        String qry = "SELECT pt.* FROM PeerTutor pt "
                + "INNER JOIN PeerTutorCourse ptc ON pt.PeerTutorID = ptc.PeerTutor_PeerTutorID "
                + "WHERE Course_CourseCode = ?";
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(qry);
            pstmt.setString(1, courseCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PeerTutor pt = new PeerTutor();
                pt.setPeerTutorID(rs.getInt("PeerTutorID"));
                pt.setLastName(rs.getString("LastName"));
                pt.setFirstName(rs.getString("FirstName"));
                
                peerTutors.add(pt);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        finally{
            try {
                if(rs != null) rs.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(pstmt != null) pstmt.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            try {
                if(con != null) con.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }    
        } 
        return peerTutors;
    }
}
