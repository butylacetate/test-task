package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.impl.DoctorServiceImpl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DoctorEditWindow extends Window {

    private Button okButton;
    private Button cancelButton;
    private TextField surnameField;
    private TextField nameField;
    private TextField secondNameField;
    private TextField specializationField;

    private DoctorService doctorService = new DoctorServiceImpl();

    private VerticalLayout createLayout() {
        center();

        VerticalLayout layout = new VerticalLayout();
        this.surnameField = new TextField("Фамилия");
        this.nameField = new TextField("Имя");
        this.secondNameField = new TextField("Отчество");
        this.specializationField = new TextField("Специализация");
        this.surnameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.nameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.secondNameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.specializationField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));

        this.okButton = new Button("Ok");
        this.cancelButton = new Button("Отмена");
        this.cancelButton.addClickListener(event -> close());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(this.okButton);
        horizontalLayout.addComponent(this.cancelButton);

        layout.addComponent(surnameField);
        layout.addComponent(nameField);
        layout.addComponent(secondNameField);
        layout.addComponent(specializationField);
        layout.addComponent(horizontalLayout);

        return layout;
    }

    public DoctorEditWindow(BeanItemContainer<Doctor> container) {
        super("Добавить доктора");
        VerticalLayout layout = createLayout();

        this.okButton.addClickListener(event -> {
            this.surnameField.validate();
            this.nameField.validate();
            this.secondNameField.validate();
            this.specializationField.validate();
            Doctor doctor = this.doctorService.create(new Doctor(
                    this.surnameField.getValue(),
                    this.nameField.getValue(),
                    this.secondNameField.getValue(),
                    this.specializationField.getValue()));
            container.addBean(doctor);
            close();
        });

        setContent(layout);
    }

    public DoctorEditWindow(Doctor doctor, BeanItemContainer<Doctor> container, Object selectedRow) {
        super("Редактировать доктора");
        VerticalLayout layout = createLayout();

        this.surnameField.setValue(doctor.getSurname());
        this.nameField.setValue(doctor.getName());
        this.secondNameField.setValue(doctor.getSecondName());
        this.specializationField.setValue(doctor.getSpecialization());

        this.okButton.addClickListener(event -> {
            this.surnameField.validate();
            this.nameField.validate();
            this.secondNameField.validate();
            this.specializationField.validate();
            Doctor editedDoc = this.doctorService.update(new Doctor(
                    doctor.getId(),
                    this.surnameField.getValue(),
                    this.nameField.getValue(),
                    this.secondNameField.getValue(),
                    this.specializationField.getValue()));
            container.removeItem(selectedRow);
            container.addBean(editedDoc);
            close();
        });

        setContent(layout);
    }
}
