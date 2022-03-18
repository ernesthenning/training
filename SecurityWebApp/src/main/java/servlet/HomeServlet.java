package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ({"/", "/index"})
public class HomeServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    public HomeServlet() {
        super();
    }
    
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
