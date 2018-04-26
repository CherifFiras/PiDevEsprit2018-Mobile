/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Relation;
import Entity.User;
import com.codename1.main.Controller;
import com.codename1.main.Session;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.List;
import service.RelationService;
import service.UtilService;

/**
 *
 * @author hero
 */
public class FriendListController extends Controller {

    private Container ContainerList;
    private UtilService utilService = UtilService.getInstance();
    private RelationService relationService = RelationService.getInstance();
    private Session session = Session.getInstance();
    
    @Override
    public void initialize() {
       
        ContainerList = new Container(BoxLayout.y());
        List<Relation> relations = relationService.fetchMembers();
        for(Relation relation:relations)
        {
            if(relation.getAcceptor().getId().equals(session.getConnectedUser().getId()))
                ContainerList.add(friendListItem(relation.getRequester()));
            else
                ContainerList.add(friendListItem(relation.getAcceptor()));
        }
        this.rootContainer.add(BorderLayout.CENTER,ContainerList);
        this.rootContainer.revalidate();
    }
    
    private Component friendListItem(User user)
    {
        Container item = new Container(BoxLayout.x());
        Image userImage = utilService.getImageFromURL(user.getImage());
        Label userName = new Label(user.getNom()+" "+user.getPrenom());
        userName.setWidth(150);
        item.add(userImage);
        item.add(userName);
        return item;
    }
    
}