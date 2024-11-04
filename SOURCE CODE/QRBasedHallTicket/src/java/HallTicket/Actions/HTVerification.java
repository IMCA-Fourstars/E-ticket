/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HallTicket.Actions;

import HallTicket.SQLconnection;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Alen
 */
public class HTVerification extends HttpServlet {

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
            HttpSession session = request.getSession();
            String year = request.getParameter("Year");
            String department = request.getParameter("Department");
            String semester = request.getParameter("Semester");
            String rollno = request.getParameter("rollno");

            String qrValue = null; // To store QR code value
            InputStream inputStream = null;
            try {
                Part qrPart = request.getPart("qr"); // "qr" should match the name of the input file field in the form

                if (qrPart != null) {

                    System.out.println(qrPart.getName());
                    System.out.println(qrPart.getSize());
                    System.out.println(qrPart.getContentType());

                    inputStream = qrPart.getInputStream();
                }
                if (inputStream != null) {

                    BufferedImage image = ImageIO.read(inputStream);
                    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                    Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
                    qrValue = qrCodeResult.getText();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Connection con = SQLconnection.getconnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            try {
                ResultSet rs1 = st1.executeQuery("SELECT * FROM hall_ticket_requests where semester='" + semester + "' AND year='" + year + "' AND department='" + department + "' AND unique_id='" + qrValue + "' AND rollno='" + rollno + "' AND htstatus='Approved'");
                System.out.println("Check 1 Pass");
                if (rs1.next()) {
                    response.sendRedirect("TicketVerification.jsp?not");
                } else {
                    response.sendRedirect("TicketVerification.jsp?Failed");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
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
