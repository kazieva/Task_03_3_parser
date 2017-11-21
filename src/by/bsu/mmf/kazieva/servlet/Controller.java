package by.bsu.mmf.kazieva.servlet;

import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import by.bsu.mmf.kazieva.servlet.factory.ActionFactory;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


//@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final String PATH_PAGE_INDEX = "/jsp/main.jsp";
//    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final String FILE_NAME = "xml/vouchers.xml";
    private static final int FIRST_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 4;
    private static String action;

    public static String getAction() {
        return action;
    }

    public static void setAction(String action) {
        Controller.action = action;
    }

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String filename = getInitParameter("init_log4j");
        if (filename != null) {
      //      PropertyConfigurator.configure(prefix + filename);
        }
//        LOGGER.info("Logger was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = FIRST_PAGE;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ActionFactory client = new ActionFactory();
        AbstractParser parser = client.defineParser(request);
        try {
            List<TouristVoucher> touristVouchers = parser.parse(FILE_NAME, (page - 1) * RECORDS_PER_PAGE,
                    RECORDS_PER_PAGE);
            request.setAttribute("amountOfPages", AbstractParser.getAmountOfPages());
            request.setAttribute("touristVouchers", touristVouchers);
        } catch (SAXException | XMLStreamException e) {
  //          LOGGER.error(e);
        }

        request.setAttribute("currentPage", page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PATH_PAGE_INDEX);
//      вызов страницы ответа на запрос
        dispatcher.forward(request, response);
    }

}
