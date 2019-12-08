package org.planth_pheno_analytics.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attributes")
public class AttributeEntity {
    @Id
    private Integer id;
    private String name;
    @Column(name = "display_position")
    private int displayPosition;
    @Column(name = "displayed_name")
    private String displayedName;
    @Column(name = "is_value_attribute")
    private boolean isValueAttribute;
    @Column(name = "attribute_group_id")
    private int attributeGroupId;

    public AttributeEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(int displayPosition) {
        this.displayPosition = displayPosition;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public boolean isValueAttribute() {
        return isValueAttribute;
    }

    public void setValueAttribute(boolean valueAttribute) {
        isValueAttribute = valueAttribute;
    }

    public int getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(int attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }
}
