package net.iliabvf.javaio.model;

public class Skill extends BaseEntity {

    public Skill(Long ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return this.ID + ":" + this.name;
    }

    public String getName() {
        return this.name;
    }

    public Long getID() {
        return this.ID;
    }



}
