/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Entity.sujet;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

import service.SujetService;


public class SujetController extends Controller {

    EncodedImage enc ;

    private Resources theme;

     public SujetController()
    {
        super();
    }
     
     
     public void test(){
      Form f;
        SpanLabel lb;
        f = new Form();    
        theme = UIManager.initFirstTheme("/theme");
        this.rootContainer.removeAll();

           ButtonGroup barGroup = new ButtonGroup();
        Button all = new Button("All");
        all.setUIID("SelectBar");
        Button ajouter = new Button("ajouter");
        ajouter.setUIID("SelectBar");
        Button popular = new Button("popular");
        popular.setUIID("SelectBar");
        Button myFavorite = new Button("My Favoritessssssss");
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(theme.getImage("news-tab-down-arrow.png"), "Container");
        
        f.add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, ajouter, popular, myFavorite),
                FlowLayout.encloseBottom()
        ));
       
         all.addActionListener(e->{
        
  this.rootContainer.removeAll();
                    
                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();
  
  });   
        
        
       popular.addActionListener(e->{
        
  this.rootContainer.removeAll();
                    
                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();
  
  });   
        
        
  ajouter.addActionListener(e->{
        
      

                       this.rootContainer.removeAll();
                    
                ajout_sujetController sujetController = new ajout_sujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();
  
  
  });
  
  
  
  
            lb = new SpanLabel("");
            Container list = new Container(BoxLayout.y());
            
            SujetService serviceTask=new SujetService();
            ArrayList<sujet> lis=serviceTask.getList4();
            
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
            
            for (sujet r : lis) {
 Beblio.setIds(r.getId_sujet());
SujetService v = new SujetService();
int x = v.getList3();
       System.err.println(x);
       Container ct = new Container();
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Image images = URLImage.createToStorage(enc,r.getImage(),Controller.ip+"/BackupWeb/piIntegration/web/images/"+r.getImage());
       Button image = new Button(images.fill(width, height));
       image.setUIID("Label");
       ct = BorderLayout.west(image);
       ct.setLeadComponent(image);
       TextArea ta = new TextArea(r.getContenu());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(x + " Commentaires  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!false) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       ct.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes)
               ));
       f.add(ct);
       image.addActionListener(e -> ToastBar.showMessage(r.getContenu(), FontImage.MATERIAL_INFO));
               
            }    
            f.getToolbar().setHidden(true);
            this.rootContainer.add(BorderLayout.NORTH, f);  
            this.rootContainer.revalidate();
        }
     

     
     
     
    
    @Override
    public void initialize() {
        try {
            Form f;
            SpanLabel lb;
            f = new Form();
            theme = UIManager.initFirstTheme("/theme");
            this.rootContainer.removeAll();

            ButtonGroup barGroup = new ButtonGroup();
            Button all = new Button("All");
            all.setUIID("SelectBar");
            Button ajouter = new Button("ajouter");
            ajouter.setUIID("SelectBar");
            Button popular = new Button("popular");
            popular.setUIID("SelectBar");
            Button myFavorite = new Button("My Favoritessssssss");
            myFavorite.setUIID("SelectBar");
            Label arrow = new Label(theme.getImage("news-tab-down-arrow.png"), "Container");

            f.add(LayeredLayout.encloseIn(
                    GridLayout.encloseIn(4, all, ajouter, popular, myFavorite),
                    FlowLayout.encloseBottom()
            ));
            all.addActionListener(e -> {

                this.rootContainer.removeAll();

                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();

            });
            popular.addActionListener(e -> {

                this.rootContainer.removeAll();

                SujetController sujetController = new SujetController();
                sujetController.test();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();

            });

            ajouter.addActionListener(e -> {

                this.rootContainer.removeAll();

                ajout_sujetController sujetController = new ajout_sujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();

            });

            lb = new SpanLabel("");
            Container list = new Container(BoxLayout.y());

            SujetService serviceTask = new SujetService();
            ArrayList<sujet> lis = serviceTask.getList2();

            enc = EncodedImage.create("/giphy.gif");

            for (sujet r : lis) {
                Beblio.setIds(r.getId_sujet());
                SujetService v = new SujetService();
                int x = v.getList3();

                System.err.println(x);
                Container ct = new Container();
                int height = Display.getInstance().convertToPixels(11.5f);
                int width = Display.getInstance().convertToPixels(14f);
                Image images = URLImage.createToStorage(enc, r.getImage(), Controller.ip+"/BackupWeb/piIntegration/web/images/" + r.getImage());
                Button image = new Button(images.fill(width, height));
                image.setUIID("Label");
                ct = BorderLayout.west(image);
                ct.setLeadComponent(image);
                TextArea ta = new TextArea(r.getContenu());
                ta.setUIID("NewsTopLine");
                ta.setEditable(false);

                Label likes = new Label(x + " Commentaires  ", "NewsBottomLine");
                likes.setTextPosition(RIGHT);
                if (!false) {
                    FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
                } else {
                    Style s = new Style(likes.getUnselectedStyle());
                    s.setFgColor(0xff2d55);
                    FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
                    likes.setIcon(heartImage);
                }

                FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

                ct.add(BorderLayout.CENTER,
                        BoxLayout.encloseY(
                                ta,
                                BoxLayout.encloseX(likes)
                        ));
                f.add(ct);
                image.addActionListener(e -> {
                    Beblio.setIds(r.getId_sujet());
                    Beblio.setImage1(r.getImage());
                    this.rootContainer.removeAll();

                    CommentaireController commentaireController = new CommentaireController();
                    commentaireController.initialize();
                    this.rootContainer.addComponent(BorderLayout.CENTER, commentaireController.getView());
                    this.rootContainer.revalidate();
                });

       
       
            }    
            f.getToolbar().setHidden(true);
            this.rootContainer.add(BorderLayout.NORTH, f);  
            this.rootContainer.revalidate();
        } catch (IOException ex) {
            
        }
 
 
 
        
    }
       
}
