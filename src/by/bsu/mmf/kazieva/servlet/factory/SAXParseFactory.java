package by.bsu.mmf.kazieva.servlet.factory;

import by.bsu.mmf.kazieva.servlet.parsers.sax.SAXParser;


public class SAXParseFactory extends AbstractParserFactory<SAXParser> {
    @Override
    public SAXParser getInstance() {
        return new SAXParser();
    }
}
