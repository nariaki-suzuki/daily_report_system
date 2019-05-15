package controllers.follows;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsShowServlet
 */
@WebServlet("/follows/show")
public class FollowsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        List<Report> reports = em.createNamedQuery("getMyAllReports",Report.class)
                .setParameter("employee",e)
                .setMaxResults(15)
                .getResultList();

        request.setAttribute("reports", reports);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/show.jsp");
        rd.forward(request, response);
    }

}
