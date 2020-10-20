package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.service.impl.PatientServiceImpl;
import com.haulmont.testtask.service.impl.RecipeServiceImpl;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PatientsView extends VerticalLayout implements View {

    private final Grid grid;

    private final PatientService patientService = new PatientServiceImpl();
    private final RecipeService recipeService = new RecipeServiceImpl();

    public PatientsView() {
        Button addButton = new Button("Добавить");
        Button editButton = new Button("Изменить");
        Button deleteButton = new Button("Удалить");
        BeanItemContainer<Patient> container = new BeanItemContainer<>(Patient.class, patientService.getAll());
        this.grid = new Grid(container);
        this.grid.setSizeFull();
        this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.grid.setColumnOrder("id", "surname", "name", "secondName", "phoneNumber");
        addButton.addClickListener(event -> UI.getCurrent().addWindow(new PatientEditWindow(container)));
        editButton.addClickListener(event -> {
            BeanItem<Patient> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                UI.getCurrent().addWindow(new PatientEditWindow(item.getBean(), container, grid.getSelectedRow()));
            } else {
                Notification.show("Выберете строку для редактирования");
            }
        });
        deleteButton.addClickListener(event -> {
            BeanItem<Patient> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                if (recipeService.getByPatientId(item.getBean().getId()).isEmpty()) {
                    patientService.delete(item.getBean().getId());
                    container.removeItem(grid.getSelectedRow());
                } else {
                    Notification.show("Невозможно удалить запись. Существуют рецепты, связанные с пациентом");
                }
            } else {
                Notification.show("Выберете строку для удаления");
            }
        });

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addButton);
        layout.addComponent(editButton);
        layout.addComponent(deleteButton);

        addComponents(layout, this.grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
