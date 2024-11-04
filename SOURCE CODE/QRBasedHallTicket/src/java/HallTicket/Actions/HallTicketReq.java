/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HallTicket.Actions;

import HallTicket.SQLconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alen
 */
public class HallTicketReq extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String year = request.getParameter("Year");
            int studentId = Integer.parseInt(request.getParameter("studentid"));
            String department = request.getParameter("Department");
            String semester = request.getParameter("Semester");
            String feesStatus = request.getParameter("fees");
            String referenceNumber = request.getParameter("refn");
            String sname = request.getParameter("sname");
            String smail = request.getParameter("smail");
            String rollno = request.getParameter("rollno");

            // If reference number is null, set it as "Not Applicable"
            if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
                referenceNumber = "Not Applicable";
            }

            try {

                Connection connection = SQLconnection.getconnection();

                String insertQuery = "INSERT INTO hall_ticket_requests (year, student_id, department, semester, fees_status, reference_number,sname,smail,rollno) VALUES ( ?, ?, ?, ?, ?, ?,?,?,?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, year);
                insertStatement.setInt(2, studentId);

                insertStatement.setString(3, department);
                insertStatement.setString(4, semester);
                insertStatement.setString(5, feesStatus);
                insertStatement.setString(6, referenceNumber);
                insertStatement.setString(7, sname);
                insertStatement.setString(8, smail);
                insertStatement.setString(9, rollno);

                insertStatement.executeUpdate();

                insertStatement.close();
                connection.close();

                response.sendRedirect("HallTicketRequest.jsp?Sent"); // Redirect to a success page
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions here
            }
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

}
