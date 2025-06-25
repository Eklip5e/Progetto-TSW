package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RigaCarrello;
import model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet("/pagamento")
public class PagamentoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Utente userSession =  (Utente) request.getSession().getAttribute("utente");
        List<RigaCarrello> righe = (List<RigaCarrello>) session.getAttribute("userCart");

        if (righe == null || righe.isEmpty()) {
            response.sendRedirect("carrello.jsp?empty=true");
            return;
        }

        session.setAttribute("userCart", righe);
        request.setAttribute("userCart", righe);

        request.getRequestDispatcher("pagamento.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // In questo modo gestisci sia GET che POST con lo stesso codice
    }
}
