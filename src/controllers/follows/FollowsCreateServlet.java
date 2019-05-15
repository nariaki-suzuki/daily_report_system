package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsCreateServlet
 */
@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //String _token = (String)request.getParameter("_token");
        //if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();
            Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

            f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));


            f.setFollow(e);



            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", f.getFollow().getName() + "さんをフォローしました");

            response.sendRedirect(request.getContextPath() + "/follows/index");
        //}
    }

}
