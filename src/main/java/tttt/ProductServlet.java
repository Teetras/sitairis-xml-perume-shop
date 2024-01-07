package tttt;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private List<Product> products;

    @Override
    public void init() throws ServletException {
        super.init();
        // Чтение XML файла и инициализация списка товаров
        products = readProductsFromXML();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Обработка GET запроса
        String action = request.getParameter("action");
        if (action == null) {
            // Отображение списка товаров
            request.setAttribute("products", products);
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        } else if (action.equals("report")) {
            // Отображение отчёта о стоимости
            double totalCost = calculateTotalCost();
            request.setAttribute("products", products);
            request.setAttribute("totalCost", totalCost);
            request.getRequestDispatcher("/report.jsp").forward(request, response);
        }
    }

    private List<Product> readProductsFromXML() {
        List<Product> productList = new ArrayList<>();
        try {
            // Путь к вашему XML файлу
            String filePath = "/path/to/products.xml";

            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(filePath);
            Element rootElement = document.getRootElement();
            List<Element> productElements = rootElement.getChildren("product");

            for (Element productElement : productElements) {
                String name = productElement.getChildText("name");
                String description = productElement.getChildText("description");
                double price = Double.parseDouble(productElement.getChildText("price"));
                String group = productElement.getChildText("group");

                Product product = new Product(name, description, price, group);
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    private double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }
}