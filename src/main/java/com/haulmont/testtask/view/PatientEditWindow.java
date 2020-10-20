package com.haulmont.testtask.view;

import com.haulmont.testtask.domain.Patient;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.service.impl.PatientServiceImpl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PatientEditWindow extends Window {

    private Button okButton;
    private Button cancelButton;
    private TextField surnameField;
    private TextField nameField;
    private TextField secondNameField;
    private TextField phoneNumberField;

    private PatientService patientService = new PatientServiceImpl();

    private VerticalLayout createLayout() {
        center();

        VerticalLayout layout = new VerticalLayout();
        this.surnameField = new TextField("Фамилия");
        this.nameField = new TextField("Имя");
        this.secondNameField = new TextField("Отчество");
        this.phoneNumberField = new TextField("Телефон");
        this.surnameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.nameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.secondNameField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));
        this.phoneNumberField.addValidator(new StringLengthValidator("Wrong length", 1, 255, false));

        this.okButton = new Button("Ok");
        this.cancelButton = new Button("Отмена");
        this.cancelButton.addClickListener(event -> close());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(this.okButton);
        horizontalLayout.addComponent(this.cancelButton);

        layout.addComponent(surnameField);
        layout.addComponent(nameField);
        layout.addComponent(secondNameField);
        layout.addComponent(phoneNumberField);
        layout.addComponent(horizontalLayout);

        return layout;
    }

    public PatientEditWindow(BeanItemContainer<Patient> container) {
        super("Добавить пациента");
        VerticalLayout layout = createLayout();

        this.okButton.addClickListener(event -> {
            this.surnameField.validate();
            this.nameField.validate();
            this.secondNameField.validate();
            this.phoneNumberField.validate();
            Patient patient = this.patientService.create(new Patient(
                    this.surnameField.getValue(),
                    this.nameField.getValue(),
                    this.secondNameField.getValue(),
                    this.phoneNumberField.getValue()));
            container.addBean(patient);
            close();
        });

        setContent(layout);
    }

    public PatientEditWindow(Patient patient, BeanItemContainer<Patient> container, Object selectedRow) {
        super("Редактировать пациента");
        VerticalLayout layout = createLayout();

        this.surnameField.setValue(patient.getSurname());
        this.nameField.setValue(patient.getName());
        this.secondNameField.setValue(patient.getSecondName());
        this.phoneNumberField.setValue(patient.getPhoneNumber());

        this.okButton.addClickListener(event -> {
            this.surnameField.validate();
            this.nameField.validate();
            this.secondNameField.validate();
            this.phoneNumberField.validate();
            Patient editedPatient = this.patientService.update(new Patient(
                    patient.getId(),
                    this.surnameField.getValue(),
                    this.nameField.getValue(),
                    this.secondNameField.getValue(),
                    this.phoneNumberField.getValue()));
            container.removeItem(selectedRow);
            container.addBean(editedPatient);
            close();
        });

        setContent(layout);
    }
}
