/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Relation;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hero
 */
public class RelationService {
    
    private static RelationService relationService;
    
    private RelationService()
    {}
    
    public static RelationService getInstance()
    {
        if(relationService == null) relationService = new RelationService();
        return relationService;
    }
    
    public List<Relation> fetchMembers()
    {
        List<Relation> relations = new ArrayList<>();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(Controller.ip+"/piintegration/web/app_dev.php/interaction/getmembers");
        request.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                List<Map<String, Object>> mappedList = (List<Map<String, Object>>) jsonp.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray())).get("root");
                for(Map<String,Object> obj:mappedList)
                {
                    Relation relation = new Relation();
                    relation.setId((int)Float.parseFloat(obj.get("id").toString()));
                    User requester = User.createUser((Map<String,Object>) obj.get("requester"));
                    User acceptor = User.createUser((Map<String,Object>) obj.get("acceptor"));
                    relation.setAcceptor(acceptor);
                    relation.setRequester(requester);
                    relations.add(relation);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return relations;
    }
}
