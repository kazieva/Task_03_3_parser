package by.bsu.mmf.kazieva.servlet.entity;

import by.bsu.mmf.kazieva.servlet.factory.AbstractParserFactory;
import by.bsu.mmf.kazieva.servlet.factory.DOMParserFactory;
import by.bsu.mmf.kazieva.servlet.factory.SAXParseFactory;
import by.bsu.mmf.kazieva.servlet.factory.StAXParserFactory;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;

public enum ParserEnum {
    SAX {
        {
            factory = new SAXParseFactory();
        }
    },
    STAX {
        {
            factory = new StAXParserFactory();
        }
    },
    DOM {
        {
            factory = new DOMParserFactory();
        }
    };

    AbstractParserFactory factory;

    public AbstractParser getCommand() {
        return factory.getInstance();
    }
}
