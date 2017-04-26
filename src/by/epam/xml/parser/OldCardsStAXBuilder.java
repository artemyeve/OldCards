package by.epam.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


import by.epam.xml.entity.OldCard;
import by.epam.xml.entity.OldCardEnum;
import by.epam.xml.factory.AbstractOldCardsBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OldCardsStAXBuilder extends AbstractOldCardsBuilder {
    private static Logger logger = LogManager.getLogger(OldCardsStAXBuilder.class);
    private Set<OldCard> cards = new HashSet<OldCard>();
    private final String UNKNOWN_AUTHOR = "unknown";
    private XMLInputFactory inputFactory;

    public OldCardsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<OldCard> getOldCards() {
        return cards;
    }

    @Override
    public void buildSetOldCards(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName().toString();
                    if (name.toString().equals(OldCardEnum.CARD.getValue())) {
                        OldCard st = buildCard(reader);
                        cards.add(st);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            logger.log(Level.ERROR,"StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            logger.log(Level.ERROR,"File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.log(Level.ERROR,"Impossible close file " + fileName + " : " + e);
            }
        }

    }


    private OldCard buildCard(XMLStreamReader reader) throws XMLStreamException {
        OldCard card = new OldCard();
        String author = reader.getAttributeValue(null, OldCardEnum.AUTHOR.getValue());
        if(author == null) {
            card.setAuthor(UNKNOWN_AUTHOR);
        }
        else {
            card.setAuthor(author);
        }
        card.setSend(Boolean.valueOf(reader.getAttributeValue(null, OldCardEnum.SEND.getValue())));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OldCardEnum.valueOf(name.toUpperCase())) {
                        case THEMA:
                            card.setThema(getXMLText(reader));
                            break;
                        case COUNTRY:
                            card.setCountry(getXMLText(reader));
                            break;
                        case YEAR:
                            card.setYear(Integer.parseInt(getXMLText(reader)));
                            break;
                        case VALUABLE:
                            card.setValuable(getXMLText(reader));
                            break;
                        case TYPE:
                            card.setType(getXMLText(reader));
                            break;
                    } break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OldCardEnum.valueOf(name.toUpperCase()) == OldCardEnum.CARD) {
                        return card;
                    }
                    break;
            }
        } throw new XMLStreamException("Unknown element");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
