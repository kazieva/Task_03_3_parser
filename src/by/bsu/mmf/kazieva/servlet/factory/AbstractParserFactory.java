package by.bsu.mmf.kazieva.servlet.factory;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;

public abstract class AbstractParserFactory<T extends AbstractParser> {
    public abstract T getInstance();
}