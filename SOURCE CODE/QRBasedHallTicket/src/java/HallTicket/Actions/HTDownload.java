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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alen
 */
public class HTDownload extends HttpServlet {

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
            
              HttpSession session = request.getSession();
            /* TODO output your page here. You may use following sample code. */
            String year = request.getParameter("Year");
            String department = request.getParameter("Department");
            String semester = request.getParameter("Semester");
            String reqid = request.getParameter("reqid");
            String rollno = request.getParameter("rollno");
            Connection con = SQLconnection.getconnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            try {
                ResultSet rs1 = st1.executeQuery("SELECT * FROM hall_ticket_requests where semester='" + semester + "' AND year='" + year + "' AND department='" + department + "' AND rollno='" + rollno + "' AND id='" + reqid + "' AND htstatus='Approved'");
                System.out.println("Check 1 Pass");
                if (rs1.next()) {
                    String unique_id=rs1.getString("unique_id");
                    ResultSet resultSet = st.executeQuery("SELECT * FROM subjects where semester='" + semester + "' AND year='" + year + "' AND department='" + department + "' ");
                    if (resultSet.next()) {
                        System.out.println("Check 2 Pass");
                        session.setAttribute("semester", semester);
                        session.setAttribute("year", year);
                        session.setAttribute("department", department);
                        session.setAttribute("unique_id", unique_id);

                        String[] subjects = new String[]{
                            resultSet.getString("subject1"),
                            resultSet.getString("subject2"),
                            resultSet.getString("subject3"),
                            resultSet.getString("subject4"),
                            resultSet.getString("subject5")
                        };
                        session.setAttribute("subjects", subjects);

                        String[] labs = new String[]{
                            resultSet.getString("lab1"),
                            resultSet.getString("lab2")
                        };
                        session.setAttribute("labs", labs);

                        String[] subjectDates = new String[5];
                        for (int i = 0; i < 5; i++) {
                            subjectDates[i] = resultSet.getString("subject" + (i + 1) + "_date");
                        }
                        session.setAttribute("subjectDates", subjectDates);

                        String[] labDates = new String[2];
                        for (int i = 0; i < 2; i++) {
                            labDates[i] = resultSet.getString("lab" + (i + 1) + "_date");
                        }
                        session.setAttribute("labDates", labDates);

                        String[] startTimes = new String[]{
                            resultSet.getString("subject1_start_time"),
                            resultSet.getString("subject2_start_time"),
                            resultSet.getString("subject3_start_time"),
                            resultSet.getString("subject4_start_time"),
                            resultSet.getString("subject5_start_time"),
                            resultSet.getString("lab1_start_time"),
                            resultSet.getString("lab2_start_time")
                        };
                        session.setAttribute("startTimes", startTimes);

                        String[] endTimes = new String[]{
                            resultSet.getString("subject1_end_time"),
                            resultSet.getString("subject2_end_time"),
                            resultSet.getString("subject3_end_time"),
                            resultSet.getString("subject4_end_time"),
                            resultSet.getString("subject5_end_time"),
                            resultSet.getString("lab1_end_time"),
                            resultSet.getString("lab2_end_time")
                        };
                        session.setAttribute("endTimes", endTimes);

                        con.close();
                        response.sendRedirect("HallTicketProcess.jsp");

                    } else {
                        response.sendRedirect("DownloadHallTicket.jsp?not");

                    }
                } else {
                    response.sendRedirect("DownloadHallTicket.jsp?not");
                }

            } catch (IOException | SQLException ex) {
            }

        } catch (SQLException ex) {
            Logger.getLogger(HTDownload.class.getName()).log(Level.SEVERE, null, ex);
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
