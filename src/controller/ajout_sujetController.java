/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import service.SujetService;

/**
 *
 * @author ASUS
 */
class ajout_sujetController extends Controller {

     public ajout_sujetController()
    {
        super();
    }
    
    
    @Override
    public void initialize() {
      
        Button btn = new Button("Ajouter ce sujet");
        TextModeLayout tl = new TextModeLayout(3, 2);
Form f = new Form("Pixel Perfect", tl);
TextComponent title = new TextComponent().label("Titre");
TextComponent price = new TextComponent().label("#Tag");
TextComponent location = new TextComponent().label("Image");
TextComponent description = new TextComponent().label("Description").multiline(true);

f.add(tl.createConstraint().horizontalSpan(2), title);
f.add(tl.createConstraint().widthPercentage(30), price);
f.add(tl.createConstraint().widthPercentage(70), location);
f.add(tl.createConstraint().horizontalSpan(2), description);
f.getToolbar().setHidden(true);
f.add(btn);
this.rootContainer.add(BorderLayout.NORTH,f);

btn.addActionListener(e->{

SujetService v = new SujetService();
v.ajouter(title.getText(),description.getText());
                       this.rootContainer.removeAll();
                    
                SujetController sujetController = new SujetController();
                sujetController.initialize();
                this.rootContainer.addComponent(BorderLayout.CENTER, sujetController.getView());
                this.rootContainer.revalidate();
                

});

  Validator valid = new Validator();
        valid.addConstraint(title, new LengthConstraint(1)).addConstraint(price, new LengthConstraint(1))
                .addConstraint(location, new LengthConstraint(8)).addConstraint(description, new LengthConstraint(1));
        valid.addSubmitButtons(btn);
        valid.setShowErrorMessageForFocusedComponent(true);


        
        
    }
    
}
