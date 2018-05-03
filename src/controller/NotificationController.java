/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Demande;
import Entity.Message;
import Entity.Notification;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import service.MessageService;
import service.NotificationService;
import service.RelationService;
import service.UtilService;

/**
 *
 * @author hero
 */
public class NotificationController extends Controller {

    private Timer timer;
    private final NotificationService notificationService = NotificationService.getInstance();
    private final MessageService messageService = MessageService.getInstance();
    private final UtilService utilService = UtilService.getInstance();
    private final RelationService relationService = RelationService.getInstance();
    private List<Notification> notifications;
    private int lastNotificationId = -1;
    private int messageNotifCount;
    private int demandeNotifCount;
    private int acceptNotifCount;
    private List<Notification> demandeNotifications;
    private List<Notification> messageNotifications;
    private List<Notification> acceptNotifications;
    private final Font messageFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    private SimpleDateFormat formatter;
    private Form form;
    private Container selectedContainer;
    private Container demandeItemsList;
    private Container acceptItemsList;
    private Container messageItemsList;
    
    @Override
    public void initialize() {
        demandeItemsList = new Container(BoxLayout.y());
        acceptItemsList = new Container(BoxLayout.y());
        messageItemsList = new Container(BoxLayout.y());
        formatter = new SimpleDateFormat("dd MM");
        notifications = notificationService.getNotifications();
        if(!notifications.isEmpty())
            lastNotificationId = notifications.get(notifications.size()-1).getId();
        initLists();
        fillLists();
        initTimer();
    }
    
    
    private void refreshCounts()
    {
        messageNotifCount = 0;
        demandeNotifCount = 0;
        acceptNotifCount = 0;
        notifications = notificationService.getNotifications();
        if(!notifications.isEmpty())
        {
            if(notifications.get(notifications.size()-1).getId() > lastNotificationId)
            {
                fillLists();
                for(Notification notification:notifications)
                {
                    switch(notification.getSubject())
                    {
                        case"Accept":acceptNotifCount++;break;
                        case"Demande":demandeNotifCount++;break;
                        case"Message":messageNotifCount++;break;
                    }
                }
                if(acceptNotifCount > 0)
                {
                    if(selectedContainer.equals(acceptItemsList))
                    {
                        getAcceptNotificationView();
                        form.revalidate();
                    }
                    else
                    {
                        if(!selectedContainer.equals(acceptItemsList)&&Dialog.show("Accept", "un personne accptait votre demande", "voir notification", "fermer"))
                        {
                            selectedContainer = acceptItemsList;
                            form.getContentPane().removeAll();
                            form.addComponent(BorderLayout.CENTER, getAcceptNotificationView());
                            form.revalidate();
                        }
                    }
                }
                if(demandeNotifCount > 0)
                {
                    if(selectedContainer.equals(demandeItemsList))
                    {
                        getDemandeNotificationView();
                        form.revalidate();
                    }
                    else
                    {
                        if(!selectedContainer.equals(demandeItemsList)&&Dialog.show("Demande", "un personne vous envoyait une demande", "voir notification", "fermer"))
                        {
                            selectedContainer = demandeItemsList;
                            form.getContentPane().removeAll();
                            form.addComponent(BorderLayout.CENTER, getDemandeNotificationView());
                            form.revalidate();
                        }
                    }
                }
                if(messageNotifCount > 0)
                {
                    if(selectedContainer.equals(messageItemsList))
                    {
                    getMessageNotificationView();
                    form.revalidate();
                    }
                    else
                    {
                        if(!selectedContainer.equals(messageItemsList)&&Dialog.show("Message", "un personne vous envoyait un message", "voir notification", "fermer"))
                        {
                            selectedContainer = messageItemsList;
                            form.getContentPane().removeAll();
                            form.addComponent(BorderLayout.CENTER, getMessageNotificationView());
                            form.revalidate();
                        }
                    }
                }
            }
            lastNotificationId = notifications.get(notifications.size()-1).getId();
        }
        System.gc();
    }
    
    private void fillLists()
    {
        System.out.println(notifications.size());
        acceptNotifications.clear();
        demandeNotifications.clear();
        messageNotifications.clear();
        for(Notification notification:notifications)
        {
            switch(notification.getSubject())
            {
                case"Accept":acceptNotifications.add(notification);break;
                case"Demande":demandeNotifications.add(notification);break;
                case"Message":messageNotifications.add(notification);break;
            }
        }
    }
    
