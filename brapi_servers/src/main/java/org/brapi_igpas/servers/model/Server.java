package org.brapi_igpas.servers.model;

import javax.persistence.*;

@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;


    public Server(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Server() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Servers [id=" +id + ", name=" + name + ", link=" + link + "]";
    }
}
