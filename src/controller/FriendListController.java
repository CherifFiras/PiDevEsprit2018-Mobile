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
import java.util.List;
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
    private List<Label> openedTabs;
    
    @Override
    public void initialize() {
        openedTabs = new ArrayList<>();
        this.rootContainer.removeAll();
        ContainerList = new Container(BoxLayout.y());
        List<Relation> relations = relationService.fetchMembers();
        for(Relation relation:relations)
        {
            if(relation.getAcceptor().getId().equals(session.getConnectedUser().getId()))
                ContainerList.add(friendListItem(relation.getRequester()));
            else
                ContainerList.add(friendListItem(relation.getAcceptor()));
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
        if(openedTabs.contains(userImage))
        {
            
        }
        else
        {
            tabContainer.add(userImage.getIcon().scaled(64, 64));
            openedTabs.add(userImage);
        }
        tabContainer.revalidate();
    }
    
    
    private void initFriendListTab()
    {
        tabContainer = new Container(BoxLayout.x());
        Label chatImage = new Label(theme.getImage("chatc.png").scaled(64, 64));
        chatImage.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            this.rootContainer.add(BorderLayout.WEST,ContainerList);
            this.rootContainer.revalidate();
        });
        tabContainer.add(chatImage);
        Label line = new Label("\u0000") {
            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(getX(), getY() + getHeight() - 1, getX() + getWidth(), getY() + getHeight() - 1);
            }
        };
        Container parentContainer = new Container(BoxLayout.y());
        tabContainer.getUnselectedStyle().setBgColor(0x000000);
        parentContainer.add(tabContainer);
        parentContainer.add(line);
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