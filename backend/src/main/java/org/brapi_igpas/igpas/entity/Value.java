package org.brapi_igpas.igpas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "values")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
    @Id
    private long id;
    private String value;
    @Column(name = "study_id")
    private long studyId;
    @Column(name = "attribute_id")
    private int attributeId;

    public Value() {
    }

    public Value(long id, String value) {
        this();
        this.id = id;
        this.value = value;
    }

    public Value(long id, String value, long studyId) {
        this(id, value);
        this.studyId = studyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getStudyId() {
        return studyId;
    }

    public void setStudyId(long studyId) {
        this.studyId = studyId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }
}
