package org.ogm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class State {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @ElementCollection(targetClass = String.class)
    private Set<String> attractions;

    public State() {
    }

    public State(String name) {
        this.name = name;
        this.attractions = new HashSet<>(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<String> attractions) {
        this.attractions = attractions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
