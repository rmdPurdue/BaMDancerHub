/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import com.digi.xbee.api.exceptions.XBeeException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class BaMDancerHubController implements ActionListener {
    
    private BaMDancerHubModel model;
    private BaMDancerHubView view;
    private String[] portList;
    private boolean xbeeConnected;
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    String fileName = "C:\\Users\\richd\\Desktop\\temp.xml";
    
    // Create the controller
    public void BaMDancerHubController(BaMDancerHubView view, BaMDancerHubModel model) {
        this.view = view;
        this.model = model;
    }
    
    // Connect model to controller
    public void addModel(BaMDancerHubModel model) {
        this.model = model;
    }
    
    // Connect view to controller
    public void addView(BaMDancerHubView view) {
        this.view  = view;
    }
    
    // Initialize the model
    public void initModel() {
        
    }

    private void connectXBee(String portName) throws XBeeException {
        if(model.checkXBeePresence(portName)) {
            view.xbeeStatus.setText("Local XBee found.");
            model.setXbeeCommPort(portName);
            model.startXbee();
            view.commStatus.setText("XBee Communication Running.");
        } else {
            view.xbeeStatus.setText("Connection error.");
            xbeeConnected = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String command;
        command = event.getActionCommand();
        if(command.equals("setDancer1Address")) {
            if(view.dancer1Name.getText().equals("")) {
                view.dancer1Status.setText("Enter a name.");
            } else if(view.dancer1MACAddr.getText().equals("")) {
                view.dancer1Status.setText("Enter a MAC address.");
            } else {
                model.addDancer(view.dancer1Name.getText(),view.dancer1MACAddr.getText());
                view.dancer1Status.setText("Dancer added.");
            }
        }
        if(command.equals("dancer1newURL1")) {
            model.addURL("Dancer1", 1, view.dancer1URL1.getText());
        }
        if(command.equals("dancer1newURL2")) {
            model.addURL("Dancer1", 2, view.dancer1URL2.getText());
        }
        if(command.equals("dancer1newURL3")) {
            model.addURL("Dancer1", 3, view.dancer1URL3.getText());
        }
        if(command.equals("dancer1newURL4")) {
            model.addURL("Dancer1", 4, view.dancer1URL4.getText());
        }
        if(command.equals("dancer1newURL5")) {
            model.addURL("Dancer1", 5, view.dancer1URL5.getText());
        }
        if(command.equals("dancer1newURL6")) {
            model.addURL("Dancer1", 6, view.dancer1URL6.getText());
        }
        if(command.equals("dancer1newURL7")) {
            model.addURL("Dancer1", 7, view.dancer1URL7.getText());
        }
        if(command.equals("dancer1newURL8")) {
            model.addURL("Dancer1", 8, view.dancer1URL8.getText());
        }
        if(command.equals("dancer1newURL9")) {
            model.addURL("Dancer1", 9, view.dancer1URL9.getText());
        }
        if(command.equals("dancer1newURL10")) {
            model.addURL("Dancer1", 10, view.dancer1URL10.getText());
        }
        if(command.equals("dancer1newURL11")) {
            model.addURL("Dancer1", 11, view.dancer1URL11.getText());
        }
        if(command.equals("dancer1newURL12")) {
            model.addURL("Dancer1", 12, view.dancer1URL12.getText());
        }
        if(command.equals("dancer1newURL13")) {
            model.addURL("Dancer1", 13, view.dancer1URL13.getText());
        }
        if(command.equals("dancer1newURL14")) {
            model.addURL("Dancer1", 14, view.dancer1URL14.getText());
        }
        if(command.equals("dancer1newURL15")) {
            model.addURL("Dancer1", 15, view.dancer1URL15.getText());
        }
        if(command.equals("dancer1newURL16")) {
            model.addURL("Dancer1", 16, view.dancer1URL16.getText());
        }
        if(command.equals("dancer1newURL17")) {
            model.addURL("Dancer1", 17, view.dancer1URL17.getText());
        }
        if(command.equals("dancer1newURL18")) {
            model.addURL("Dancer1", 18, view.dancer1URL18.getText());
        }
        if(command.equals("dancer1newURL19")) {
            model.addURL("Dancer1", 19, view.dancer1URL19.getText());
        }
        if(command.equals("dancer1newURL20")) {
            model.addURL("Dancer1", 20, view.dancer1URL20.getText());
        }
        if(command.equals("dancer1Save")) {
            fileSave("Dancer1");
        }
        if(command.equals("dancer1Load")) {
            fileLoad("Dancer1");
        }
        
        if(command.equals("setDancer2Address")) {
            model.addDancer("Dancer2",view.dancer2MACAddr.getText());
            view.dancer2Status.setText("Dancer added.");
        }
        if(command.equals("dancer2newURL1")) {
            model.addURL("Dancer2", 1, view.dancer2URL1.getText());
        }
        if(command.equals("dancer2newURL2")) {
            model.addURL("Dancer2", 2, view.dancer2URL2.getText());
        }
        if(command.equals("dancer2newURL3")) {
            model.addURL("Dancer2", 3, view.dancer2URL3.getText());
        }
        if(command.equals("dancer2newURL4")) {
            model.addURL("Dancer2", 4, view.dancer2URL4.getText());
        }
        if(command.equals("dancer2newURL5")) {
            model.addURL("Dancer2", 5, view.dancer2URL5.getText());
        }
        if(command.equals("dancer2newURL6")) {
            model.addURL("Dancer2", 6, view.dancer2URL6.getText());
        }
        if(command.equals("dancer2newURL7")) {
            model.addURL("Dancer2", 7, view.dancer2URL7.getText());
        }
        if(command.equals("dancer2newURL8")) {
            model.addURL("Dancer2", 8, view.dancer2URL8.getText());
        }
        if(command.equals("dancer2newURL9")) {
            model.addURL("Dancer2", 9, view.dancer2URL9.getText());
        }
        if(command.equals("dancer2newURL10")) {
            model.addURL("Dancer2", 10, view.dancer2URL10.getText());
        }
        if(command.equals("dancer2newURL11")) {
            model.addURL("Dancer2", 11, view.dancer2URL11.getText());
        }
        if(command.equals("dancer2newURL12")) {
            model.addURL("Dancer2", 12, view.dancer2URL12.getText());
        }
        if(command.equals("dancer2newURL13")) {
            model.addURL("Dancer2", 13, view.dancer2URL13.getText());
        }
        if(command.equals("dancer2newURL14")) {
            model.addURL("Dancer2", 14, view.dancer2URL14.getText());
        }
        if(command.equals("dancer2newURL15")) {
            model.addURL("Dancer2", 15, view.dancer2URL15.getText());
        }
        if(command.equals("dancer2newURL16")) {
            model.addURL("Dancer2", 16, view.dancer2URL16.getText());
        }
        if(command.equals("dancer2newURL17")) {
            model.addURL("Dancer2", 17, view.dancer2URL17.getText());
        }
        if(command.equals("dancer2newURL18")) {
            model.addURL("Dancer2", 18, view.dancer2URL18.getText());
        }
        if(command.equals("dancer2newURL19")) {
            model.addURL("Dancer2", 19, view.dancer2URL19.getText());
        }
        if(command.equals("dancer2newURL20")) {
            model.addURL("Dancer2", 20, view.dancer2URL20.getText());
        }
        if(command.equals("dancer2Save")) {
            fileSave("Dancer2");
        }
        
        if(command.equals("Connect")) {
            String selectedPort = (String)view.commPort.getSelectedItem();
            if("None found.".equals(selectedPort)) {
                view.xbeeStatus.setText("No Ports Found.");
            } else {
                try {
                    connectXBee(selectedPort);
                } catch (XBeeException ex) {
                    Logger.getLogger(BaMDancerHubController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void fileSave(String name) {
        XMLEvent endLine = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        
        try {
            XMLEventWriter writer = factory.createXMLEventWriter(new FileWriter(fileName));
            XMLEvent event = eventFactory.createStartDocument();
            writer.add(event);
            writer.add(endLine);
            
            event = eventFactory.createStartElement("","", name);
            writer.add(event);
            writer.add(endLine);
            
            event = eventFactory.createStartElement("","","MAC_Address");
            writer.add(tab);
            writer.add(event);
            
            writer.add(eventFactory.createCharacters(view.dancer1MACAddr.getText()));
            
            event = eventFactory.createEndElement("","","MAC_Address");
            writer.add(event);
            writer.add(endLine);
            
            event = eventFactory.createStartElement("","","Slot_1");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL1.getText()));
            
            event = eventFactory.createEndElement("","","Slot_1");
            writer.add(event);
            writer.add(endLine);
            
            event = eventFactory.createStartElement("","","Slot_2");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL2.getText()));
            
            event = eventFactory.createEndElement("","","Slot_2");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_3");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL3.getText()));
            
            event = eventFactory.createEndElement("","","Slot_3");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_4");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL4.getText()));
            
            event = eventFactory.createEndElement("","","Slot_4");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_5");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL5.getText()));
            
            event = eventFactory.createEndElement("","","Slot_5");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_6");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL6.getText()));
            
            event = eventFactory.createEndElement("","","Slot_6");
            writer.add(event);
            writer.add(endLine);
           
            event = eventFactory.createStartElement("","","Slot_7");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL7.getText()));
            
            event = eventFactory.createEndElement("","","Slot_7");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_8");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL8.getText()));
            
            event = eventFactory.createEndElement("","","Slot_8");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_9");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL9.getText()));
            
            event = eventFactory.createEndElement("","","Slot_9");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_10");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL10.getText()));
            
            event = eventFactory.createEndElement("","","Slot_10");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_11");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL11.getText()));
            
            event = eventFactory.createEndElement("","","Slot_11");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_12");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL12.getText()));
            
            event = eventFactory.createEndElement("","","Slot_12");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_13");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL13.getText()));
            
            event = eventFactory.createEndElement("","","Slot_13");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_14");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL14.getText()));
            
            event = eventFactory.createEndElement("","","Slot_14");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_15");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL15.getText()));
            
            event = eventFactory.createEndElement("","","Slot_15");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_16");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL16.getText()));
            
            event = eventFactory.createEndElement("","","Slot_16");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_17");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL17.getText()));
            
            event = eventFactory.createEndElement("","","Slot_17");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_18");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL18.getText()));
            
            event = eventFactory.createEndElement("","","Slot_18");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_19");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL19.getText()));
            
            event = eventFactory.createEndElement("","","Slot_19");
            writer.add(event);
            writer.add(endLine);

            event = eventFactory.createStartElement("","","Slot_20");
            writer.add(tab);
            writer.add(event);
                        
            writer.add(eventFactory.createCharacters(view.dancer1URL20.getText()));
            
            event = eventFactory.createEndElement("","","Slot_20");
            writer.add(event);
            writer.add(endLine);
            
            event = eventFactory.createEndElement("","",name);
            writer.add(event);
            
            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException ex) {
            System.out.println(ex);
            Logger.getLogger(BaMDancerHubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Writing successful.");
    }
    
    public void fileLoad(String name) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(fileName));
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                String element;
                String contents;
                if(event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    if(startElement.getName().getLocalPart().equals("MAC_Address")) {
                        event = eventReader.nextEvent();
                        view.dancer1MACAddr.setText(event.asCharacters().getData());
                    } else if(startElement.getName().getLocalPart().equals("Slot_1")) {
                        event = eventReader.nextEvent();
                        view.dancer1URL1.setText(event.asCharacters().getData());
                        view.dancer1URL1.setForeground(Color.black);
                        
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(BaMDancerHubController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void startApplication() throws XBeeException, InterruptedException, UnknownHostException {
        portList = BaMDancerHubModel.getCommPortList();
        String[] errorList = {"None found."};
        if(portList.length > 0) {
            view.setCommPortList(portList);
        } else {
            view.setCommPortList(errorList);
        }
        view.setVisible(true);
        Thread.sleep(1000);
        model.generateOSCBundles();
        model.parseXbeeMessage();
    }
}
