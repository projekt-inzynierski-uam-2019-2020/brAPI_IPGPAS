package org.brapi_igpas.igpas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "values")
public class ValuesEntity {
    @Id
    private long id;
    private String value;
    @Column(name = "study_id")
    private long studyId;
    @Column(name = "attribute_id")
    private int attributeId;

    public ValuesEntity() {
    }

    public ValuesEntity(long id, String value) {
        this();
        this.id = id;
        this.value = value;
    }

    public ValuesEntity(long id, String value, long studyId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuesEntity that = (ValuesEntity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
