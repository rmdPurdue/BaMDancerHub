/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comm;

import BaMDancerHub.Dancer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class OSCHandler implements Runnable {
    
    OSCSender oscSender = new OSCSender();
    
    @Override
    public void run() {
        while(true) {
            try {
                oscSender.sendBundles();
            } catch (IOException ex) {
                Logger.getLogger(OSCHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(OSCHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(OSCHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateLevels (byte[] data, String addressString) {
        oscSender.updateLevels(data, addressString);
    }
    
    public void addURL(String name, int slot, String url) {
        oscSender.addURL(name, slot, url);
    }
    
    public void addHost(InetAddress host) {
        oscSender.addHost(host);
    }
    
    public void addDancer(Dancer dancer) {
        oscSender.addDancer(dancer);
    }
}
