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
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowIndexServlet
 */
@WebServlet("/follows/index")
public class FollowsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsIndexServlet() {
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
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        List<Follow> follows = em.createNamedQuery("getMyAllFollows",Follow.class)
                .setParameter("employee",login_employee)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long follows_count = (long)em.createNamedQuery("getMyFollowsCount",Long.class)
                .setParameter("employee",login_employee)
                .getSingleResult();

        List<Report> reports = em.createNamedQuery("getMyAllReports",Report.class)
                .setParameter("employee",login_employee)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();




        request.setAttribute("follows", follows);
        request.setAttribute("follows_count", follows_count);
        request.setAttribute("reports", reports);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/index.jsp");
        rd.forward(request, response);
    }

}
