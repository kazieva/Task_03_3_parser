package by.bsu.mmf.kazieva.servlet.parsers.sax;

import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;
//import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SAXParser extends AbstractParser {
 //   private static final Logger LOGGER = Logger.getLogger(SAXParser.class);

    @Override
    public List<TouristVoucher> parse(String fileName, int start, int amount) throws SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        TouristVoucherHandler handler = new TouristVoucherHandler();

        reader.setContentHandler(handler);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        InputSource inputSource = new InputSource(inputStream);
        try {
            reader.parse(inputSource);
        } catch (IOException e) {
 //           LOGGER.error(e);
        }
        return getTouristVoucherListToPage(start, amount, handler.getTouristVoucherList());
    }
}
