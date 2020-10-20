package com.haulmont.testtask.view;

import java.util.stream.Collectors;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.domain.Recipe;
import com.haulmont.testtask.domain.RecipePriority;
import com.haulmont.testtask.dto.RecipeDTO;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.service.RecipeService;
import com.haulmont.testtask.service.impl.DoctorServiceImpl;
import com.haulmont.testtask.service.impl.PatientServiceImpl;
import com.haulmont.testtask.service.impl.RecipeServiceImpl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RecipesEditWindows extends Window {

    private Button okButton;
    private Button cancelButton;
    private TextField descriptionField;
    private TextField validityField;
    private ComboBox priorityBox;
    private ComboBox patientBox;
    private ComboBox doctorBox;

    private final PatientService patientService = new PatientServiceImpl();
    private final DoctorService doctorService = new DoctorServiceImpl();
    private final RecipeService recipeService = new RecipeServiceImpl();

    private VerticalLayout createLayout() {
        center();

        VerticalLayout layout = new VerticalLayout();
        this.descriptionField = new TextField("Описание");
        this.validityField = new TextField("Срок действия", new ObjectProperty<Integer>(0));
        this.priorityBox = new ComboBox("Приоритет");
        this.priorityBox.addItem(RecipePriority.NORMAL);
        this.priorityBox.addItem(RecipePriority.CITO);
        this.priorityBox.addItem(RecipePriority.STATIM);
        BeanItemContainer<Patient> patientContainer = new BeanItemContainer<>(Patient.class);
        patientContainer.addAll(patientService.getAll());
        this.patientBox = new ComboBox("Пациент", patientContainer);
        this.patientBox.setItemCaptionPropertyId("surname");
        BeanItemContainer<Doctor> doctorContainer = new BeanItemContainer<>(Doctor.class);
        doctorContainer.addAll(doctorService.getAll());
        this.doctorBox = new ComboBox("Врач", doctorContainer);
        this.doctorBox.setItemCaptionPropertyId("surname");
        this.descriptionField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.validityField.addValidator(new IntegerRangeValidator("Wrong validity", 1, 365));
        this.priorityBox.addValidator(new NullValidator("Should be not null", false));
        this.patientBox.addValidator(new NullValidator("Should be not null", false));
        this.doctorBox.addValidator(new NullValidator("Should be not null", false));

        this.okButton = new Button("Ok");
        this.cancelButton = new Button("Отмена");
        this.cancelButton.addClickListener(event -> close());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(this.okButton);
        horizontalLayout.addComponent(this.cancelButton);

        layout.addComponent(descriptionField);
        layout.addComponent(validityField);
        layout.addComponent(priorityBox);
        layout.addComponent(patientBox);
        layout.addComponent(doctorBox);
        layout.addComponent(horizontalLayout);

        return layout;
    }

    public RecipesEditWindows(BeanItemContainer<RecipeDTO> container) {
        super("Добавить рецепт");
        VerticalLayout layout = createLayout();

        this.okButton.addClickListener(event -> {
            this.descriptionField.validate();
            this.validityField.validate();
            this.priorityBox.validate();
            this.patientBox.validate();
            this.doctorBox.validate();

            Recipe recipe = recipeService.create(new Recipe(
                    descriptionField.getValue(),
                    Integer.parseInt(validityField.getValue()),
                    (RecipePriority) priorityBox.getValue(),
                    (Patient) patientBox.getValue(),
                    (Doctor) doctorBox.getValue()
            ));
            container.addBean(new RecipeDTO(recipe));
            close();
        });

        setContent(layout);
    }

    public RecipesEditWindows(RecipeDTO recipeDTO, BeanItemContainer<RecipeDTO> container, Object selectedRow) {
        super("Редактировать рецепт");
        VerticalLayout layout = createLayout();

        this.descriptionField.setValue(recipeDTO.getDescription());
        this.validityField.setValue(String.valueOf(recipeDTO.getValidity()));
        this.priorityBox.setValue(recipeDTO.getPriority());

        this.okButton.addClickListener(event -> {
            this.descriptionField.validate();
            this.validityField.validate();
            this.priorityBox.validate();
            this.patientBox.validate();
            this.doctorBox.validate();

            Recipe recipe = recipeService.update(new Recipe(
                    recipeDTO.getId(),
                    descriptionField.getValue(),
                    Integer.parseInt(validityField.getValue()),
                    (RecipePriority) priorityBox.getValue(),
                    (Patient) patientBox.getValue(),
                    (Doctor) doctorBox.getValue()
            ));
            container.removeItem(selectedRow);
            container.addBean(new RecipeDTO(recipe));
            close();
        });

        setContent(layout);
    }
}
