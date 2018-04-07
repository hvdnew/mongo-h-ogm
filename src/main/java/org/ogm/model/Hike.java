package org.ogm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Hike {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String description;
    private Date date;
    private BigDecimal difficulty;
    @ManyToOne
    private Person organizer;
    @ElementCollection
    @OrderColumn(name = "sectionNo")
    private List<HikeSection> sections;

    public Hike() {
    }

    public Hike(String description, Date date, BigDecimal difficulty, HikeSection... hikeSections) {
        this.description = description;
        this.date = date;
        this.difficulty = difficulty;
        this.sections = new ArrayList<>(0);
        for (HikeSection section : hikeSections) {
            this.sections.add(section);
        }
    }

    public Hike(String id, String description, Date date, BigDecimal difficulty, Person organizer, List<HikeSection> sections) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.difficulty = difficulty;
        this.organizer = organizer;
        this.sections = sections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

    public List<HikeSection> getSections() {
        return sections;
    }

    public void setSections(List<HikeSection> sections) {
        this.sections = sections;
    }
}

