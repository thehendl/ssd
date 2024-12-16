import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

public class MyParser {

    public static void main(String[] args) throws Exception {

        // to avoid null pointer ex for run,
        if (args.length == 0) {
            System.out.println("run without xml call");
            return;
        }

        XMLReader parser = XMLReaderFactory.createXMLReader();
        MyContentHandler contentHandler = new MyContentHandler();
        parser.setContentHandler(contentHandler);

        for (String file : args) {
            parser.parse(file);
        }


        System.out.println(contentHandler.getOutputXml());

    }
}
