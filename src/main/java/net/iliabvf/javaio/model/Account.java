package net.iliabvf.javaio.model;

public class Account extends BaseEntity {
    private AccountStatus status;
    private Long developerID;
    private Long skillID;

    public Account(AccountStatus status, Long developerID, Long skillID, Long ID) {
        this.status = status;
        this.developerID = developerID;
        this.skillID = skillID;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return this.status.getNumVal() + ":" + this.developerID + ":" + this.skillID + ":" + this.ID;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public Long getDeveloperID() {
        return this.developerID;
    }

    public Long getSkillID() {
        return this.skillID;
    }

    public Long getID() {
        return this.ID;
    }

}
