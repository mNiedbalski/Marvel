package pl.polsl.niedbalski.michal.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;

/**
 * Calculating Pearson Correlation servlet.
 * @author Michał Niedbalski
 * @version 1.0
 */
@WebServlet("/DisplayPearsonCorr")
public class DisplayingPearsonCorrelationServlet extends HttpServlet {

/**
 * Class field representing model in MVC
 */
    private LogicalOperations logicalOperations = LogicalOperations.getInstance();
 /**
 * Class field partly representing view in MVC
 */
    private UserInterface userInterface = new UserInterface();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-2");
        Cookie[] cookies = request.getCookies();
        Cookie counterCookie = new Cookie("pearsonCorrVisits","0");
        boolean foundCookie = false;
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("pearsonCorrVisits")){
                    int counter = Integer.parseInt(cookie.getValue());
                    counter++;
                    cookie.setValue(Integer.toString(counter));
                    response.addCookie(cookie);
                    foundCookie = true;
                    break;
                }
            }
            if (!foundCookie){
            response.addCookie(counterCookie); 
            }
        }
        else{
            response.addCookie(counterCookie);
        }
        request.setAttribute("pearsonCorr", userInterface.displayPearsonCorrelation(logicalOperations.calculatePearsonCorrelation()));
        request.getRequestDispatcher("/WEB-INF/PearsonCorrelationPage.jsp").forward(request,response);
        response.sendRedirect("/WEB-INF/PearsonCorrelationPage.jsp");
    }
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
        return "Calculating Pearson Correlation servlet.";
    }

}
