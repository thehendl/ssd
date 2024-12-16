import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.ArrayList;
import java.util.List;

import java.sql.Array;


public class MyContentHandler extends DefaultHandler {
    private StringBuilder outputXml;
    private int cinemaCount = 0;
    private int movieCount = 0;
    private float averageMpC = 0;
    private int postalCodeSum = 0;
    private int postalCodeCount = 0;
    private StringBuilder currentPostalCode = null; // postal code content
    private int[] yearArr = new int[131];
    private List<String> titleIds = new ArrayList<>(); // list to store title IDs


    private String curYear = "";
    private StringBuilder curTitle = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        outputXml = new StringBuilder();
        outputXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        outputXml.append("<cinemaStats>\n");
    }

    @Override
    public void endDocument() throws SAXException {

        outputXml.append("  <cinemaCount>").append(cinemaCount).append("</cinemaCount>\n");
        outputXml.append("  <movieCount>").append(movieCount).append("</movieCount>\n");
        outputXml.append("  <averageMpC>").append(averageMpC).append("</averageMpC>\n");
        /*for (int i = 0; i < yearArr.length; i++) {
            if (yearArr[i] != 0) {
                outputXml.append("  <moviesperyear>").append(yearArr[9]).append(yearArr[i]).append("</moviesperyear>\n");
            }
        }...... in progress*/
        double averagePostalCode = (postalCodeCount > 0) ? (double) postalCodeSum / postalCodeCount : 0;

        // Add the statistics to the output XML
        outputXml.append("  <averagePostalCode>").append(averagePostalCode).append("</averagePostalCode>\n");


        outputXml.append("  <titleIds>\n");
        for (String id : titleIds) {
            outputXml.append("    <id>").append(id).append("</id>\n");
        }
        outputXml.append("  </titleIds>\n");


        outputXml.append("</cinemaStats>");

    }

    @Override
    public void startElement(String uri, String localName, String elementName, Attributes atts) throws SAXException {


        if (elementName.equals("cinema")) {
            cinemaCount++;
        }
        if (elementName.equals("title")) {
            movieCount++;
        }
        if (cinemaCount > 0 && movieCount > 0) {
            averageMpC = movieCount / cinemaCount;
        }
        /*if (elementName.equals("year")) {

            int j = Integer.parseInt(atts.getValue("y"));
            for (int i = 1900; i < 2030; i++) {
                if (i == j) {
                    yearArr[j - 1900] += 1;

                }
            }
        } ......in progress*/
        if (elementName.equals("postal")) {
            currentPostalCode = new StringBuilder(); // Start capturing postal code
        }
        if ("title".equals(elementName)) {
            // Extract the "id" attribute from the <title> element
            String id = atts.getValue("id");
            if (id != null) {
                titleIds.add(id); // Add the ID to the list
            }
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (currentPostalCode != null) {
            currentPostalCode.append(new String(ch, start, length).trim()); // Capture content
        }
        /*/ Process year content
        if (currentYear != null) {
            currentYear += new String(ch, start, length).trim();
        }
        // Process title content
        if (currentTitle != null) {
            currentTitle.append(new String(ch, start, length).trim());
        }.......work in progress*/
    }

    @Override
    public void endElement(String uri, String localName, String elementName) throws SAXException {
        if ("postal".equals(elementName) && currentPostalCode != null) {
            try {
                int postalCode = Integer.parseInt(currentPostalCode.toString().trim());
                postalCodeSum += postalCode; // Add to sum
                postalCodeCount++; // Increment count
            } catch (NumberFormatException e) {
                System.err.println("Invalid postal code: " + currentPostalCode.toString());
            }
            currentPostalCode = null; // Reset postal code
        }
    }

    public String getOutputXml() {
        return outputXml.toString();
    }
}
