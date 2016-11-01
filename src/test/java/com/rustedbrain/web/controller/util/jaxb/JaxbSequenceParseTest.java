package com.rustedbrain.web.controller.util.jaxb;

import com.rustedbrain.web.model.jaxb.sequence.Sequence;
import com.rustedbrain.web.model.jaxb.sequence.Sequences;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class JaxbSequenceParseTest {

    private File file;
    private Parser parser;

    @Before
    public void setUp() throws Exception {
        this.file = Paths.get("sequences.xml").toFile();
        this.parser = new JaxbParser();
    }

    @Test
    public void testSaveSequenceToFile() throws Exception {
        Sequences sequences = new Sequences();
        Sequence sequence = createTestSequence("public", "admin_id_seq", 1, 1, 1, 9223372036854775807L, 1, Collections.singletonList("postgres"));
        sequences.setSequences(Collections.singletonList(sequence));
        parser.saveObject(file, sequences);
    }

    @Test
    public void testGetSequenceFromFile() throws Exception {


    }

    @After
    public void tearDown() throws Exception {


    }

    private Sequence createTestSequence(String namespace, String name, int incrementValue, int startValue, int minValue, long maxValue,
                                        int cacheValue, List<String> owners) {
        Sequence sequence = new Sequence();

        sequence.setSchema(namespace);
        sequence.setName(name);
        sequence.setIncrementByValue(incrementValue);
        sequence.setStartValue(startValue);
        sequence.setMinValue(minValue);
        sequence.setMaxValue(maxValue);
        sequence.setCacheValue(cacheValue);
        sequence.setOwners(owners);

        return sequence;
    }
}
