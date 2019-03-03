package it.fscotto.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Classe di utilit� con metodi per la gestione di file XML
 *
 * @author Fabio Scotto di Santolo
 * @since 12/01/2017
 */
public class JAXBUtils {

	/**
	 * Converte un oggetto JAX-B in una stringa
	 *
	 * @param context
	 * @param objectJAXB
	 * @return
	 */
	public static String getXMLString(String context, Object objectJAXB) {
		StringWriter xml = new StringWriter();
		String xmlString = new String();
		try {
			JAXBContext jaxbLocalContext = JAXBContext.newInstance(context);
			jaxbLocalContext.createMarshaller().marshal(objectJAXB, xml);
			xmlString = xml.toString();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		return xmlString;
	}

	/**
	 * Converte una String in un oggetto JAX-B
	 *
	 * @param context
	 * @param stringToConvert
	 * @param displayStamp
	 * @return
	 */
	public static Object getObject(String context, String stringToConvert, boolean displayStamp) {
		Unmarshaller unmarshaller = null;
		Object objectJAXB = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(context);
			unmarshaller = jaxbContext.createUnmarshaller();
			StringBuffer xmlString = new StringBuffer(stringToConvert);
			objectJAXB = unmarshaller.unmarshal(new StringReader(xmlString.toString()));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return objectJAXB;
	}

	/**
	 * Serializza un oggetto JAX-B in un file in formato XML
	 *
	 * @param context
	 * @param object
	 * @param filePath
	 */
	public static void getMarshalledFile(String context, Object object, String filePath) {
		Marshaller marshaller = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(context);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, new FileOutputStream(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stampa in output un oggetto JAX-B
	 *
	 * @param context
	 * @param object
	 */
	public static void getMarshalledOutput(String context, Object object) {
		Marshaller marshaller = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(context);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(object, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
