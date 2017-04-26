package by.epam.xml.handler;

import by.epam.xml.entity.OldCard;
import by.epam.xml.entity.OldCardEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


public class OldCardHandler extends DefaultHandler {

    private Set<OldCard> cards;

    private OldCard current = null;
    private OldCardEnum currentEnum = null;
    private EnumSet<OldCardEnum> withText;
    private final String UNKNOWN_AUTHOR = "unknown";


    public OldCardHandler() {
        cards = new HashSet<OldCard>();
        withText = EnumSet.range(OldCardEnum.THEMA, OldCardEnum.TYPE);
    }

    public Set<OldCard> getOldCards() {
        return cards;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (OldCardEnum.CARD.getValue().equals(localName)) {
            current = new OldCard();
            current.setSend(Boolean.valueOf(attrs.getValue(1)));
            if (attrs.getLength() == 3) {
                current.setAuthor(attrs.getValue(2));
            } else {
                current.setAuthor(UNKNOWN_AUTHOR);
            }
        } else {
            OldCardEnum temp = OldCardEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (OldCardEnum.CARD.getValue().equals(localName)) {
            cards.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case THEMA:
                    current.setThema(s);
                    break;
                case YEAR:
                    current.setYear(Integer.parseInt(s));
                    break;
                case TYPE:
                    current.setType(s);
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                case VALUABLE:
                    current.setValuable(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}   
