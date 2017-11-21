package by.bsu.mmf.kazieva.servlet.parsers.sax;

import by.bsu.mmf.kazieva.servlet.entity.NameOfTagEnum;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Cost;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Hotel;
import by.bsu.mmf.kazieva.servlet.entity.voucher.TouristVoucher;
import by.bsu.mmf.kazieva.servlet.entity.voucher.Type;
//import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class TouristVoucherHandler extends DefaultHandler {
 //   private static final Logger LOGGER = Logger.getLogger(TouristVoucherHandler.class);

    private List<TouristVoucher> touristVoucherList = new ArrayList<>();
    private TouristVoucher touristVoucher;
    private StringBuilder text;

    public List<TouristVoucher> getTouristVoucherList() {
        return touristVoucherList;
    }

    @Override
    public void startDocument() {
//        LOGGER.debug("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();
        if (qName.equals("tourist_voucher")) {
            touristVoucher = new TouristVoucher();
            touristVoucher.setCountry(attributes.getValue("country"));
            touristVoucher.setTransport(attributes.getValue("transport"));
        }
        if (qName.equals("Hotel")) {
            Hotel hotel = new Hotel();
            hotel.setName(attributes.getValue("name"));
            hotel.setFood(attributes.getValue("food"));
            if (attributes.getValue("TV") != null) {
                hotel.setTV(attributes.getValue("TV"));
            }
            if (attributes.getValue("AirConditioning") != null) {
                hotel.setAirConditioning(attributes.getValue("AirConditioning"));
            }
            if (attributes.getValue("WiFi") != null) {
                hotel.setWiFi(attributes.getValue("WiFi"));
            }
            touristVoucher.setHotel(hotel);
        }
        if (qName.equals("Cost")) {
            Cost cost = new Cost();
            cost.setInclude(attributes.getValue("include"));
            touristVoucher.setCost(cost);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        NameOfTagEnum nameOfTag = NameOfTagEnum.valueOf(qName.toUpperCase().replace(":", "_"));
            switch (nameOfTag) {
                case TYPE: {
                    Type type = Type.valueOf(text.toString().toUpperCase());
                    touristVoucher.setType(type);
                    break;
                }
                case NUMBERDAYS: {
                    touristVoucher.setNumberDays(Integer.parseInt(text.toString()));
                    break;
                }
                case NUMBERNIGHTS: {
                    touristVoucher.setNumberNights(Integer.parseInt(text.toString()));
                    break;
                }
                case HOTEL: {
                    break;
                }
                case AMOUNTOFSTARS: {
                    touristVoucher.getHotel().setAmountOfStars(Integer.parseInt(text.toString()));
                    break;
                }
                case AMOUNTOFROOM: {
                    touristVoucher.getHotel().setAmountOfRoom(Integer.parseInt(text.toString()));
                    break;
                }
                case COST: {
                    break;
                }
                case PRICE: {
                    touristVoucher.getCost().setPrice(text.toString() + "$");
                    break;
                }
                case TOURIST_VOUCHER: {
                    touristVoucherList.add(touristVoucher);
                    break;
                }
                case TOURIST_VOUCHERS: {
                    break;
                }
 /*               default: {
                    LOGGER.debug("No such tags!");
                }*/

            }
    }

    @Override
    public void endDocument() {
   /*     LOGGER.debug("\nParsing ended");*/
    }
}
