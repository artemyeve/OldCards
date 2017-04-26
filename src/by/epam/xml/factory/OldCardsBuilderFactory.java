package by.epam.xml.factory;


import by.epam.xml.parser.OldCardsDOMBuilder;
import by.epam.xml.parser.OldCardsSAXBuilder;
import by.epam.xml.parser.OldCardsStAXBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OldCardsBuilderFactory {

    private static Logger logger = LogManager.getLogger(OldCardsBuilderFactory.class);

    private enum TypeParser {SAX, STAX, DOM}

    public AbstractOldCardsBuilder createCardBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                logger.log(Level.INFO,"Chosen DOM builder");
                return new OldCardsDOMBuilder();
            case STAX:
                logger.log(Level.INFO,"Chosen StAX builder");
                return new OldCardsStAXBuilder();
            case SAX:
                logger.log(Level.INFO,"Chosen SAX builder");
                return new OldCardsSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }

    }
}
