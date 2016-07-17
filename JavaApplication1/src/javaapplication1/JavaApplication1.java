/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
/**
 *
 * @author Happy_Wolf
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TransformerException, TransformerConfigurationException, FileNotFoundException, ParserConfigurationException, SAXException, IOException {
                
        System.out.println("||----------------------------------||\n");
        
        System.out.println("help - вывод справки");
        System.out.println("insert - добавить запись");
        System.out.println("update - изменить запись");
        System.out.println("delete - удалить запись");
        System.out.println("find - найти запись");
        System.out.println("list - отобразить запись");
        System.out.println("exit - выйти из программы");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите команду: ");
        String com = in.nextLine();
        do {
            
            if (com.equals("insert"))
                {
                    JavaApplication1.Vvod();
                    com.equals("");
                }
            if (com.equals("update"))
                {
                    JavaApplication1.obnovit();
                }
            if (com.equals("delete"))
                {
                    JavaApplication1.udailt();
                }
            if (com.equals("help"))
                {
                   System.out.println("insert - добавить запись");
                   System.out.println("update - изменить запись");
                   System.out.println("delete - удалить запись");
                   System.out.println("find - найти запись");
                   System.out.println("list - отобразить запись");
                   com= "";
                }
            if (com.equals("find"))
                {
                    JavaApplication1.find();
                }
            if (com.equals("list"))
                {
                    JavaApplication1.otobraz();
                }
            System.out.println("Введите команду: ");
            com = in.nextLine();
        } while (!com.equals("exit"));
    }
    
    
    public static void Vvod() throws TransformerException, TransformerConfigurationException, FileNotFoundException {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder build = dbf.newDocumentBuilder();
            Document doc = build.newDocument();
            
            Element root = doc.createElement("Clients");
            
            Element client = doc.createElement("Client");
            
            Element clientID = doc.createElement("ClientID");
            
            Scanner in = new Scanner(System.in);
            
            System.out.print("Введите идентификатор клиента: ");
            String clientid = in.nextLine();
            doc.createTextNode(clientid);
            clientID.appendChild(doc.createTextNode(clientid));
            
            Element name = doc.createElement("Name");
            System.out.print("Введите ФИО клиента: ");
            String Name = in.nextLine();
            doc.createTextNode(Name);
            name.appendChild(doc.createTextNode(Name));
            
            Element spec = doc.createElement("Speciality");
            System.out.print("Введите должность клиента: ");
            String Spe = in.nextLine();
            spec.appendChild(doc.createTextNode(Spe));
            
            
            Element organ = doc.createElement("Organization");
            System.out.print("Введите организацию клиента: ");
            String orga = in.nextLine();
            organ.appendChild(doc.createTextNode(orga));
            
            Element email = doc.createElement("E-mail");
            System.out.print("Введите E-Mail клиента: ");
            String ema = in.nextLine();
            email.appendChild(doc.createTextNode(ema));
                        
            System.out.print("Введите количество телефонных номеров: ");
            int ph = in.nextInt();
            
            Element Phones = doc.createElement("Phones");
            Element phn = doc.createElement("Phone");
            
            //здесь не создается ветка для Phones
            if (ph >= 1)
            {
                for (int j=1;j <= ph ; j++)
                {
                    System.out.print("Введите номер телефона №" + " " + j+ ":  ");
                    String ph1 = in.nextLine();
                    phn.appendChild(doc.createTextNode(ph1));
                }
            }
            else
                {
                    System.out.print("Введите номер телефона: ");
                    String ph1 = in.nextLine();
                    phn.appendChild(doc.createTextNode(ph1));
                }
            
            client.appendChild(clientID);
            
            client.appendChild(name);
            
            client.appendChild(spec);
            
            client.appendChild(organ);
            
            client.appendChild(email);
            
            client.appendChild(Phones);
            
            Phones.appendChild(phn);
            
            root.appendChild(client);
            
            doc.appendChild(root);
            
           // DOMSource source = new DOMSource(doc);
            
            String path = "src\\javaapplication1\\clients.xml";
            File f = new File(path);
            
           // Result result = new StreamResult(f);
            
           // Result result = new StreamResult(f);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("src\\javaapplication1\\clients.xml",true)));
            System.out.print("write data success to file: " + path + "\n\n");
           
        
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
        
    public static void udailt() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException
        {
            String path = "src\\javaapplication1\\clients.xml";
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);

            factory.setExpandEntityReferences(false);

            Document doc = factory.newDocumentBuilder().parse(new File(path));
            
            Scanner in = new Scanner(System.in);
            
            System.out.print("enter tag Name: ");
            
            String id = in.nextLine();
            
            Element el = (Element)doc.getElementsByTagName(id).item(0);
            
            el.getParentNode().removeChild(el);
            
            doc.normalize();
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("src\\javaapplication1\\clients.xml")));
            System.out.print("Done: " + path + "\n\n");
        }
     
    public static void obnovit()
        {
            String path = "src\\javaapplication1\\clients.xml";
            
            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
            
            try {
                
               DocumentBuilder bui = dbf1.newDocumentBuilder();
               
               Document doc1 = bui.parse(path);
               
               Node data = doc1.getFirstChild();
               
               Node name = doc1.getElementsByTagName("Name").item(0);
               
               String name1 = name.getNodeValue();
               
                System.out.print("Введите новое ФИО клиента: ");
               
               Scanner in = new Scanner(System.in);
               
               String name2 = in.nextLine();
               
               name.setTextContent(name2);
               
               TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(doc1), new StreamResult(new FileOutputStream("src\\javaapplication1\\clients.xml")));
                System.out.print("write data success to file: " + path + "\n\n");

            }
             catch (Exception e) {
                e.printStackTrace();
             }
        }
    
    public static void find () //продолжить пилить здесь завтра
        {
            String path = "src\\javaapplication1\\clients.xml";
            
            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
            
            try {
                
               DocumentBuilder bui = dbf1.newDocumentBuilder();
               
               Document doc1 = bui.parse(path);
               
               XPath xPath = XPathFactory.newInstance().newXPath();
               
               Scanner in = new Scanner(System.in);
               
               System.out.println("Введите название тэга: ");
               
               String tag = in.nextLine();
               
               NodeList currnode = doc1.getChildNodes();
               
               for (int i = 0; i< currnode.getLength(); i++)
               {
                   Node curr = currnode.item(i);
                   if ( doc1.getNodeName() == tag)
                   {
                       //doSomething(curr);
                       System.out.println(curr.getTextContent());
                   }
                   else
                   {
                       //doSomething(curr);
                       System.out.println(curr.getNodeValue());
                   }
               }
               
                
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    public static void otobraz() throws ParserConfigurationException, SAXException, IOException
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    Document document = docBuilder.parse(new File("src\\javaapplication1\\clients.xml"));
    doSomething(document.getDocumentElement());
        }
    
    public static void doSomething(Node node) {
    // do something with the current node instead of System.out
        System.out.print(node.getNodeName()+ ": ");
        System.out.println(node.getTextContent());

    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
        Node currentNode = nodeList.item(i);
        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
            //calls this method for all the children which is Element
            doSomething(currentNode);
        }
    }
    }
}
        
