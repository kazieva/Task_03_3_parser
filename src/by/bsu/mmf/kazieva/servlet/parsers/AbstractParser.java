package by.bsu.mmf.kazieva.servlet.parsers;

import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParser {
    private static int amountOfPages;

    public abstract List<TouristVoucher> parse(String fileName, int start, int amount) throws SAXException, XMLStreamException, IOException;

    public List<TouristVoucher> getTouristVoucherListToPage(int start, int amount, List<TouristVoucher> touristVoucherList) {
        if (touristVoucherList.size() % amount == 0) {
            amountOfPages = touristVoucherList.size() / amount;
        } else {
            amountOfPages = touristVoucherList.size() /amount + 1;
        }
        List<TouristVoucher> currentVoucher = new ArrayList<>();
        for (int i = start; i < amount + start; i++) {
            if (i < touristVoucherList.size()) {
                currentVoucher.add(touristVoucherList.get(i));
            } else {
                return currentVoucher;
            }
        }
        return currentVoucher;
    }

    public static int getAmountOfPages() {
        return amountOfPages;
    }
}
