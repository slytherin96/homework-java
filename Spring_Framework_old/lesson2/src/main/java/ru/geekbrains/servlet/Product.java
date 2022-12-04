package ru.geekbrains.servlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "Product", urlPatterns = "/product")
public class Product extends HttpServlet {
    int id;
    String title;
    double cost;
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    private void setProducts(int id, String title, double cost, HttpServletResponse resp) throws IOException {
        resp.getWriter().printf("<h1>"+id + " "+title+" "+cost+"</h1>");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("New GET request");
        setProducts(1, "milk", 35.60, resp);
        setProducts(2, "cheese", 50, resp);
        setProducts(3, "sausage", 60, resp);
        setProducts(4, "ketchup", 70, resp);
        setProducts(5, "mayonnaise", 100, resp);
        setProducts(6, "eggs", 43.90, resp);
        setProducts(7, "bread", 30.90, resp);
        setProducts(8, "chips", 20.80, resp);
        setProducts(9, "potato", 270.80, resp);
        setProducts(10, "apples", 260.80, resp);


    }

}