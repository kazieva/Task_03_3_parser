package by.bsu.mmf.kazieva.servlet.factory;

import by.bsu.mmf.kazieva.servlet.parsers.stax.StAXParser;


public class StAXParserFactory extends AbstractParserFactory<StAXParser>{
    @Override
    public StAXParser getInstance() {
        return new StAXParser();
    }
}
