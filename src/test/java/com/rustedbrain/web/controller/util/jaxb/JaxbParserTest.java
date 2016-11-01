package com.rustedbrain.web.controller.util.jaxb;

import com.rustedbrain.web.model.jaxb.table.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JaxbParserTest {

    private Parser parser;
    private File file;

    @Before
    public void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File("person.xml");
    }

    @Test
    public void testGetObject() throws Exception {
        Tables person = (Tables) parser.getObject(file, Tables.class);
        System.out.println(person);
    }

    @Test
    public void testSaveObject() throws Exception {
        Tables tables = new Tables();

        Table table1 = createTestTable("public",
                "user",
                Arrays.asList(
                        new Column("id", "integer", Arrays.asList(new ColumnConstraint("NOT NULL"), new ColumnConstraint("DEFAULT nextval('user_id_seq'::regclass)", "user_id_seq"))),
                        new Column("name", "character varying(64)", Collections.singletonList(new ColumnConstraint("NOT NULL"))),
                        new Column("surname", "character varying(64)")
                ),
                Collections.singletonList(
                        new TableConstraint("city_pkey", "PRIMARY KEY (id)")),
                Arrays.asList(
                        new ForeignKey("user_city_id_fkey", "city_id", "public", "city", "id", "ON UPDATE SET NULL\n" +
                                "\t\t\t\t\tON DELETE SET NULL"),
                        new ForeignKey("user_city_id_fkey", "city_id", "public", "city", "id", "ON UPDATE SET NULL\n" +
                                "\t\t\t\t\tON DELETE SET NULL")
                ),
                "pg_default",
                false,
                Collections.singletonList("postgres"));

        Table table2 = createTestTable("public",
                "city",
                Arrays.asList(
                        new Column("id", "integer", Arrays.asList(new ColumnConstraint("NOT NULL"), new ColumnConstraint("DEFAULT nextval('city_id_seq'::regclass)", "city_id_seq"))),
                        new Column("name", "character varying(64)", Collections.singletonList(new ColumnConstraint("NOT NULL")))
                ),
                Collections.singletonList(
                        new TableConstraint("city_pkey", "PRIMARY KEY (id)")),
                Arrays.asList(
                        new ForeignKey("user_city_id_fkey", "city_id", "public", "city", "id", "ON UPDATE SET NULL\n" +
                                "\t\t\t\t\tON DELETE SET NULL"),
                        new ForeignKey("user_city_id_fkey", "city_id", "public", "city", "id", "ON UPDATE SET NULL\n" +
                                "\t\t\t\t\tON DELETE SET NULL")
                ),
                "pg_default",
                true,
                Collections.singletonList("postgres"));

        tables.setTables(Arrays.asList(table1, table2));

        parser.saveObject(file, tables);
    }

    private Table createTestTable(String schema, String name, List<Column> columns, List<TableConstraint> tableConstraints, List<ForeignKey> foreignKeys, String namespace, boolean oids, List<String> owners) {
        Table table = new Table();
        table.setSchema(schema);
        table.setName(name);
        table.setColumns(columns);
        table.setTableConstraints(tableConstraints);
        table.setForeignKeys(foreignKeys);
        table.setNamespace(namespace);
        table.setOids(oids);
        table.setOwners(owners);
        return table;
    }

}
