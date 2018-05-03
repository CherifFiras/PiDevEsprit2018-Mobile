/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Message;
import Entity.Relation;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hero
 */
public class MessageService {
    
    private static MessageService messageService; 
    
    private MessageService(){}
    
    public static MessageService getInstance()
    {
        if(messageService == null) messageService = new MessageService();
        return messageService;
    }
    
    public List<Message> getMessages(int chatUser)
    {
        List<Message> messages = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/getmessages");
        request.addArgument("touser", String.valueOf(chatUser));
        request.addArgument("last", "0");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        request.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                List<Map<String, Object>> mappedList = (List<Map<String, Object>>) jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray())).get("root");
                for(Map<String,Object> obj:mappedList)
                {
                    Message message = new Message();
                    message.setId((int)Float.parseFloat(obj.get("id").toString()));
                    User sender = User.createUser((Map<String,Object>) obj.get("sender"));
                    User receiver = User.createUser((Map<String,Object>) obj.get("receiver"));
                    message.setSender(sender);
                    message.setReceiver(receiver);
                    message.setText(obj.get("text").toString());
                    try {
                        message.setDate(dateFormat.parse(obj.get("date").toString()));
                    } catch (ParseException ex) {
                    }
                    messages.add(message);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return messages;
    }
    
}
