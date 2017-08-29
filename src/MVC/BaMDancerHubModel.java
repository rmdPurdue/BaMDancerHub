/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import BaMDancerHub.Dancer;
import Comm.OSCHandler;
import Comm.XBeeHandler;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;
import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.net.*;
import java.util.Arrays;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class BaMDancerHubModel extends java.util.Observable {
    
    XBeeHandler xbeeHandler = new XBeeHandler();
    OSCHandler oscHandler = new OSCHandler();
    Thread xbeeThread = new Thread(xbeeHandler, "XBee Thread");
    Thread oscThread = new Thread(oscHandler, "OSC Thread");
    XBeeDevice myXbee;

    public static String[] getCommPortList() {
        SerialPort[] ports = SerialPort.getCommPorts();
        ArrayList<String>aListPortList;
        aListPortList = new ArrayList<>();

        for(SerialPort port : ports) {
            aListPortList.add(port.getSystemPortName());
        }
        
        Object[] objPortList = aListPortList.toArray();
        String[] stringPortList = Arrays.copyOf(objPortList, objPortList.length, String[].class);
        
        return stringPortList;
    }
    
    public boolean checkXBeePresence(String portName) throws XBeeException {
        myXbee = new XBeeDevice(portName, 9600);
        try {
           myXbee.open(); 
        }
        catch (XBeeException xe) {
            System.out.println(xe);
            return false;
        }
        return true;
    }
    
    public void addDancer(String name, String address) {
        Dancer dancer = new Dancer(name);
        dancer.setStringAddress(address);
        oscHandler.addDancer(dancer);
    }
    
    public void addURL(String name, int slot, String url) {
        oscHandler.addURL(name, slot-1, url);
    }
    
    public void setXbeeCommPort(String portName) {
        xbeeHandler.setCommPort(portName);
    }
    
    public void startXbee() {
        xbeeThread.start();
    }
    
    public void parseXbeeMessage() throws InterruptedException {
        while(true) {
            XBeeMessage message = xbeeHandler.dequeueMessage();
            if(message!=null) {
                String addressString = message.getDevice().get64BitAddress().toString();
                byte[] data = message.getData();
                long address = hex2Long(addressString);
                oscHandler.updateLevels(data, addressString);
            }
        }
    }
    
    public void generateOSCBundles() throws UnknownHostException {
        InetAddress localHost = InetAddress.getByName("127.0.0.1");
        oscHandler.addHost(localHost);
        System.out.println("Start generating OSC.");
        oscThread.start();
    }
    
    private long hex2Long(String addressString) {
        long address = 0;
        String digits = "0123456789ABCDEF";
        addressString = addressString.toUpperCase();
        for(int i = 0; i < addressString.length(); i++) {
            char c = addressString.charAt(i);
            long d = digits.indexOf(c);
            address = 16 * address + d;
        }
        return address;
    }
}
