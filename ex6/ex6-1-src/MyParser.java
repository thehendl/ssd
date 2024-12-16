import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class MyParser {

    public static void main(String[] args) throws Exception {

        XMLReader parser = XMLReaderFactory.createXMLReader();

        ContentHandler contentHandler = new MyContentHandler();
        parser.setContentHandler(contentHandler);


        for (int i = 0; i < args.length; i++) {
            parser.parse(args[i]);
        }
    }
}
