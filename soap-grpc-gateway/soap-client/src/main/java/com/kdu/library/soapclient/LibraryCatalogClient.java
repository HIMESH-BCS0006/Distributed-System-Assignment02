package com.kdu.library.soapclient;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Uses JAX-WS's Dispatch API instead of a typed proxy (getPort). Dispatch
 * sends/receives raw XML payloads directly and never needs to build a
 * WSDL-derived SEI model, so it avoids a Metro bug on some non-Oracle JDKs
 * where WSDL parsing throws NoClassDefFoundError for an internal Oracle-only
 * class (com.sun.org.apache.xml.internal.resolver.CatalogManager).
 */
public class LibraryCatalogClient {

    private static final QName SERVICE_NAME = new QName("http://library.kdu.com/soap", "LibraryCatalogService");
    private static final QName PORT_NAME = new QName("http://library.kdu.com/soap", "LibraryCatalogServicePort");

    public static void main(String[] args) throws Exception {
        String gatewayHost = System.getenv().getOrDefault("GATEWAY_HOST", "localhost");
        String endpointAddress = "http://" + gatewayHost + ":8080/ws/library";

        Service service = Service.create(SERVICE_NAME);
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        Dispatch<Source> dispatch = service.createDispatch(PORT_NAME, Source.class, Service.Mode.PAYLOAD);

        invoke(dispatch, "1. getBookDetails (valid book)",
                "<ns:getBookDetails xmlns:ns=\"http://library.kdu.com/soap\"><bookId>B001</bookId></ns:getBookDetails>");

        invoke(dispatch, "2. checkAvailability (valid book)",
                "<ns:checkAvailability xmlns:ns=\"http://library.kdu.com/soap\"><bookId>B001</bookId></ns:checkAvailability>");

        invoke(dispatch, "3. reserveBook (valid book, has copies)",
                "<ns:reserveBook xmlns:ns=\"http://library.kdu.com/soap\"><bookId>B001</bookId><memberId>member-42</memberId></ns:reserveBook>");

        invoke(dispatch, "4. reserveBook (no copies available -> NoCopiesAvailableFault)",
                "<ns:reserveBook xmlns:ns=\"http://library.kdu.com/soap\"><bookId>B002</bookId><memberId>member-42</memberId></ns:reserveBook>");

        invoke(dispatch, "5. getBookDetails (unknown book -> BookNotFoundFault)",
                "<ns:getBookDetails xmlns:ns=\"http://library.kdu.com/soap\"><bookId>B999</bookId></ns:getBookDetails>");

        invoke(dispatch, "6. checkAvailability (blank bookId -> InvalidInputFault)",
                "<ns:checkAvailability xmlns:ns=\"http://library.kdu.com/soap\"><bookId></bookId></ns:checkAvailability>");
    }

    private static void invoke(Dispatch<Source> dispatch, String label, String payloadXml) {
        System.out.println("\n=== " + label + " ===");
        try {
            Source request = new StreamSource(new StringReader(payloadXml));
            Source response = dispatch.invoke(request);
            System.out.println(sourceToString(response));
        } catch (SOAPFaultException fault) {
            System.out.println("Got SOAP Fault: " + fault.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);
        }
    }

    private static String sourceToString(Source source) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(source, new StreamResult(writer));
        return writer.toString();
    }
}