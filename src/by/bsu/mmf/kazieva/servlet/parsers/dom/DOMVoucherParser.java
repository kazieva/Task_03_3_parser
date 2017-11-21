package by.bsu.mmf.kazieva.servlet.parsers.dom;

import by.bsu.mmf.kazieva.servlet.entity.voucher.Cost;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Hotel;
import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Type;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DOMVoucherParser extends AbstractParser {
  //  private static final Logger LOGGER = Logger.getLogger(DOMVoucherParser.class);

    @Override
    public List<TouristVoucher> parse(String fileName, int start, int amount) throws IOException, SAXException {
        DOMParser domParser = new DOMParser();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        InputSource inputSource = new InputSource(inputStream);
        domParser.parse(inputSource);

        Document document = domParser.getDocument();
        Element root = document.getDocumentElement();

        List<TouristVoucher> voucherDom = new ArrayList<>();
        NodeList voucherNodes = root.getElementsByTagName("tourist_voucher");

        DOMVoucherParser.process(voucherDom, voucherNodes);
        return getTouristVoucherListToPage(start, amount, voucherDom);
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nList = element.getElementsByTagName(childName);
        return (Element) nList.item(0);
    }

    public static void process(List<TouristVoucher> voucherList, NodeList voucherNode) {
        TouristVoucher voucher;
        Hotel hotel;
        Cost cost;
    //    LOGGER.debug("Parsing started");
        for (int i = 0; i < voucherNode.getLength(); i++) {
            voucher = new TouristVoucher();
            hotel = new Hotel();
            cost = new Cost();
            Element voucherElement = (Element) voucherNode.item(i);

            voucher.setCountry(voucherElement.getAttribute("country"));
            voucher.setTransport(voucherElement.getAttribute("transport"));

            voucher.setType(Type.valueOf(getSingleChild(voucherElement, "Type").getTextContent().trim().toUpperCase()));
            voucher.setNumberDays(Integer.parseInt(getSingleChild(voucherElement, "NumberDays").getTextContent().trim()));
            voucher.setNumberNights(Integer.parseInt(getSingleChild(voucherElement, "NumberNights").getTextContent().trim()));

            hotel.setName(getSingleChild(voucherElement, "Hotel").getAttribute("name"));
            hotel.setFood(getSingleChild(voucherElement, "Hotel").getAttribute("food"));
            String tv = getSingleChild(voucherElement, "Hotel").getAttribute("TV");
            String wifi = getSingleChild(voucherElement, "Hotel").getAttribute("WiFi");
            String airCond = getSingleChild(voucherElement, "Hotel").getAttribute("AirConditioning");
            if (tv != null) {
                hotel.setTV(tv);
            }
            if (wifi != null) {
                hotel.setWiFi(wifi);
            }
            if (airCond != null) {
                hotel.setAirConditioning(airCond);
            }

            hotel.setAmountOfStars(Integer.parseInt(getSingleChild(voucherElement, "AmountOfStars").getTextContent().trim()));
            hotel.setAmountOfRoom(Integer.parseInt(getSingleChild(voucherElement, "AmountOfRoom").getTextContent().trim()));
            voucher.setHotel(hotel);

            cost.setInclude(getSingleChild(voucherElement, "Cost").getAttribute("include"));
            cost.setPrice(getSingleChild(voucherElement, "price").getTextContent().trim() + "$");
            voucher.setCost(cost);

            voucherList.add(voucher);
        }
    }
}
