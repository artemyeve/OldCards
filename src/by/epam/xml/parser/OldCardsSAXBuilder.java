package by.epam.xml.parser;

import java.io.IOException;
import java.util.Set;

import by.epam.xml.entity.OldCard;
import by.epam.xml.factory.AbstractOldCardsBuilder;
import by.epam.xml.handler.OldCardHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class OldCardsSAXBuilder extends AbstractOldCardsBuilder {
    private static Logger logger = LogManager.getLogger(OldCardsSAXBuilder.class);
    private Set<OldCard> cards;
    private OldCardHandler handler;
    private XMLReader reader;
    
    public OldCardsSAXBuilder() {
        handler = new OldCardHandler();
        
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.log(Level.ERROR,"SAX builder error: " + e.getMessage());
        }
    }

    public Set<OldCard> getOldCards() {

        return cards;
    }
    @Override
    public void buildSetOldCards(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.log(Level.ERROR,"SAX builder error: " + e);
        } catch (IOException e) {
            logger.log(Level.ERROR,"IO stream error: " + e);
        }
        
        cards = handler.getOldCards();
    }
}
