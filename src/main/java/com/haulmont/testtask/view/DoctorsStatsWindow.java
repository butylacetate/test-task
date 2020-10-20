package com.haulmont.testtask.view;

import com.haulmont.testtask.dto.DoctorStatsDTO;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.impl.DoctorServiceImpl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Window;

public class DoctorsStatsWindow extends Window {

    private final DoctorService doctorService = new DoctorServiceImpl();

    public DoctorsStatsWindow() {
        super("Статистика");
        setWidth("50%");
        setHeight("50%");
        center();
        setClosable(true);
        BeanItemContainer<DoctorStatsDTO> container = new BeanItemContainer<>(DoctorStatsDTO.class, doctorService.getAllWithStats());
        Grid grid = new Grid(container);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setColumnOrder("id", "surname", "name", "secondName", "recipeCount");

        setContent(grid);
    }
}
