/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Message;
import Entity.User;
import com.codename1.main.Controller;
import com.codename1.main.Session;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import java.util.Date;
import java.util.List;
import service.ChatListener;
import service.MessageService;

/**
 *
 * @author hero
 */
public class ChatController extends Controller{

    private User friendUser;
    private User currentUser;
    private Container chatZone;
    private Container sendZone;
    private Container barZone;
    private final MessageService messageService = MessageService.getInstance();
    private FriendListController friendListController;
    private Label imageLabel;
    TextField textField;
    Font chatFont;
    @Override
    public void initialize() {
        
        currentUser = Session.getInstance().getConnectedUser();
        chatFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        Label exitButton = new Button("Quitter");
        exitButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            friendListController.returnToTab(imageLabel,friendUser.getId());
        });
        chatZone = new Container(BoxLayout.y());
        //chatZone.getUnselectedStyle().setBorder(RoundRectBorder.createLineBorder(1),true);
        chatZone.setScrollableY(true);
        chatZone.setWidth(500);
        chatZone.revalidate();
        sendZone = new Container(BoxLayout.x());
        barZone = new Container(BoxLayout.y());
        barZone.addAll(exitButton);
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH, barZone);
        this.rootContainer.add(BorderLayout.WEST,chatZone);
        initSendZone();
    }

    public void setImageLabel(Label label)
    {
        imageLabel = label;
    }
    
    private void initSendZone()
    {
        textField = new TextField();
        textField.setColumns(13);
        Button sendButton = new Button("send");
        sendButton.addActionListener(this::send);
        sendZone.addAll(textField,sendButton);
        this.rootContainer.add(BorderLayout.SOUTH,sendZone);
    }
    
    public void setFriendListController(FriendListController controller)
    {
        friendListController = controller;
    }
    
    public void loadMessages()
    {
        List<Message> messages = messageService.getMessages(friendUser.getId());
        for(Message message:messages)
        {
            if(message.getSender().equals(currentUser))
            {
                message.setSender(currentUser);
                message.setReceiver(friendUser);
            }
            else
            {
                message.setSender(friendUser);
                message.setReceiver(currentUser);
            }
            addToChat(message);
        }
    }
    
    private void send(ActionEvent evt)
    {
        Message message = new Message();
        message.setId(1);
        message.setSender(currentUser);
        message.setReceiver(friendUser);
        message.setText(textField.getText());
        message.setDate(new Date());
        textField.clear();
        addToChat(message);
        ChatListener.send(message);
    }
    
    public void setFriendUser(User user)
    {
        friendUser = user;
    }
    
    public void addToChat(Message message)
    {
        Label messageLabel = new Label();
        messageLabel.getUnselectedStyle().setFont(chatFont);
        messageLabel.setText(message.getSender().getNom()+" "+message.getSender().getPrenom()+": "+message.getText());
        //messageLabel.getUnselectedStyle().setAlignment(Component.RIGHT);
        chatZone.add(messageLabel);
        this.rootContainer.revalidate();
    }
    

    public User getChatUser() {
        return friendUser;
    }
 
}