    public void setForm(Form form)
    {
        this.form = form;
    }
    
    
    private void initLists()
    {
        acceptNotifications = new ArrayList<>();
        demandeNotifications = new ArrayList<>();
        messageNotifications = new ArrayList<>();
    }
    
    private void initTimer()
    {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                refreshCounts();
            }
        };
        timer.schedule(timerTask,0,5000);
    }
    
    public Component getDemandeNotificationView()
    {
        demandeItemsList.removeAll();
        Container topChild = new Container(BoxLayout.x());
        topChild.add(new Label("Notifications des Demandes:"));
        demandeItemsList.add(topChild);
        for(Demande demande:notificationService.getDemandeNotifications())
            demandeItemsList.add(demandeNotificationItem(demande));
        selectedContainer = demandeItemsList;
        demandeItemsList.revalidate();
        return demandeItemsList;
    }
    
    private Container demandeNotificationItem(Demande demande)
    {
        Container container = new Container(BoxLayout.x());
        container.add(getRoundImage(utilService.getImageFromURL(demande.getRequester().getImage())));
        Label messageUser = new Label(demande.getRequester().getNom()+" "+demande.getRequester().getPrenom());
        messageUser.getUnselectedStyle().setFont(messageFont);
        container.add(messageUser);
        Container children = new Container(BoxLayout.y());
        Button accpetButton = new Button("accepter");
        Button rejectButton = new Button("rejecter");
        children.add(accpetButton);
        children.add(rejectButton);
        accpetButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            relationService.acceptDemande(demande);
            Container parent = container.getParent();
            container.remove();
            parent.revalidate();
        });
        rejectButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            relationService.rejectDemande(demande);
            Container parent = container.getParent();
            container.remove();
            parent.revalidate();
        });
        container.add(children);
        return container;
    }
    
    public Component getAcceptNotificationView()
    {
        acceptItemsList.removeAll();
        Container topChild = new Container(BoxLayout.x());
        topChild.add(new Label("Notifications des Messages:"));
        acceptItemsList.add(topChild);
        for(Notification notification:acceptNotifications)
            acceptItemsList.add(acceptNotificationItem(notification));
        selectedContainer = acceptItemsList;
        acceptItemsList.revalidate();
        return acceptItemsList;
    }
    
    private Container acceptNotificationItem(Notification notification)
    {
        Container container = new Container(BoxLayout.x());
        container.add(getRoundImage(utilService.getImageFromURL(notification.getLink())));
        Container children = new Container(BoxLayout.y());
        Label messageUser = new Label(notification.getMessage());
        messageUser.getUnselectedStyle().setFont(messageFont);
        children.add(messageUser);
        Label textMessage = new Label("acceptait votre demande");
        textMessage.getUnselectedStyle().setFont(messageFont);
        children.add(textMessage);
        Label dateMessage = new Label(formatter.format(notification.getDate()));
        dateMessage.getUnselectedStyle().setFont(messageFont);
        container.add(children);
        container.add(dateMessage);
        return container;
    }
    
    public Component getMessageNotificationView()
    {
        List<String> ids = new ArrayList<>();
        for(Notification notification:messageNotifications)
            ids.add(notification.getLink());
        List<Message> messageList = messageService.getNotificatedMessages(ids.toArray(new String[0]));
        messageItemsList.removeAll();
        Container child = new Container(BoxLayout.x());
        child.add(new Label("Notifications des Messages:"));
        messageItemsList.add(child);
        for(Message message:messageList)
            messageItemsList.add(messageNotificationItem(message));   
        selectedContainer = messageItemsList;
        messageItemsList.revalidate();
        return messageItemsList;
    }
    
    private Container messageNotificationItem(Message message)
    {
        Container container = new Container(BoxLayout.x());
        container.add(getRoundImage(utilService.getImageFromURL(message.getSender().getImage())));
        Container children = new Container(BoxLayout.y());
        Label messageUser = new Label(message.getSender().getNom()+" "+message.getSender().getPrenom());
        messageUser.getUnselectedStyle().setFont(messageFont);
        children.add(messageUser);
        Label textMessage = new Label(message.getText());
        textMessage.getUnselectedStyle().setFont(messageFont);
        children.add(textMessage);
        container.add(children);
        Label dateMessage = new Label(formatter.format(message.getDate()));
        dateMessage.getUnselectedStyle().setFont(messageFont);
        container.add(dateMessage);
        return container;
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
