import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/SocketQuoteServlet")
public class SocketQuoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String socketType = request.getParameter("socketType");
        String quantityStr = request.getParameter("quantity");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Input validation
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            quantity = -1;
        }

        // Business logic to calculate quote (basic logic here, can be expanded)
        double pricePerUnit;
        switch (socketType) {
            case "TypeA":
                pricePerUnit = 13.8;
                break;
            case "TypeB":
                pricePerUnit = 21.0;
                break;
            case "TypeC":
                pricePerUnit = 42.0;
                break;
            default:
                pricePerUnit = 0.0;
        }
        double totalPrice = pricePerUnit * quantity;

        // Prepare response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (quantity < 0) {
            out.println("<h3>Invalid quantity provided!</h3>");
        } else {
            out.println("<h2>Quote for " + name + ":</h2>");
            out.println("<p>Socket Type: " + socketType + "</p>");
            out.println("<p>Quantity: " + quantity + "</p>");
            out.println("<p>Total Price: $" + totalPrice + "</p>");
        }
        out.println("</body></html>");
    }
}
