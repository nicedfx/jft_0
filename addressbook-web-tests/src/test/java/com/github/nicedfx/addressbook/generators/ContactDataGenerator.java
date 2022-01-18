package com.github.nicedfx.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.GroupData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander commander = new JCommander(generator);
        try {
            if (args.length == 0) {
                throw new ParameterException("No parameters provided");
            }
            commander.parse(args);
        } catch (ParameterException e) {
            commander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if ("xml".equals(format)) {
            saveAsXml(contacts, new File(file));
        } else if ("json".equals(format)) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("FirstName %s", i))
                    .withMiddleName(String.format("MiddleName %s", i))
                    .withLastName(String.format("LastName %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHomePhone(String.format("+7 (921) 220507%s", i))
                    .withMobilePhone(String.format("557-89-2%s", i))
                    .withWorkPhone(String.format("8 800 111 22  3%s", i))
                    .withSecondPhone(String.format("8888     888%s", i))
                    .withEmail(String.format("thisIs%s@email.co   m", i))
                    .withEmail2(String.format("thisIs%s@email.com    ", i))
                    .withEmail3(String.format("   thisIs%s@email.com", i)));
        }
        return contacts;
    }
}
