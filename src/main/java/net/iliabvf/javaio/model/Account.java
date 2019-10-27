package net.iliabvf.javaio.model;

import java.util.ArrayList;

public class Account extends BaseEntity {
    private AccountStatus status;
    private Long developerID;
    private ArrayList<Long> skillsIDsList;

    public Account(AccountStatus status, Long developerID, ArrayList<Long> skillsIDsList, Long ID) {
        this.status = status;
        this.developerID = developerID;
        this.skillsIDsList = skillsIDsList;
        this.ID = ID;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder(this.status.getNumVal() + ":" + this.developerID + ":");

        for (int i = 0; i<this.skillsIDsList.size(); i++) {
            result.append (((i == 0) ? "" : ";") + this.skillsIDsList.get(i));
        }

        result.append(":" + this.ID);

        return result.toString();
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public Long getDeveloperID() {
        return this.developerID;
    }

    public ArrayList<Long> getskillsIDsList() {
        return this.skillsIDsList;
    }

    public Long getID() {
        return this.ID;
    }

}
