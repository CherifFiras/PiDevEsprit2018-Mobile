/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.main;

import Entity.User;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author hero
 */
public abstract class Controller {
    
    protected Container rootContainer;
    public static Resources theme;
    public static String ip = "http://127.0.0.1:8888";
    
    public Controller()
    {
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE);
        rootContainer = new Container(borderLayout);
        Container loadingContainer = new Container();
        loadingContainer.addComponent(new InfiniteProgress());
        rootContainer.addComponent(BorderLayout.CENTER, loadingContainer);
    }
    
    public abstract void initialize();
    
    public Component getView()
    {
        return rootContainer;
    }

}
