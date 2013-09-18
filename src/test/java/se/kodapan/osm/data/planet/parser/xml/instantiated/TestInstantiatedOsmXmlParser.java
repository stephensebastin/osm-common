package se.kodapan.osm.data.planet.parser.xml.instantiated;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * @author kalle
 * @since 2013-05-01 16:25
 */
public class TestInstantiatedOsmXmlParser extends TestCase {

  public void testBadData() throws Exception {

    InstantiatedOsmXmlParser parser = new InstantiatedOsmXmlParser();

    try {
      parser.parse(new StringReader("<foo>bar"));
      fail("Should throw an exception due to bad input data!");
    } catch (Exception e) {
      // all good
    }

  }

  public void testFjallbacka() throws Exception {

    InstantiatedOsmXmlParser parser = new InstantiatedOsmXmlParser();

    parser.parse(new InputStreamReader(new FileInputStream("src/test/resources/fjallbacka.osm.xml"), "UTF8"));

    assertEquals(36393, parser.getRoot().getNodes().size());
    assertEquals(4103, parser.getRoot().getWays().size());
    assertEquals(87, parser.getRoot().getRelations().size());

    System.currentTimeMillis();
  }

}