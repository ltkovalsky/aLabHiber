package com.example.alabhiber;

import com.example.alabhiber.db.entity.Document;
import com.example.alabhiber.db.entity.Person;

public class TestHelper {
    public static Person getPerson() {
        Person result = new Person();

        result.setFirstName("A");
        result.setMiddleName("B");
        result.setLastName("C");

        return result;
    }

    public static Document getDocument() {
        Document result = new Document();

        result.setType("passport");
        result.setDocNumber("060636123456");
        result.setArchived(false);

        return result;
    }
}
