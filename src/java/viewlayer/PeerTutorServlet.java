/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package viewlayer;

import businesslayer.PeerTutorBusinessLogic;
import java.io.*;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.PeerTutor;


/**
 * The PeerTutorServlet class is a Servlet that handles HTTP requests related to peer tutors and courses.
 * It provides methods to process requests, handle GET and POST methods, and display information in HTML format.
 */
public class PeerTutorServlet extends HttpServlet {

    private PrintWriter out;
    private PeerTutor currentTutor;
    private String courseCode;
    
    /**
     * Processes the HTTP request and generates an HTML response.
     *
     * @param request  The HTTP request received from the client.
     * @param response The HTTP response to be sent back to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while processing the request or response.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            out = response.getWriter();
            printHeader(request.getContextPath());

            // Extract information from the request
            currentTutor = new PeerTutor();
            currentTutor.setLastName(request.getParameter("lastName"));
            currentTutor.setFirstName(request.getParameter("firstName"));
            courseCode = request.getParameter("courseCode");

            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            
            // Perform validation and business logic checks
            if (!logic.isPeerTutorRegistered(currentTutor)) {
                printError(currentTutor.getLastName(), currentTutor.getFirstName(), null, 
                        "The person is not registered as a peer tutor.");
            } else if (!logic.isCourseValid(courseCode)) {
                printError(null, null, courseCode, 
                        "The course is not valid.");
            } else if (!logic.hasPeerTutorTakenCourse(currentTutor, courseCode)) {
                printError(currentTutor.getLastName(), currentTutor.getFirstName(), courseCode, 
                        "The peer tutor has not taken the course.");
            } else if (!logic.getPeerTutorLetterGradeForCourse(currentTutor, courseCode).contains("A")) {
                printError(currentTutor.getLastName(), currentTutor.getFirstName(), courseCode, 
                        "The letter grade obtained by the peer tutor for the course is not sufficient.");
            } else if (logic.isCourseAlreadyAssignedToPeerTutor(currentTutor, courseCode)) {
                printError(currentTutor.getLastName(), currentTutor.getFirstName(), courseCode, 
                        "The peer tutor is already assigned to the course.");
            } else {
                // If all checks are successful, assign the course to the peer tutor
                logic.assignCourseToPeerTutor(currentTutor, courseCode);
                
                // Display the list of peer tutors assigned to the course
                List<PeerTutor> tutors = logic.getAllPeerTutorsForCourse(courseCode);
                out.println("<h3>Table of Peer Tutors of " + courseCode + "</h3>");
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<td>Tutor ID</td>");
                out.println("<td>Last Name</td>");
                out.println("<td>First Name</td>");
                out.println("</tr>");
                
                for (PeerTutor tutor : tutors) {
                    out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                     tutor.getPeerTutorID(), tutor.getLastName(), tutor.getFirstName());
                }
                out.println("</table>");
            }
            out.println("</body>");
            out.println("</html>");
            
        } catch(IOException e) {
            System.out.println("FATAL ERROR: RUN YOU FOOLS!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Prints the HTML header section for the response.
     *
     * @param context The context path of the servlet for generating links and references.
     */
    private void printHeader(String context) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet PeerTutorServlet</title>");            
        out.println("</head>");
        out.println("<body bgcolor=\"#FDF5E6\">");
        out.println("<h1>Servlet PeerTutorServlet at " + context + "</h1>");
    }

    /**
     * Prints an error message in HTML format for the response.
     *
     * @param lastName   The last name of the peer tutor associated with the error (can be null).
     * @param firstName  The first name of the peer tutor associated with the error (can be null).
     * @param courseCode The course code associated with the error (can be null).
     * @param error      The error message to be displayed.
     */
    private void printError(String lastName, String firstName, String courseCode, String error) {
        out.println("<ul>");
        
        if (lastName != null) {
            out.print("<li><b>Last Name: </b>" + lastName + "</li>");
        }
        if (firstName != null) {
            out.print("<li><b>First Name: </b>" + firstName + "</li>");
        }
        if (courseCode != null) {
            out.print("<li><b>Course Code: </b>" + courseCode + "</li>");
        }
        out.println("</ul>");
        
        out.println("<h3><b>Error: " + error + "</b></h3>");
    }
}
