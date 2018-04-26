/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.codename1.main.Controller;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author hero
 */
public class FriendListController extends Controller {

    private BoxLayout friendList;
    
    @Override
    public void initialize() {
       
        friendList = BoxLayout.y();
    }
    
}
