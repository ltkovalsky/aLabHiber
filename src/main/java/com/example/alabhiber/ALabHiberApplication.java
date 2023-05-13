package com.example.alabhiber;

import com.example.alabhiber.db.entity.Document;
import com.example.alabhiber.db.entity.Person;
import com.example.alabhiber.service.DocumentService;
import com.example.alabhiber.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ALabHiberApplication implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private DocumentService documentService;

    public static void main(String[] args) {
        SpringApplication.run(ALabHiberApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        prepareDB();    // too lazy for creating csv for liqui :)

        // if we need person first, also showcase of @Query
        List<Person> personFetchResult = personService.findAllByActiveDocNumberLike("777");
        List<Document> docFetchResult = documentService.findAllByActiveDocNumLike("777");   // for task

        docFetchResult.forEach(doc -> {
            Person p = doc.getPerson();
            System.out.println("---");
            System.out.printf("%s %s %s, \n Document type: %s, number: %s%n",
                    p.getFirstName(), p.getMiddleName(), p.getLastName(), doc.getType(), doc.getDocNumber());
        });
        System.out.println("---");

    }

    private void prepareDB() {
        List<Person> persons = generatePersons();
        Person p = new Person();
        p.setFirstName("First");
        p.setMiddleName("Middle");
        p.setLastName("Last");

        Document d = new Document();
        d.setArchived(false);
        d.setType("passport");
        d.setDocNumber("060645777012");
        p.getDocuments().add(d);
        persons.add(p);
        personService.saveAll(persons);
    }

    private List<Person> generatePersons() {
        List<Person> result = new LinkedList<>();

        String[] letters = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "O", "P", "A", "S", "D", "F", "G"};
        Random rand = new Random();
        Arrays.stream(letters).forEach(l -> {
            Person p = new Person();
            p.setFirstName(l);
            p.setMiddleName(l + l);
            p.setLastName(l + l + l);
            p.getDocuments().addAll(generateDocs(rand.nextInt(9)));
            result.add(p);
        });

        return result;
    }

    private List<Document> generateDocs(int count) {
        List<Document> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Document d = new Document();
            d.setType(Math.random() > 0.5 ? "passport" : "other");
            d.setDocNumber(String.valueOf(new Random().nextInt()));
            d.setArchived(Math.random() > 0.7);
            result.add(d);
        }
        return result;
    }
}
