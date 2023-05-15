package com.example.alabhiber.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@ToString
@EqualsAndHashCode
public class Document {
    @Id
    @Getter
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String docNumber;

    @Getter
    @Setter
    private boolean archived;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
