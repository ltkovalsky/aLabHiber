package com.example.alabhiber.db;

import com.example.alabhiber.TestHelper;
import com.example.alabhiber.db.entity.Document;
import com.example.alabhiber.db.entity.Person;
import com.example.alabhiber.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService service;

    @Test
    public void testSaveFetch() {
        service.save(TestHelper.getPerson());

        List<Person> c = service.findByLastName("C");
        assertThat(c.size()).isEqualTo(1);
        assertThat(c.get(0).getFirstName()).isEqualTo("A");
    }

    @Test
    @Transactional
    public void testSaveFetchWithDoc() {
        Person p = TestHelper.getPerson();
        Document d = TestHelper.getDocument();
        p.getDocuments().add(d);
        service.save(p);

        Optional<Person> result = service.findById(p.getId());

        assertThat(result.isPresent()).isTrue();
        List<Document> resultDocs = result.get().getDocuments();
        assertThat(resultDocs.size()).isEqualTo(1);
        assertThat(resultDocs.get(0).getDocNumber()).isEqualTo(d.getDocNumber());
        assertThat(resultDocs.get(0).getPerson()).isNotNull();
        assertThat(resultDocs.get(0).getPerson().getId()).isEqualTo(p.getId());
    }
}
