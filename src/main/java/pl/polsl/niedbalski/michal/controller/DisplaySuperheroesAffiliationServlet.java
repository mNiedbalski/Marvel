
package pl.polsl.niedbalski.michal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.niedbalski.michal.marvel.model.LogicalOperations;
import pl.polsl.niedbalski.michal.marvel.view.UserInterface;

/**
 *
 * @author Michał Niedbalski
 * @version 1.0
 */
@WebServlet("/BrowseUniverses")
public class DisplaySuperheroesAffiliationServlet extends HttpServlet {

    private LogicalOperations logicalOperations = new LogicalOperations();
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
        logicalOperations.prepareDatabase();
        ArrayList<String> universes = new ArrayList();
        for (String universe: logicalOperations.getUniverses()){
            universes.add(universe);
        }
        request.setAttribute("universes", universes);
        request.getRequestDispatcher("/WEB-INF/BrowseUniversesPage.jsp").forward(request,response);
        response.sendRedirect("/WEB-INF/BrowseUniversesPage.jsp");
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
        return "Short description";
    }
}
