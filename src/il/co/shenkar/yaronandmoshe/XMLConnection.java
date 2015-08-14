package il.co.shenkar.yaronandmoshe;

import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Vector;

import javax.xml.parsers.*;

import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import org.w3c.dom.NodeList;

public class XMLConnection implements CurrencyRate
{
	private String dateTmp;
	private Currency cArray[];
	public String getDateTmp() {return dateTmp;}
	public Currency[] getcArray() {return cArray;}
	public XMLConnection() 
	{
		try {
			/** this part is responsible for opening the link to the site */
			URL website = new URL("http://www.bankisrael.gov.il/currency.xml");
			
			
				
			//else
				//System.out.println("aaaaa");
			
			/** this part is responsible for save the xml file */
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("currency.xml");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			
			//connection.disconnect();
		} catch(IOException e) {
			
		} /*catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		try {
		FileInputStream fin = new FileInputStream("currency.xml");
		
		
		
		InputStream in = fin;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(in);
		
		
		
		
		
		
		/** this part is responsible for translating the XML doc into NODES */
		NodeList list = doc.getElementsByTagName("NAME");
		NodeList country = doc.getElementsByTagName("COUNTRY");
		NodeList rate = doc.getElementsByTagName("RATE");
		NodeList code = doc.getElementsByTagName("CURRENCYCODE");
		NodeList unit = doc.getElementsByTagName("UNIT");
		NodeList date = doc.getElementsByTagName("LAST_UPDATE");
		/** translation loop */
		cArray=new Currency[list.getLength()];
		int len=list.getLength();
		System.out.print(len);
		for(int i=0;i<len;i++)
		{
			String countryTmp,nameTmp,rateTmp,codeTmp,unitTmp;
			nameTmp=(list.item(i).getFirstChild().getNodeValue());
			countryTmp=country.item(i).getFirstChild().getNodeValue();
			rateTmp=rate.item(i).getFirstChild().getNodeValue();	
			codeTmp=code.item(i).getFirstChild().getNodeValue();
			unitTmp=unit.item(i).getFirstChild().getNodeValue();
			cArray[i]=new Currency (nameTmp,countryTmp,codeTmp,Double.parseDouble(rateTmp),Double.parseDouble(unitTmp));
		}
		
		dateTmp=date.item(0).getFirstChild().getNodeValue();
		
			in.close();
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
