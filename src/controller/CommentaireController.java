/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Commentaire_forum;
import Entity.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import service.SujetService;
import service.UtilService;


/**
 *
 * @author ASUS
 */
public class CommentaireController extends Controller{
     Resources theme;
public CommentaireController()
    {
        super();
    }
private Image captureRoundImage(Image userPicture) {
     
            int width = userPicture.getWidth();
            if(userPicture.getHeight() != width) {
                if(userPicture.getWidth() < userPicture.getHeight()) {
                    userPicture = userPicture.subImage(0, userPicture.getHeight() / 2 - width / 2, width, width, false);
                } else {
                    Image n = Image.createImage(width, width);
                    n.getGraphics().drawImage(userPicture, 0, width / 2- userPicture.getHeight() / 2);
                    userPicture = n;
                }
            }
            return roundImage(userPicture);
     }
   private Image roundImage(Image img) {
        int h = Display.getInstance().convertToPixels(10f);
        int w = Display.getInstance().convertToPixels(10f);
        img=img.fill(w, h);
        
        return img;
    }
    @Override
    public void initialize() {
     
        Form f;
        f = new Form();
        f.getToolbar().setHidden(true);
        theme = UIManager.initFirstTheme("/theme");
        ImageViewer iva=new ImageViewer();
        try {
            iva.setImage(Image.createImage(Storage.getInstance().createInputStream(Beblio.getImage1())).scaled(100, 100));
        } catch (IOException ex) {
        }
        Container ca1=new Container(BoxLayout.x());
        ca1.getStyle().setMargin(0, 0, 0, 0);
        Label ss=new Label();
        ss.setUIID("ss");
        Label sss=new Label("Commentaires:");
        sss.setUIID("ss");
        f.add(ca1).add(ss);
        SujetService com = new SujetService();
        ArrayList<Commentaire_forum> lis = com.tt();
        System.out.println(lis);
        for (Commentaire_forum r : lis) {
            Container commentaires1=new Container(new BorderLayout());
            commentaires1.add(BorderLayout.CENTER,new SpanLabel("                                                                           "));
            Container discussion1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel t1 = new SpanLabel(r.getCommentaire());
            t1.setWidth(commentaires1.getWidth());
            t1.setX(0);
            t1.setHeight(t1.getPreferredH());
            t1.setY(Display.getInstance().getDisplayHeight());
            t1.setIconPosition(BorderLayout.WEST);
            
            Image userPicture1= UtilService.getInstance().getImageFromURL(Beblio.getImage());
            userPicture1=captureRoundImage(userPicture1);
            t1.setIcon(userPicture1);
            t1.setTextBlockAlign(Component.LEFT);
            discussion1.add(t1);
            discussion1.animateLayoutAndWait(400);
            discussion1.scrollComponentToVisible(t1);
            
            
            commentaires1.add(BorderLayout.CENTER, discussion1);
            
            commentaires1.setScrollableY(true);
            f.add(commentaires1);
            
            
        }
        f.add(sss);
        Container commentaires=new Container(new BorderLayout());
        commentaires.getStyle().setBorder(Border.createLineBorder(2));
        commentaires.add(BorderLayout.CENTER,new SpanLabel("                                                                           "));
        Container discussion = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        SpanLabel t = new SpanLabel(Beblio.getUsername());
        t.setWidth(commentaires.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());
        t.setY(Display.getInstance().getDisplayHeight());
        t.setIconPosition(BorderLayout.WEST);
        Image userPicture= UtilService.getInstance().getImageFromURL(Beblio.getImage());
        userPicture=captureRoundImage(userPicture);
        t.setIcon(userPicture);
        t.setTextBlockAlign(Component.LEFT);
        discussion.add(t);
        discussion.animateLayoutAndWait(400);
        discussion.scrollComponentToVisible(t);
        TextField ask = new TextField("", "Commentaire:", 20, TextField.ANY);
        Button askButton = new Button("Commenter");
        commentaires.add(BorderLayout.CENTER, discussion).add(BorderLayout.SOUTH, BorderLayout.center(ask).add(BorderLayout.EAST, askButton));
        askButton.addActionListener((evt) -> {
            
            SujetService cc = new SujetService();
            cc.ajouter_com(ask.getText());
            this.rootContainer.removeAll();
            
            CommentaireController commentaireController = new CommentaireController();
            commentaireController.initialize();
            this.rootContainer.addComponent(BorderLayout.CENTER, commentaireController.getView());
            this.rootContainer.revalidate();
            
            
        });
        commentaires.setScrollableY(true);
        f.add(commentaires);
        this.rootContainer.add(BorderLayout.NORTH, f);
    }
    
}
