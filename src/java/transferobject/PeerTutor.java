/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package transferobject;

/**
 * The PeerTutor class represents a model for a peer tutor.
 * It contains attributes for the peer tutor's ID, last name, and first name.
 */
public class PeerTutor {

    private int peerTutorID; // The unique ID of the peer tutor
    private String lastName; // The last name of the peer tutor
    private String firstName; // The first name of the peer tutor
    
    /**
     * Gets the ID of the peer tutor.
     *
     * @return The ID of the peer tutor.
     */

    public int getPeerTutorID() {
        return peerTutorID;
    }

    /**
     * Sets the ID of the peer tutor.
     *
     * @param peerTutorID The ID to set for the peer tutor.
     */
    public void setPeerTutorID(int peerTutorID) {
        this.peerTutorID = peerTutorID;
    }

    /**
     * Gets the last name of the peer tutor.
     *
     * @return The last name of the peer tutor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the peer tutor.
     *
     * @param lastName The last name to set for the peer tutor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of the peer tutor.
     *
     * @return The first name of the peer tutor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the peer tutor.
     *
     * @param firstName The first name to set for the peer tutor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
