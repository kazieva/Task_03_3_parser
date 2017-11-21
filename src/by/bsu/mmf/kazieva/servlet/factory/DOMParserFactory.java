package by.bsu.mmf.kazieva.servlet.factory;

import by.bsu.mmf.kazieva.servlet.parsers.dom.DOMVoucherParser;

public class DOMParserFactory extends AbstractParserFactory<DOMVoucherParser> {
    @Override
    public DOMVoucherParser getInstance() {
        return new DOMVoucherParser();
    }
}
