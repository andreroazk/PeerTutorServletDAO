/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;

/**
 * The PeerTutorDAO interface provides methods to interact with a data source
 * containing information about peer tutors and their assigned courses.
 */
public interface PeerTutorDAO {
    
    /**
     * Checks if a peer tutor is registered in the system.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor to be checked.
     * @return true if the peer tutor is registered, false otherwise.
     */
    boolean isPeerTutorRegistered(PeerTutor peerTutor);
    
    /**
     * Checks if a course is valid and exists in the system.
     *
     * @param courseCode The course code to be checked.
     * @return true if the course is valid, false otherwise.
     */
    boolean isCourseValid(String courseCode);
    
    /**
     * Checks if a peer tutor has taken a specific course.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor to be checked.
     * @param courseCode The course code to be checked.
     * @return true if the peer tutor has taken the course, false otherwise.
     */
    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);
    
    /**
     * Gets the letter grade received by a peer tutor for a specific course.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The course code for which to retrieve the letter grade.
     * @return The letter grade as a String for the specified course. Null if not available.
     */
    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);
    
    /**
     * Checks if a course is already assigned to a peer tutor.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be checked for assignment.
     * @return true if the course is already assigned to the peer tutor, false otherwise.
     */
    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);
    
    /**
     * Assigns a course to a peer tutor.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The course code to be assigned to the peer tutor.
     */
    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode);
    
    /**
     * Retrieves a list of all peer tutors who are assigned to a specific course.
     *
     * @param courseCode The course code for which to retrieve the peer tutors.
     * @return A list of PeerTutor objects representing the assigned peer tutors for the course.
     */
    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}

