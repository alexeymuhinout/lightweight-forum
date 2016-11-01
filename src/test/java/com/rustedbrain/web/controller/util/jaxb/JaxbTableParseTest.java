package com.rustedbrain.web.controller.util.jaxb;

import com.rustedbrain.web.model.jaxb.table.Tables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.UnmarshalException;
import java.nio.file.Paths;

public class JaxbTableParseTest {

    private final String tablesXmlFileName = "tables.xml";
    private final String tablesWrongXmlFileName = "tab.xml";
    private final String tablesXmlPath = "C:\\Work\\IdeaProjects\\lightweight-forum\\resources\\";
    private Parser parser;

    @Before
    public void setUp() throws Exception {
        this.parser = new JaxbParser();
    }

    @Test(expected = UnmarshalException.class)
    public void testWrongTablesXmlMapping() throws Exception {
        Tables tables = (Tables) parser.getObject(Paths.get(tablesXmlPath + tablesWrongXmlFileName).toFile(), Tables.class);
    }

    @Test
    public void testTablesXmlMapping() throws Exception {
        Tables tables = (Tables) parser.getObject(Paths.get(tablesXmlPath + tablesXmlFileName).toFile(), Tables.class);
        Assert.assertNotNull(tables);
    }
}
