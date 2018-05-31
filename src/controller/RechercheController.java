/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.User;
import com.codename1.main.Controller;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import java.util.List;
import service.APIService;
import service.TheMovieDBAPI;
import service.UtilService;

/**
 *
 * @author hero
 */
public class RechercheController extends Controller{

    private Container rechercheContainer;
    private final APIService apiService = APIService.getInstance();
    private final UtilService utilService = UtilService.getInstance();
    private final Font nameFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    private final Font messageFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    private AutoCompleteTextField occupationAutoComplete;
    private AutoCompleteTextField paysAutoComplete;
    private AutoCompleteTextField movieAutoComplete;
    private AutoCompleteTextField serieAutoComplete;
    private Picker sexePicker;
    
    @Override
    public void initialize() {
        rechercheContainer = new Container(BoxLayout.y());
        rechercheContainer.add(genreField());
        rechercheContainer.add(paysField());
        rechercheContainer.add(occupationField());
        rechercheContainer.add(movieField());
        rechercheContainer.add(serieField());
        Button rechercheButton = new Button("Rechercher");
        rechercheButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            rechercheResult();
        });
        rechercheContainer.add(rechercheButton);
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,rechercheContainer);
        this.rootContainer.revalidate();
    }
    
    
    private void rechercheResult()
    {
        String g = "";
        Container parent = new Container(BoxLayout.y());
        Container topChild = new Container(BoxLayout.x());
        Label label = new Label("Result de recherche");
        Button backButton = new Button("retour");
        backButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            this.rootContainer.removeAll();
            this.rootContainer.add(BorderLayout.NORTH,rechercheContainer);
            this.rootContainer.revalidate();
        });
        topChild.addAll(label,backButton);
        Container child = new Container(BoxLayout.y());
        child.setScrollableY(true);
        if("Homme".equals(sexePicker.getText()))
            g="M";
        if("Femme".equals(sexePicker.getText()))
            g="F";
        
        List<User> users = apiService.searchResult(g,paysAutoComplete.getText(), occupationAutoComplete.getText(), movieAutoComplete.getText(), serieAutoComplete.getText());
        for(User user:users)
            child.add(resultItem(user));
        parent.add(topChild);
        parent.add(child);
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,parent);
        this.rootContainer.revalidate();
    }
    
    private Container resultItem(User user)
    {
        Container container = new Container(BoxLayout.x());
        container.add(getRoundImage(utilService.getImageFromURL(user.getImage())));
        Label messageUser = new Label(user.getNom()+" "+user.getPrenom());
        messageUser.getUnselectedStyle().setFont(messageFont);
        container.add(messageUser);
        Container children = new Container(BoxLayout.y());
        Button demandeButton = new Button("demande");
        if(apiService.checkUser(user.getId()))
            demandeButton.setEnabled(false);
        Button profilButton = new Button("voir profile");
        children.add(demandeButton);
        children.add(profilButton);
        demandeButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            apiService.demandeUser(user.getId());
            demandeButton.setEnabled(false);
            Dialog.show("Demande", "Votre demande a été envoyé", "OK","OK");
        });
        profilButton.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            AutreProfilController autreProfilController = new AutreProfilController();
            autreProfilController.setIdAutreUser(user.getId().toString());
            autreProfilController.initialize();
            this.rootContainer.removeAll();
            this.rootContainer.add(BorderLayout.NORTH,autreProfilController.getView());
            this.rootContainer.revalidate();
        });
        container.add(children);
        return container;
    }
    
    private Container occupationField()
    {
        occupationAutoComplete = new AutoCompleteTextField(apiService.getOccupations().toArray(new String[0]));
        Container container = new Container(BoxLayout.x());
        Label fieldName = new Label("Occupation");
        fieldName.getUnselectedStyle().setFont(nameFont);
        container.addAll(fieldName,occupationAutoComplete);
        return container;
    }
    
    private Container genreField()
    {
        String[] sexevalues = { "Homme", "Femme"};
        sexePicker = new Picker();
        sexePicker.setStrings(sexevalues);
        Container container = new Container(BoxLayout.x());
        Label fieldName = new Label("Genre");
        fieldName.getUnselectedStyle().setFont(nameFont);
        container.addAll(fieldName,sexePicker);
        return container;
    }
    
    private Container paysField()
    {
        paysAutoComplete = new AutoCompleteTextField(apiService.getPays().toArray(new String[0]));
        Container container = new Container(BoxLayout.x());
        Label fieldName = new Label("Pays");
        fieldName.getUnselectedStyle().setFont(nameFont);
        container.addAll(fieldName,paysAutoComplete);
        return container;
    }
    
    private Container movieField()
    {
        final DefaultListModel<String> options = new DefaultListModel<>();
        TheMovieDBAPI api = new TheMovieDBAPI(TheMovieDBAPI.Movie);
        movieAutoComplete = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = api.fetchResult(text).toArray(new String[0]);
                if (l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }

        };
        Container container = new Container(BoxLayout.x());
        Label fieldName = new Label("Films");
        fieldName.getUnselectedStyle().setFont(nameFont);
        container.addAll(fieldName,movieAutoComplete);
        return container;
    }
    
    private Container serieField() {
        final DefaultListModel<String> options = new DefaultListModel<>();
        TheMovieDBAPI api = new TheMovieDBAPI(TheMovieDBAPI.Serie);
        serieAutoComplete = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = api.fetchResult(text).toArray(new String[0]);
                if (l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }

        };
        Container container = new Container(BoxLayout.x());
        Label fieldName = new Label("Series");
        fieldName.getUnselectedStyle().setFont(nameFont);
        container.addAll(fieldName,serieAutoComplete);
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
