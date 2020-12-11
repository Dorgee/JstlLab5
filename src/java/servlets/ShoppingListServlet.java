
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String user = (String) session.getAttribute("username");
        if (action != null) {
            session.invalidate();
            session = request.getSession();
            String m = "logout sucessful";
            request.setAttribute("m", m);
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
            if (user == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");

        if (action.equals("register")) {
            String username = request.getParameter("username");
            if (username != null || username != "") {
                session.setAttribute("username", username);
                response.sendRedirect("shoppingList.jsp");
            }
        } else if (action.equals("add")) {
            String item = (String) request.getParameter("item");
            // if there is no array list make one
            if(!item.equals("")){
            if (items == null ) {
                items = new ArrayList();
            }
            items.add(item);
            session.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String delete = request.getParameter("delete");
            items.remove(delete);
            session.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }
}
}