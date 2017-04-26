package test.by.epam.xml.validator;

import by.epam.xml.validator.XsdValidator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XsdValidatorTest {

    private static String fileName;
    private static String schemaName;
    private static XsdValidator validator;

    @BeforeClass
    public static void init() {

        fileName = "data/cards.xml";
        schemaName = "data/cards.xsd";
        validator = new XsdValidator();
    }
    @AfterClass
    public static void unInit() {

        fileName = null;
        schemaName = null;

    }
    @Test
    public void testValidate() throws IOException, SAXException {

        Assert.assertTrue(validator.validate(fileName, schemaName));
    }

}
