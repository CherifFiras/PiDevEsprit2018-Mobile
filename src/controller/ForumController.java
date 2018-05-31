/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Category;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Log.e;
import com.codename1.io.Storage;

import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.restfb.BinaryAttachment;
import java.io.IOException;
import java.util.ArrayList;
import com.restfb.types.FacebookType;
import com.restfb.FacebookClient;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;

import java.io.InputStream;













import service.ForumService;

/**
 *
 * @author hero
 */
public class ForumController extends Controller {
    EncodedImage enc ;
    Resources theme;
    private Label titre;
    private Button readmore;
    Slider starRank ;
    public ForumController()
    {
        super();
    }
    
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
}
private Slider createStarRankSlider(int i) {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        starRank.setProgress(i);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
 
    
    @Override
    public void initialize() {
        
 
                
                
                
        Form f;
        f = new Form();
        f.getToolbar().setHidden(true);
        theme = UIManager.initFirstTheme("/theme");
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        ForumService serviceTask = new ForumService();
        ArrayList<Category> lis = serviceTask.getList2();
        for (Category r : lis) {
            Slider s = createStarRankSlider((int) 4);
            s.setEditable(false);

            Container c1 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.y());
            Button btnn = new Button("partager");
            c1.getStyle().setBorder(Border.createUnderlineBorder(2));
            c1.getStyle().setMargin(1, 1, 1, 1);
            c1.getStyle().setPadding(15, 15, 0, 0);
            ImageViewer iv = new ImageViewer();
            System.out.println(r.getImage());
            iv.setImage(URLImage.createToStorage(enc, r.getImage(), Controller.ip+"/BackupWeb/piIntegration/web/images/" + r.getImage()).scaled(130, 160));
            Button b = new Button("View Category");
            Label l2 = new Label("Nom: " + r.getLibelle());
            c1.add(iv);
            c1.add(c3);

            c3.add(new SpanLabel("Description:" + r.getDescription()));
            c3.add(l2);

            c3.add(s);
            c3.add(b);
            c3.add(btnn);
            TextField tf = new TextField();
//               f1.add(c1);
            btnn.addActionListener(e -> {

                // ConnectionRequest request = new ConnectionRequest();
                // request.setUrl("https://graph.facebook.com//v3.0/page-id/feed");
                String token = "EAAB4Ydgv3yABAEjHZAX7hqbqfQjl0ZAkQFdqL5C5M4tgALBQmLAbdRGp5v4OSL7hrRoK1X7HPYLMMTj6vkipUXveQHp79LHRkSZB8swiy3YJXfxPFZCO2HfQSbYnZAsMQVgZCEgh9wZC443I6jwqmewawyAeXSGp3wwtYj3jbJC3ctUgv7Lr2Oht6t6DFJhaN3XLecHYqidZBgZDZD";
                FacebookClient fb = new DefaultFacebookClient(token);
                InputStream fis = null;
                System.err.println(r.getImage());
                try {
                    fis = Storage.getInstance().createInputStream(r.getImage());
                } catch (IOException ex) {

                }

                FacebookType rr = fb.publish("me/photos", FacebookType.class,
                        BinaryAttachment.with("photo.jpg", fis), Parameter.with("message", r.getLibelle()));
                System.out.println("fgkjqsioughfsdniohsdfidshvkdfj");

            });

            b.addActionListener((l) -> {
                Beblio.setIdc(r.getId_category());
                this.rootContainer.removeAll();

                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();

            });

            f.add(c1);

        }

        this.rootContainer.add(BorderLayout.NORTH, f);



        

    }
    
    
}
