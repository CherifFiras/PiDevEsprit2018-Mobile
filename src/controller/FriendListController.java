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
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ChatListener;
import service.RelationService;
import service.UtilService;

/**
 *
 * @author hero
 */
public class FriendListController extends Controller {

    private Container ContainerList;
    private Container tabContainer;
    private UtilService utilService = UtilService.getInstance();
    private RelationService relationService = RelationService.getInstance();
    private Session session = Session.getInstance();
    private Map<Integer,ChatController> chatControllerList;
    private Map<Integer,User> chatUserList;
    private int selectedUser;
    @Override
    public void initialize() {
        chatControllerList = new HashMap<>();
        chatUserList = new HashMap<>();
        this.rootContainer.removeAll();
        ContainerList = new Container(BoxLayout.y());
        List<Relation> relations = relationService.fetchMembers();
        for(Relation relation:relations)
        {
            if(relation.getAcceptor().getId().equals(session.getConnectedUser().getId()))
            {
                ContainerList.add(friendListItem(relation.getRequester()));
                chatUserList.put(relation.getRequester().getId(), relation.getRequester());
            }
            else
            {
                ContainerList.add(friendListItem(relation.getAcceptor()));
                chatUserList.put(relation.getAcceptor().getId(), relation.getAcceptor());
            }
        }
        this.rootContainer.add(BorderLayout.WEST,ContainerList);
        initFriendListTab();
        this.rootContainer.revalidate();
    }
    
    private Component friendListItem(User user)
    {
        Font font = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Container item = new Container(BoxLayout.x());
        Label userImage = new Label(getRoundImage(utilService.getImageFromURL(user.getImage())));
        Label userName = new Label(user.getNom()+" "+user.getPrenom());
        userImage.setUIID(user.getId().toString());
        userImage.addPointerPressedListener(this::itemAction);
        userName.getUnselectedStyle().setFont(font);
        userName.setWidth(150);
        item.add(userImage);
        item.add(userName);
        item.setLeadComponent(userImage);
        return item;
    }
    
    private void itemAction(ActionEvent evt)
    {
        Label userImage = (Label) evt.getSource();
        int userId = Integer.parseInt(userImage.getUIID());
        ChatController chatController;
        if(chatControllerList.get(userId) != null)
        {
            if(selectedUser== userId) return;
            chatController = chatControllerList.get(userId);
            this.rootContainer.add(BorderLayout.WEST,chatController.getView());
            selectedUser = userId;
        }
        else
        {
            chatController = new ChatController();
            selectedUser = userId;
            chatController.initialize();
            chatController.setFriendUser(chatUserList.get(userId));
            chatController.loadMessages();
            ChatListener.addController(chatController);
            chatControllerList.put(userId, chatController);
            this.rootContainer.add(BorderLayout.WEST,chatController.getView());
            addUserImageToTab(userImage);
        }
        this.rootContainer.revalidate();
    }
    
    private void addUserImageToTab(Label userImage)
    {
        Label tabLabel = new Label(userImage.getIcon().scaled(64, 64));
        tabLabel.setUIID(userImage.getUIID());
        tabLabel.addPointerPressedListener(this::itemAction);
        tabContainer.add(tabLabel);
        tabContainer.revalidate();
    }
    
    private void initFriendListTab()
    {
        tabContainer = new Container(BoxLayout.x());
        Label chatImage = new Label(theme.getImage("chatc.png").scaled(64, 64));
        chatImage.setUIID("0");
        chatImage.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            int id = Integer.parseInt(((Label)evt.getSource()).getUIID());
            if(id == selectedUser)return;
            selectedUser= id;
            this.rootContainer.add(BorderLayout.WEST,ContainerList);
            this.rootContainer.revalidate();
        });
        tabContainer.add(chatImage);
        Container parentContainer = new Container(BoxLayout.y());
        tabContainer.getUnselectedStyle().setBgColor(0x000000);
        parentContainer.add(tabContainer);
        this.rootContainer.add(BorderLayout.NORTH, parentContainer);
    }
    
    private Image getRoundImage(Image originalImage)
    {
        Label label1 = new Label(originalImage);
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();   
        Image maskImage = Image.createImage(w, h);
        Graphics g = maskImage.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);
        g.setColor(0xffffff);
        g.fillArc(0, 0, w, h, 0, 360);
        Object mask = maskImage.createMask();     
        return originalImage.applyMask(mask);
    }
    
}