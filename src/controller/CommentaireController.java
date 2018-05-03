/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.components.SpanLabel;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class CommentaireController extends Controller{
public CommentaireController()
    {
        super();
    }

    @Override
    public void initialize() {
     
        
         Form f;
            f = new Form();
            Container list = new Container(BoxLayout.y());
            list.setScrollableY(true);
        Container list1 = new Container(BoxLayout.x());
        
      
        Button b = new Button("hello");
        Button b2 = new Button("byeeeeeeeee");
        list1.add(b2);
        list.add(b);
        list.add(list1);
          f.add(list);
          f.show();
    }
    
}
