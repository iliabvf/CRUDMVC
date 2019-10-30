package net.iliabvf.javaio.model;

public class Skill extends BaseEntity {

    public Skill(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.name;
    }

    public String getName() {
        return this.name;
    }

    public Long getID() {
        return this.id;
    }



}
