package by.bsu.mmf.kazieva.servlet.factory;

import by.bsu.mmf.kazieva.servlet.Controller;
import by.bsu.mmf.kazieva.servlet.entity.ParserEnum;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;

import javax.servlet.http.HttpServletRequest;


public class ActionFactory {

    public AbstractParser defineParser(HttpServletRequest request) {
        AbstractParser current;
        String action = request.getParameter("action");
        if (request.getParameter("page") == null) {
            Controller.setAction(action);
        } else {
            action = Controller.getAction();
        }
        ParserEnum currentEnum = ParserEnum.valueOf(action.toUpperCase());
        current = currentEnum.getCommand();
        return current;
    }
}
