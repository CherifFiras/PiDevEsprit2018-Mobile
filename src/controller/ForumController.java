/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.main.Controller;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author hero
 */
public class ForumController extends Controller {

    public ForumController()
    {
        super();
    }
    
    @Override
    public void initialize() {
        this.rootContainer.removeAll();
        
        
        this.rootContainer.add(BorderLayout.SOUTH, "ttttttttttttttttttttttttttt");
        
        
        
        
        this.rootContainer.revalidate();
    }
    
}
