package org.brapi_igpas.igpas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studies")
public class StudiesEntity {
    @Id
    private long id;
    private String title;
    private String description;
    @Column(name = "investigation_id")
    private int investigationId;

    public StudiesEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(int investigationId) {
        this.investigationId = investigationId;
    }
}
