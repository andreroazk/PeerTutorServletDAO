/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package businesslayer;

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;
import transferobject.PeerTutor;

/**
 * The PeerTutorBusinessLogic class represents the business logic layer for managing operations related
 * to peer tutors and courses. It interacts with the underlying data access layer (PeerTutorDAO) to perform
 * various operations on the peer tutor data.
 */
public class PeerTutorBusinessLogic {
    
    private PeerTutorDAO peerTutorDAO; // The data access object for peer tutor operations
    
    /**
     * Constructs a new PeerTutorBusinessLogic object and initializes the PeerTutorDAO implementation.
     */
    public PeerTutorBusinessLogic() {
        peerTutorDAO = new PeerTutorDAOImpl();
    }
    
    /**
     * Checks if a peer tutor is registered in the system.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor to be checked.
     * @return true if the peer tutor is registered, false otherwise.
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }
    
    /**
     * Checks if a course code is valid and exists in the system.
     *
     * @param courseCode The course code to be checked.
     * @return true if the course code is valid, false otherwise.
     */
    public boolean isCourseValid(String courseCode) {
        return peerTutorDAO.isCourseValid(courseCode);
    }
    
    /**
     * Checks if a peer tutor has taken a specific course based on their information and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor to be checked.
     * @param courseCode The course code to be checked.
     * @return true if the peer tutor has taken the course, false otherwise.
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }
    
    /**
     * Retrieves the letter grade received by a peer tutor for a specific course.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code for which to retrieve the letter grade.
     * @return The letter grade as a String for the specified course. Empty String if not available.
     */
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }
    
    /**
     * Checks if a course is already assigned to a peer tutor based on their information and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be checked for assignment.
     * @return true if the course is already assigned to the peer tutor, false otherwise.
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }
    
    /**
     * Assigns a course to a peer tutor based on their information and the course code.
     *
     * @param peerTutor  The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be assigned to the peer tutor.
     */
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }
    
    /**
     * Retrieves a list of all peer tutors who are assigned to a specific course.
     *
     * @param courseCode The course code for which to retrieve the peer tutors.
     * @return A list of PeerTutor objects representing the assigned peer tutors for the course.
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
}
