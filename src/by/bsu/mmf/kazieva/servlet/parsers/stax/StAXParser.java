package by.bsu.mmf.kazieva.servlet.parsers.stax;

import by.bsu.mmf.kazieva.servlet.entity.NameOfTagEnum;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Cost;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Hotel;
import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Type;
import by.bsu.mmf.kazieva.servlet.parsers.AbstractParser;
//import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StAXParser extends AbstractParser {
//    private static final Logger LOGGER = Logger.getLogger(StAXParser.class);

    @Override
    public List<TouristVoucher> parse(String fileName, int start, int amount) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        XMLStreamReader readerStAX = inputFactory.createXMLStreamReader(inputStream);
        return getTouristVoucherListToPage(start, amount, StAXParser.process(readerStAX));
    }

    public static List<TouristVoucher> process(XMLStreamReader reader) throws XMLStreamException {
//        LOGGER.debug("Parsing started");
        List<TouristVoucher> voucherList = new ArrayList<>();
        TouristVoucher voucher = null;
        Hotel hotel = null;
        Cost cost = null;
        NameOfTagEnum tagName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    tagName = NameOfTagEnum.valueOf(reader.getLocalName().toUpperCase());
                    switch (tagName) {
                        case TOURIST_VOUCHER: {
                            voucher = new TouristVoucher();
                            String country = reader.getAttributeValue(null, "country");
                            String transport = reader.getAttributeValue(null, "transport");
                            voucher.setCountry(country);
                            voucher.setTransport(transport);
                            break;
                        }
                        case HOTEL: {
                            hotel = new Hotel();
                            String name = reader.getAttributeValue(null, "name");
                            String food = reader.getAttributeValue(null, "food");
                            hotel.setName(name);
                            hotel.setFood(food);
                            String tv = reader.getAttributeValue(null, "TV");
                            String wifi = reader.getAttributeValue(null, "WiFi");
                            String airCond = reader.getAttributeValue(null, "AirConditioning");
                            if (tv != null) {
                                hotel.setTV(tv);
                            }
                            if (wifi != null) {
                                hotel.setWiFi(wifi);
                            }
                            if (airCond != null) {
                                hotel.setAirConditioning(airCond);
                            }
                            break;
                        }
                        case COST: {
                            cost = new Cost();
                            String include = reader.getAttributeValue(null, "include");
                            cost.setInclude(include);
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (tagName) {
                        case TYPE: {
                            voucher.setType(Type.valueOf(text.toUpperCase()));
                            break;
                        }
                        case NUMBERDAYS: {
                            voucher.setNumberDays(Integer.parseInt(text));
                            break;
                        }
                        case NUMBERNIGHTS: {
                            voucher.setNumberNights(Integer.parseInt(text));
                            break;
                        }
                        case AMOUNTOFSTARS: {
                            hotel.setAmountOfStars(Integer.parseInt(text));
                            break;
                        }
                        case AMOUNTOFROOM: {
                            hotel.setAmountOfRoom(Integer.parseInt(text));
                            break;
                        }
                        case PRICE: {
                            cost.setPrice(text + "$");
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    tagName = NameOfTagEnum.valueOf(reader.getLocalName().toUpperCase());
                    switch (tagName) {
                        case TOURIST_VOUCHER: {
                            voucher.setHotel(hotel);
                            voucher.setCost(cost);
                            voucherList.add(voucher);
                            break;
                        }
                    }
                }
            }
        }
//        LOGGER.debug("Parsing ended");
        return voucherList;
    }
}
