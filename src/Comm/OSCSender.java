/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comm;

import BaMDancerHub.Dancer;
import com.illposed.osc.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Rich Dionne <rdionne@purdue.edu>
 */
public class OSCSender {
    
    private ArrayList<InetAddress> hosts = new ArrayList<>();
    private final ArrayList<Dancer> dancers = new ArrayList<>();
    int remotePort = 8000;
    
    public void addHost(InetAddress host) {
        System.out.println(host);
        hosts.add(host);
    }
    
    public void addDancer(Dancer dancer) {
        dancers.add(dancer);
    }
    
    public void addURL(String name, int slot, String url) {
        for(Dancer dancer:dancers) {
            if(null != dancer.getName() && dancer.getName().equals(name)) {
                dancer.setURL(slot, url);
            }
        }
    }
    
    public void updateLevels(byte[] data, String addressString) {
        for(Dancer dancer:dancers) {
            if(dancer.getStringAddr().equals(addressString)) {
                System.out.println(dancer.getName());
                for(int i = 0; i < data.length; i++) {
                    dancer.setLevel(i, data[i]);
                }
            }
        }
    }
    
    public void sendBundles() throws SocketException, IOException, InterruptedException {
        for(InetAddress host:hosts) {
            OSCPortOut sender = new OSCPortOut(host, remotePort);
            System.out.println(host + ":"+remotePort);
            OSCBundle bundle = new OSCBundle();
            for(Dancer dancer:dancers) {
                for(int i = 0; i < 30; i++) {
                    if(dancer.getURL(i) != null) {
                        String address = messageBuilder(dancer.getName(),dancer.getURL(i));
                        Object args[] = new Object[1];
                        System.out.println(i);
                        args[0] = dancer.getLevel(i);
                        OSCMessage message = new OSCMessage(address, args);
                        System.out.println(address + " " + args[0].toString());
                        bundle.addPacket(message);
                    }
                }
            }
            sender.send(bundle);
        }
    }
    
    private String messageBuilder(String dancer, String address) {
        String[] addressComponents = new String[2];
        
        addressComponents[0] = dancer;
        
        addressComponents[1] = address;
        String url = "/" + String.join("/", addressComponents);
//        System.out.println(url);
        return url;
    }
}
