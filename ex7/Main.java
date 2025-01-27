import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.FileWriter;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            //load ex7.xml into memory
            File inputFile = new File("ex7.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            //normalize
            doc.getDocumentElement().normalize();

            //statistics
            NodeList movies = doc.getElementsByTagName("title");
            int movieCount = movies.getLength();
            int earliestYear = Integer.MAX_VALUE;
            int latestYear = Integer.MIN_VALUE;

            for (int i = 0; i < movieCount; i++) {
                Element movie = (Element) movies.item(i);
                Element yearElement = (Element) movie.getElementsByTagName("date").item(0);
                int year = Integer.parseInt(yearElement.getTextContent().trim());
                if (year < earliestYear) {
                    earliestYear = year;
                }
                if (year > latestYear) {
                    latestYear = year;
                }
            }

            //add statistics to doc
            Element statistics = doc.createElement("statistics");
            Element releases = doc.createElement("releases");

            Element totalMovies = doc.createElement("totalMovies");
            totalMovies.setTextContent(String.valueOf(movieCount));

            Element earliestRelease = doc.createElement("earliestReleaseYear");
            earliestRelease.setTextContent(String.valueOf(earliestYear));

            Element latestRelease = doc.createElement("latestReleaseYear");
            latestRelease.setTextContent(String.valueOf(latestYear));

            statistics.appendChild(totalMovies);
            statistics.appendChild(releases);
            releases.appendChild(earliestRelease);
            releases.appendChild(latestRelease);

            doc.getDocumentElement().appendChild(statistics);

            //dom to string-filewriter
            String xmlOutput = docToString(doc.getDocumentElement());
            FileWriter writer = new FileWriter("ex7-out.xml");
            writer.write(xmlOutput);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String docToString(Node node) {

        StringBuilder builder = new StringBuilder();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            builder.append("<").append(element.getTagName());

            // Add attributes
            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                builder.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
            }
            builder.append(">");

            // Add children
            NodeList children = element.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                builder.append(docToString(children.item(i)));
            }

            builder.append("</").append(element.getTagName()).append(">");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String text = node.getTextContent().trim();
            if (!text.isEmpty()) {
                builder.append(text);
            }
        }

        return builder.toString();
    }
}