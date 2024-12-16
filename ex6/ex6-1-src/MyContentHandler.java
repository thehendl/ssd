import org.xml.sax.*;
import org.xml.sax.helpers.*;

class MyContentHandler implements ContentHandler {
    private StringBuilder htmlOutput = new StringBuilder();


    public void startElement(String uri, String localName, String elementName, Attributes atts) throws SAXException {

        if (elementName.equals("offered-dishes")) {
            htmlOutput.append("<h2>" + elementName + "</h2><ul>");
        } else if (elementName.equals("dish")) {
            String id = atts.getValue("id");
            String name = atts.getValue("name");
            String vegetarian = atts.getValue("vegetarian");
            htmlOutput.append("<li>" + elementName + id + ": " + name + " (vegetarian: " + vegetarian + ")</li>");


        } else if (elementName.equals("personnel")) {
            htmlOutput.append("<h2>" + elementName + "</h2><ul>");
        } else if (elementName.equals("person")) {
            String id = atts.getValue("id");
            htmlOutput.append("<li>" + elementName + " " + id + ": ");
        } else if (elementName.equals("name")) {
            htmlOutput.append(elementName + " (");
        } else if (elementName.equals("transport")) {
            htmlOutput.append(elementName + " (");


        } else if (elementName.equals("current-orders")) {
            htmlOutput.append("<h2>" + elementName + ":" + "</h2>");
        } else if (elementName.equals("order")) {
            String orderId = atts.getValue("order-id");
            htmlOutput.append("<h3>" + elementName + orderId + "</h3><ul>");


        } else if (elementName.equals("item")) {
            String dishId = atts.getValue("dish");
            String price = atts.getValue("price");
            htmlOutput.append("<li>" + elementName + dishId + " (Price: " + price + ")"+ "</li>");


        } else if (elementName.equals("address")) {

            String deliveredBy = atts.getValue("deliveredBy");
            htmlOutput.append("<li>" + "delivered by: " + deliveredBy + " / " + elementName + ": ");


        } else if (elementName.equals("self-pickup")) {
            String clientName = atts.getValue("client-name");
            htmlOutput.append("<li>" + elementName + ": " + clientName + "</li>");
        }
    }


    public void endElement(String uri, String localName, String elementName) throws SAXException {
        if (elementName.equals("person")) {
            htmlOutput.append("</li>");

        } else if (elementName.equals("personnel")) {
            htmlOutput.append("</ul>");
        } else if (elementName.equals("order")) {
            htmlOutput.append("</ul>");
        } else if (elementName.equals("offered-dishes")) {
            htmlOutput.append("</ul>");
        } else if (elementName.equals("current-orders")) {
            //......................................................................
        } else if (elementName.equals("name")) {
            htmlOutput.append("), ");
        } else if (elementName.equals("transport")) {
            htmlOutput.append(")");
        } else if (elementName.equals("address")) {
            htmlOutput.append("</li>");
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (!content.isEmpty()) {
            htmlOutput.append(content);
        }
    }


    public void setDocumentLocator(Locator locator) {
    }

    public void startDocument() throws SAXException {
        htmlOutput.append("<html><head><title>Takeout Orders</title></head><body>");
        htmlOutput.append("<h1>Takeout Orders</h1>");
    }

    public void endDocument() throws SAXException {
        htmlOutput.append("</body></html>");
        System.out.println(htmlOutput.toString());
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    public void processingInstruction(String target, String data) throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }
}
