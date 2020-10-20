package com.haulmont.testtask.view;

import java.util.stream.Collectors;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.domain.RecipePriority;
import com.haulmont.testtask.dto.RecipeDTO;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.service.impl.DoctorServiceImpl;
import com.haulmont.testtask.service.impl.RecipeServiceImpl;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class RecipesView extends VerticalLayout implements View {

    private final Grid grid;

    private final RecipeService recipeService = new RecipeServiceImpl();

    public RecipesView() {
        Button addButton = new Button("Добавить");
        Button editButton = new Button("Изменить");
        Button deleteButton = new Button("Удалить");
        BeanItemContainer<RecipeDTO> container = new BeanItemContainer<>(
                RecipeDTO.class,
                recipeService.getAll().stream().map(RecipeDTO::new).collect(Collectors.toList()));
        this.grid = new Grid(container);
        this.grid.setSizeFull();
        this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.grid.setColumnOrder("id", "description", "createDate", "validity", "priority", "patient", "doctor");
        addButton.addClickListener(event -> UI.getCurrent().addWindow(new RecipesEditWindows(container)));
        editButton.addClickListener(event -> {
            BeanItem<RecipeDTO> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                UI.getCurrent().addWindow(new RecipesEditWindows(item.getBean(), container, grid.getSelectedRow()));
            } else {
                Notification.show("Выберете строку для редактирования");
            }
        });
        deleteButton.addClickListener(event -> {
            BeanItem<RecipeDTO> item = container.getItem(grid.getSelectedRow());
            if (item != null && item.getBean() != null) {
                recipeService.delete(item.getBean().getId());
                container.removeItem(grid.getSelectedRow());
            } else {
                Notification.show("Выберете строку для удаления");
            }
        });

        Grid.HeaderRow filterRow = grid.appendHeaderRow();
        addFilter(filterRow, container, "description");
        addFilter(filterRow, container, "priority");
        addFilter(filterRow, container, "patient");

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(addButton);
        layout.addComponent(editButton);
        layout.addComponent(deleteButton);
        addComponent(layout);
        addComponent(this.grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void addFilter(Grid.HeaderRow filterRow, BeanItemContainer<RecipeDTO> container, Object pid) {
        Grid.HeaderCell cell = filterRow.getCell(pid);

        // Have an input field to use for filter
        TextField filterField = new TextField();
        filterField.setColumns(8);
        filterField.setInputPrompt("Filter");
        filterField.addStyleName(ValoTheme.TEXTFIELD_TINY);

        // Update filter When the filter input is changed
        filterField.addTextChangeListener(change -> {
            // Can't modify filters so need to replace
            container.removeContainerFilters(pid);

            // (Re)create the filter if necessary
            if (! change.getText().isEmpty())
                container.addContainerFilter(
                        new SimpleStringFilter(pid,
                                change.getText(), true, false));
        });
        cell.setComponent(filterField);
    }
}
