package by.epam.xml.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import by.epam.xml.entity.OldCard;
import by.epam.xml.entity.OldCardEnum;
import by.epam.xml.factory.AbstractOldCardsBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OldCardsDOMBuilder extends AbstractOldCardsBuilder {
    private static Logger logger = LogManager.getLogger(OldCardsDOMBuilder.class);
    private Set<OldCard> cards;
    private final String UNKNOWN_AUTHOR = "unknown";
    private DocumentBuilder docBuilder;

    public OldCardsDOMBuilder() {
        cards = new HashSet<OldCard>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR,"Parser configuration exception: " + e);
        }
    }

    public Set<OldCard> getOldCards() {
        return cards;
    }

    @Override
       public void buildSetOldCards(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList cardsList = root.getElementsByTagName(OldCardEnum.CARD.getValue());
            for (int i = 0; i < cardsList.getLength(); i++) {
                Element cardElement = (Element) cardsList.item(i);
                OldCard card = buildCard(cardElement);
                cards.add(card);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR,"File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.log(Level.ERROR,"Parsing failure: " + e);
        }
    }

    private OldCard buildCard(Element cardElement) {
        OldCard card = new OldCard();
        String name = cardElement.getAttribute(OldCardEnum.AUTHOR.getValue());
        if(name.isEmpty()){
            card.setAuthor(UNKNOWN_AUTHOR);
        }
        else{
            card.setAuthor(name);
        }
        card.setSend(Boolean.valueOf(cardElement.getAttribute(OldCardEnum.SEND.getValue())));
        Integer year = Integer.parseInt(getElementTextContent( cardElement,OldCardEnum.YEAR.getValue()));
        card.setYear(year);
        card.setThema(getElementTextContent(cardElement, OldCardEnum.THEMA.getValue()));
        card.setCountry(getElementTextContent(cardElement, OldCardEnum.COUNTRY.getValue()));
        card.setType(getElementTextContent(cardElement, OldCardEnum.TYPE.getValue()));
        card.setValuable(getElementTextContent(cardElement, OldCardEnum.VALUABLE.getValue()));
        return card;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
