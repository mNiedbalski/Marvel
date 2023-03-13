package pl.polsl.niedbalski.michal.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet presenting the use of cookies
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
@WebServlet("/CookieAccess")
public class CookieAccessServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    switch (cookie.getName()) {
                        case "affiliationVisits":
                            request.setAttribute("affiliationVisits",cookie.getValue());
                            break;
                        case "pearsonCorrVisits":
                            request.setAttribute("pearsonCorrVisits",cookie.getValue());
                            break;
                        case "strongestVisits":
                            request.setAttribute("strongestVisits",cookie.getValue());
                            break;
                        default:
                            break;
                    }
                }
            }
        request.getRequestDispatcher("/WEB-INF/CookiesPage.jsp").forward(request,response);
        response.sendRedirect("/WEB-INF/CookiesPage.jsp");
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
        return "Servlet presenting the use of cookies.";
    }// </editor-fold>

}
