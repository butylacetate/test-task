package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.service.impl.DoctorServiceImpl;
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

public class DoctorsView extends VerticalLayout implements View {

    private final Grid grid;

    private final DoctorService doctorService = new DoctorServiceImpl();
    private final RecipeService recipeService = new RecipeServiceImpl();

    public DoctorsView() {
        Button addButton = new Button("Добавить");
        Button editButton = new Button("Изменить");
        Button deleteButton = new Button("Удалить");
        Button statButton = new Button("Показать статистику");
        BeanItemContainer<Doctor> container = new BeanItemContainer<>(Doctor.class, doctorService.getAll());
        this.grid = new Grid(container);
        this.grid.setSizeFull();
        this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.grid.setColumnOrder("id", "surname", "name", "secondName", "specialization");
        addButton.addClickListener(event -> UI.getCurrent().addWindow(new DoctorEditWindow(container)));
        editButton.addClickListener(event -> {
            BeanItem<Doctor> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                UI.getCurrent().addWindow(new DoctorEditWindow(item.getBean(), container, grid.getSelectedRow()));
            } else {
                Notification.show("Выберете строку для редактирования");
            }
        });
        deleteButton.addClickListener(event -> {
            BeanItem<Doctor> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                if (recipeService.getByDoctorId(item.getBean().getId()).isEmpty()) {
                    doctorService.delete(item.getBean().getId());
                    container.removeItem(grid.getSelectedRow());
                } else {
                    Notification.show("Невозможно удалить запись. Существуют рецепты, связанные с врачом");
                }
            } else {
                Notification.show("Выберете строку для удаления");
            }
        });
        statButton.addClickListener(event -> UI.getCurrent().addWindow(new DoctorsStatsWindow()));

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addButton);
        layout.addComponent(editButton);
        layout.addComponent(deleteButton);
        layout.addComponent(statButton);
        addComponent(layout);
        addComponent(this.grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
