/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comm;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class myDataReceiveListener implements IDataReceiveListener {
    
    public XBeeMessage xbm;
    public String address;
    public byte[] message;
    public boolean newMessage = false;

    /*
    * Data reception callback
    */    
    
    @Override
    public void dataReceived(XBeeMessage xbeeMessage) {
        address = xbeeMessage.getDevice().get64BitAddress().toString();
        message = xbeeMessage.getData();
        xbm = xbeeMessage;
        newMessage = true;
    }
}