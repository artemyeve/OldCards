package test.by.epam.xml.builder;


import by.epam.xml.factory.AbstractOldCardsBuilder;
import by.epam.xml.factory.OldCardsBuilderFactory;
import org.junit.BeforeClass;
import org.junit.Test;


public class OldCardsSAXBuilderTest {

    private static OldCardsBuilderFactory factory;
    private static AbstractOldCardsBuilder builder;

    @BeforeClass
    public static void initBuilder() {
        factory = new OldCardsBuilderFactory();
        builder = factory.createCardBuilder("SAX");
        builder.buildSetOldCards("data/cards.xml");
    }
    @Test

    public void buildSetOldCardsTest() {
        System.out.println(builder.getOldCards());

    }

}
