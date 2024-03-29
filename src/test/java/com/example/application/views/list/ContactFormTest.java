package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.fasterxml.classmate.Annotations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact marcUsher;
    private Contact aaaaaaa;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @Before
    public void setupData() {
        companies = new ArrayList<>();
        company1 = new Company();
        company1.setName("Vaadin Ltd");
        company2 = new Company();
        company2.setName("IT Mill");
        companies.add(company1);
        companies.add(company2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher = new Contact();
        marcUsher.setFirstName("Marc");
        marcUsher.setLastName("Usher");
        marcUsher.setEmail("marc@usher.com");
        marcUsher.setStatus(status1);
        marcUsher.setCompany(company2);
    }
    @Test
    public void formFieldsPopulatedByCom() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setCompany(company2);
        Assert.assertNotEquals(company2, form.company.getValue());

    }


    @Test
    public void formFieldsPopulatedBySt() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setStatus(status1);
        Assert.assertNotEquals(status1, form.status.getValue());

    }
    
        @Test
    public void formFieldsPopulatedByCom2() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setCompany(company2);
        Assert.assertNotEquals(company2, form.company.getValue());

    }


    @Test
    public void formFieldsPopulatedBySt2() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setStatus(status1);
        Assert.assertNotEquals(status1, form.status.getValue());

    }

    @Test
    public void formFieldsPopulated() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setContact(marcUsher);
        Assert.assertEquals("Marc", form.firstName.getValue());
        Assert.assertEquals("Usher", form.lastName.getValue());
        Assert.assertEquals("marc@usher.com", form.email.getValue());
        Assert.assertEquals(company2, form.company.getValue());
        Assert.assertEquals(status1, form.status.getValue());
    }

    @Test
    public void NotformFieldsPopulated() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setContact(aaaaaaa);
        Assert.assertNotEquals("Marc", form.firstName.getValue());
        Assert.assertNotEquals("Usher", form.lastName.getValue());
        Assert.assertNotEquals("marc@usher.com", form.email.getValue());
        Assert.assertNotEquals(company2, form.company.getValue());
        Assert.assertNotEquals(status1, form.status.getValue());
    }
    
        @Test
    public void NotformFieldsPopulated2() {
        ContactForm form = new ContactForm(companies, statuses);
        form.setContact(aaaaaaa);
        Assert.assertNotEquals("Marc", form.firstName.getValue());
        Assert.assertNotEquals("Usher", form.lastName.getValue());
        Assert.assertNotEquals("marc@usher.com", form.email.getValue());
        Assert.assertNotEquals(company2, form.company.getValue());
        Assert.assertNotEquals(status1, form.status.getValue());
    }


    
        @Test
    public void saveEventHasCorrectValues() {
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        form.setContact(contact);
        form.firstName.setValue("Ser");
        form.lastName.setValue("Gor");
        form.company.setValue(company1);
        form.email.setValue("gor@doe.com");
        form.status.setValue(status2);

        AtomicReference<Contact> savedContactRef = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> {
            savedContactRef.set(e.getContact());
        });
        form.save.click();
        Contact savedContact = savedContactRef.get();

        Assert.assertNotEquals("John", savedContact.getFirstName());
        Assert.assertNotEquals("Doe", savedContact.getLastName());
        Assert.assertNotEquals("john@doe.com", savedContact.getEmail());
        Assert.assertEquals(company1, savedContact.getCompany());
        Assert.assertEquals(status2, savedContact.getStatus());
    }

    @Test
    public void DelEventHasCorrectValues() {
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        form.setContact(contact);
        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.company.setValue(company1);
        form.email.setValue("john");
        form.status.setValue(status2);

        AtomicReference<Contact> delContactRef = new AtomicReference<>(null);

        form.addListener(ContactForm.SaveEvent.class, e -> {
            delContactRef.set(e.getContact());
        });
        form.delete.click();

        Contact delContact = delContactRef.get();


    }


}
