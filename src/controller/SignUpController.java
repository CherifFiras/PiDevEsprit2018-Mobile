/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.capture.Capture;
import com.codename1.main.Controller;
import com.codename1.main.MainView;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;



/**
 *
 * @author ASUS
 */
public class SignUpController extends Controller {

private Resources theme;

   
    public SignUpController  ()
    {
        super();
         
    }
    

    @Override
    public void initialize()  {

 
        
        this.rootContainer.removeAll();
        this.rootContainer.setUIID("SignUpForm");
        this.theme = theme;
        theme = UIManager.initFirstTheme("/theme");
       

       Container north = new Container(new FlowLayout(Component.CENTER));
        Button photobutton = new Button("" , theme.getImage("profile-photo-button.png") );
        photobutton.getIcon();
        photobutton.setUIID("SignUpPhotoButton");
        north.addComponent(photobutton);
        
        photobutton.addActionListener(new ActionListener() {
        Form mainForm = new Form();
       
            @Override
            public void actionPerformed(ActionEvent evt) {
               
             String i=   Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
             if(i != null)
             {
                 Image img;
                 try {
                     img = Image.createImage(i);
                     
                 } catch (IOException ex) {
                  
                 }
             ;
             
             }
            }
          
        });
        
         this.rootContainer.add(BorderLayout.NORTH, north);
  
         

         
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
        center.setUIID("SignUpCenter");
        Container row1 = new Container(new GridLayout(1,2));
        TextField firstname = new TextField();
        firstname.setHint("Nom");
        firstname.getHintLabel().setUIID("SignUpField");
        firstname.setUIID("SignUpField");
        TextField lastname = new TextField();
        lastname.setHint("Prenom");
        lastname.getHintLabel().setUIID("SignUpField");
        lastname.setUIID("SignUpField");
        row1.addComponent(firstname);
         row1.addComponent(lastname);
         center.addComponent(row1);
         center.setScrollableY(true);
         TextField email = new TextField();
         center.addComponent(email);
         email.setHint("E-mail");
         email.getHintLabel().setUIID("SignUpField");
         email.setUIID("SignUpField");
         TextField password = new TextField();
         password.setConstraint(TextField.PASSWORD);
         password.setHint("Password");
         password.getHintLabel().setUIID("SignUpField");
         password.setUIID("SignUpField");
         center.addComponent(password);

 Container row4 = new Container(new BorderLayout()); 
        Label code = new Label("+216");
        row4.addComponent(BorderLayout.WEST,code);
        TextField phonenumber = new TextField();
        phonenumber.setHint("Phone Number");
        phonenumber.getHintLabel().setUIID("SignUpField");
         phonenumber.setUIID("SignUpField");
         code.setUIID("SignUpField");
        row4.addComponent(BorderLayout.CENTER,phonenumber);
 center.addComponent(row4);
  this.rootContainer.add(BorderLayout.CENTER, center);
  
  Button getstarted = new Button("Get Started" , theme.getImage("entrer.png"));
  getstarted.setUIID("SignUpButton");
  getstarted.setGap(getstarted.getStyle().getFont().getHeight());
  getstarted.setTextPosition(Component.LEFT);
  this.rootContainer.add(BorderLayout.SOUTH, getstarted);
  
  
  getstarted.addActionListener(new ActionListener() {
        Form mainForm = new Form();
       
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (firstname.getText().isEmpty())
               { Dialog.show("Error", "Nom invalide !", "Ok", null); }
               else if (lastname.getText().isEmpty())
               {Dialog.show("Error", "Prenom invalide !", "Ok", null);} 
               else if (password.getText().isEmpty())
               {Dialog.show("Error", "Mot de passe vide !", "Ok", null);} 
               else if (email.getText().isEmpty())
               {Dialog.show("Error", "Email Incorrect  !", "Ok", null);} 
               else {
                  
                   mainForm.setLayout(new BorderLayout());
                mainForm.getContentPane().removeAll();
                ajouter_compteController forumController = new ajouter_compteController();
                forumController.add_user(firstname.getText(), password.getText(), firstname.getText(),email.getText(), lastname.getText());
                MainView m = new MainView();
                
                 m.start();
                 
                
               }
    
            }
          
        });
  
 
        this.rootContainer.revalidate();
    }
    
}
