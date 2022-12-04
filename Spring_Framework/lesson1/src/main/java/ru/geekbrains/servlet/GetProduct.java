package ru.geekbrains.servlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "GetProduct", urlPatterns = "/product")
public class GetProduct extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    private static final List<Product> products = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().printf("<html><body>");
        for (Product product : products) {
            resp.getWriter().printf("<h1>"+product.getId()+" "+product.getTitle()+" "+product.getCost()+"</h1>");
        }
        resp.getWriter().printf("</body></html>");
        resp.getWriter().close();
    }
    @Override
    public void init() throws ServletException {
        logger.debug("Init");
        products.add(new Product(1, "milk", 35.60));
        products.add(new Product(1, "milk", 35.60));
        products.add(new Product(2, "cheese", 50));
        products.add(new Product(3, "sausage", 60));
        products.add(new Product(4, "ketchup", 70));
        products.add(new Product(5, "mayonnaise", 100));
        products.add(new Product(6, "eggs", 43.90));
        products.add(new Product(7, "bread", 30.90));
        products.add(new Product(8, "chips", 20.80));
        products.add(new Product(9, "potato", 270.80));
        products.add(new Product(10, "apples", 260.80));
    }
}