/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comm;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class XBeeHandler implements Runnable {

    XBeeDevice myXbee;
    myDataReceiveListener xbeeListener = new myDataReceiveListener();
    BlockingQueue<XBeeMessage> messages = new ArrayBlockingQueue<>(10);
    String portName;
    
    @Override
    public void run() {
        myXbee = new XBeeDevice((String)portName, 9600);
        try {
            myXbee.open();
            System.out.println("XBee Listening.");
        } catch (XBeeException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(XBeeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true) {
            myXbee.addDataListener(xbeeListener);
            if(xbeeListener.newMessage) {
                messages.offer(xbeeListener.xbm);
//                System.out.println("Offer size: " + messages.size());
                xbeeListener.newMessage = false;
            }
        }
    }
    
    public XBeeMessage dequeueMessage() {
        XBeeMessage message = null;
        if(messages.size() > 0) {
            message = messages.poll();
        }
        return message;
    }
    
    public void setCommPort(String pn) {
        portName = pn;
    }
}