package com.haulmont.testtask;

import com.haulmont.testtask.view.DoctorsView;
import com.haulmont.testtask.view.PatientsView;
import com.haulmont.testtask.view.RecipesView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    private Navigator navigator;

    private static final String PATIENTS = "patients";
    private static final String DOCTORS = "doctors";
    private static final String RECIPES = "recipes";

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        CssLayout menu = new CssLayout();
        menu.addStyleName(ValoTheme.MENU_PART);

        VerticalLayout layout = new VerticalLayout();
        Label label = new Label("Меню");
        label.addStyleName(ValoTheme.LABEL_H3);
        layout.addComponent(label);
        layout.addComponent(new MenuButton("Пациенты", PATIENTS));
        layout.addComponent(new MenuButton("Врачи", DOCTORS));
        layout.addComponent(new MenuButton("Рецепты", RECIPES));
        menu.addComponent(layout);
        mainLayout.addComponent(menu);

        Panel contentArea = new Panel();
        contentArea.addStyleName(ValoTheme.PANEL_BORDERLESS);
        contentArea.setSizeFull();
        mainLayout.addComponent(contentArea);
        mainLayout.setExpandRatio(contentArea, 1.0f);

        this.navigator = new Navigator(UI.getCurrent(), contentArea);

        this.navigator.addView(PATIENTS, PatientsView.class);
        this.navigator.addView(DOCTORS, DoctorsView.class);
        this.navigator.addView(RECIPES, RecipesView.class);
        this.navigator.navigateTo(PATIENTS);

        setContent(mainLayout);
    }

    class MenuButton extends Button {
        public MenuButton(String caption, String id) {
            super(caption);
            addStyleName(ValoTheme.MENU_ITEM);
            addClickListener(event -> navigator.navigateTo(id));
        }
    }
}