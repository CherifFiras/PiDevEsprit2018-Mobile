/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Category;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;

import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

import service.ForumService;

/**
 *
 * @author hero
 */
public class ForumController extends Controller {
    EncodedImage enc ;
    private Label titre;
    private Button readmore;
    public ForumController()
    {
        super();
    }
    
    @Override
    public void initialize() {
        try {
            this.rootContainer.removeAll();
            
            
            Form f;
            SpanLabel lb;
            f = new Form();
            lb = new SpanLabel("");
            Container list = new Container(BoxLayout.y());
            list.setScrollableY(true);
            ForumService serviceTask=new ForumService();
            ArrayList<Category> lis=serviceTask.getList2();
            
            enc = EncodedImage.create("/giphy.gif");
            
            for (Category r : lis) {
                
                Container ct = new Container();
                titre = new Label();
                Image image = URLImage.createToStorage(enc,r.getImage(),"http://127.0.0.1/piIntegration/web/images/"+r.getImage());
                ct.add(image);
                readmore=new Button("Read More");
                readmore.addActionListener((evt) -> {
                    Beblio.setIdc(r.getId_category());
                       this.rootContainer.removeAll();
                    
                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();
                
                });
                titre.setText(r.getLibelle());
                ct.add(titre);
                ct.add(readmore);
                f.add(ct);
            }
            
            f.getToolbar().setVisible(false);
            this.rootContainer.add(BorderLayout.NORTH, f);
            
            
            
            
            this.rootContainer.revalidate();
        } catch (IOException ex) {
            
        }
    }
    
}
