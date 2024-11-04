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
import java.sql.ResultSet;
import java.sql.SQLException;
  import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alen
 */
public class addsubjects extends HttpServlet {

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
            String department = request.getParameter("Department");
            String semester = request.getParameter("Semester");
            
            // Get exam date and time for each subject and lab
            String[] subjects = new String[5];
            String[] subjectDates = new String[5];
            String[] subjectStartTimes = new String[5];
            String[] subjectEndTimes = new String[5];
            
            for (int i = 0; i < 5; i++) {
                subjects[i] = request.getParameter("subject" + (i + 1));
                subjectDates[i] = request.getParameter("subject" + (i + 1) + "_date");
                subjectStartTimes[i] = request.getParameter("subject" + (i + 1) + "_start_time");
                subjectEndTimes[i] = request.getParameter("subject" + (i + 1) + "_end_time");
            }

            String[] labs = new String[2];
            String[] labDates = new String[2];
            String[] labStartTimes = new String[2];
            String[] labEndTimes = new String[2];
            
            for (int i = 0; i < 2; i++) {
                labs[i] = request.getParameter("lab" + (i + 1));
                labDates[i] = request.getParameter("lab" + (i + 1) + "_date");
                labStartTimes[i] = request.getParameter("lab" + (i + 1) + "_start_time");
                labEndTimes[i] = request.getParameter("lab" + (i + 1) + "_end_time");
            }

            try {
                Connection connection = SQLconnection.getconnection();

                String checkQuery = "SELECT id FROM subjects WHERE year=? AND department=? AND semester=?";
                PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                checkStatement.setString(1, year);
                checkStatement.setString(2, department);
                checkStatement.setString(3, semester);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    String updateQuery = "UPDATE subjects SET ";
                    for (int i = 0; i < 5; i++) {
                        updateQuery += "subject" + (i + 1) + "=?, subject" + (i + 1) + "_date=?, subject" + (i + 1) + "_start_time=?, subject" + (i + 1) + "_end_time=?, ";
                    }
                    for (int i = 0; i < 2; i++) {
                        updateQuery += "lab" + (i + 1) + "=?, lab" + (i + 1) + "_date=?, lab" + (i + 1) + "_start_time=?, lab" + (i + 1) + "_end_time=?, ";
                    }
                    updateQuery = updateQuery.substring(0, updateQuery.length() - 2); // Remove the trailing comma and space
                    updateQuery += " WHERE id=?";
                    
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    int parameterIndex = 1;
                    for (int i = 0; i < 5; i++) {
                        updateStatement.setString(parameterIndex++, subjects[i]);
                        updateStatement.setString(parameterIndex++, subjectDates[i]);
                        updateStatement.setString(parameterIndex++, subjectStartTimes[i]);
                        updateStatement.setString(parameterIndex++, subjectEndTimes[i]);
                    }
                    for (int i = 0; i < 2; i++) {
                        updateStatement.setString(parameterIndex++, labs[i]);
                        updateStatement.setString(parameterIndex++, labDates[i]);
                        updateStatement.setString(parameterIndex++, labStartTimes[i]);
                        updateStatement.setString(parameterIndex++, labEndTimes[i]);
                    }
                    updateStatement.setInt(parameterIndex, resultSet.getInt("id"));
                    updateStatement.executeUpdate();
                    updateStatement.close();
                    response.sendRedirect("addSub.jsp?Updated");
                } else {
                    String insertQuery = "INSERT INTO subjects (year, department, semester, ";
                    for (int i = 0; i < 5; i++) {
                        insertQuery += "subject" + (i + 1) + ", subject" + (i + 1) + "_date, subject" + (i + 1) + "_start_time, subject" + (i + 1) + "_end_time, ";
                    }
                    for (int i = 0; i < 2; i++) {
                        insertQuery += "lab" + (i + 1) + ", lab" + (i + 1) + "_date, lab" + (i + 1) + "_start_time, lab" + (i + 1) + "_end_time, ";
                    }
                    insertQuery = insertQuery.substring(0, insertQuery.length() - 2); // Remove the trailing comma and space
                    insertQuery += ") VALUES (?, ?, ?, ";
                    for (int i = 0; i < 5; i++) {
                        insertQuery += "?, ?, ?, ?, ";
                    }
                    for (int i = 0; i < 2; i++) {
                        insertQuery += "?, ?, ?, ?, ";
                    }
                    insertQuery = insertQuery.substring(0, insertQuery.length() - 2); // Remove the trailing comma and space
                    insertQuery += ")";
                    
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    int parameterIndex = 1;
                    insertStatement.setString(parameterIndex++, year);
                    insertStatement.setString(parameterIndex++, department);
                    insertStatement.setString(parameterIndex++, semester);
                    for (int i = 0; i < 5; i++) {
                    insertStatement.setString(parameterIndex++, subjects[i]);
                    insertStatement.setString(parameterIndex++, subjectDates[i]);
                    insertStatement.setString(parameterIndex++, subjectStartTimes[i]);
                    insertStatement.setString(parameterIndex++, subjectEndTimes[i]);
                }
                for (int i = 0; i < 2; i++) {
                    insertStatement.setString(parameterIndex++, labs[i]);
                    insertStatement.setString(parameterIndex++, labDates[i]);
                    insertStatement.setString(parameterIndex++, labStartTimes[i]);
                    insertStatement.setString(parameterIndex++, labEndTimes[i]);
                }

                // Execute the insert statement
                insertStatement.executeUpdate();
                insertStatement.close();

                // Close the connection
                connection.close();

                // Redirect to the appropriate page indicating success
                response.sendRedirect("addSub.jsp?Added");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any exceptions here
                // Redirect to an error page if needed
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
