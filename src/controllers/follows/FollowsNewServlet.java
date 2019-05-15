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
import utils.DBUtil;

/**
 * Servlet implementation class FollowsNewServlet
 */
@WebServlet("/follows/new")
public class FollowsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        EntityManager em = DBUtil.createEntityManager();




        List<Employee> employees = em.createNamedQuery("getAllEmployees",Employee.class)
                                        .setMaxResults(15)
                                        .getResultList();

        List<Follow> follows = em.createNamedQuery("getMyAllFollows",Follow.class)
                                 .setParameter("employee", login_employee)
                                 .getResultList();

        em.close();

        request.setAttribute("employees",employees);
        request.setAttribute("follows",follows);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/new.jsp");
        rd.forward(request, response);


    }

}
