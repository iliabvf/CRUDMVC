package net.iliabvf.javaio.model;

public enum AccountStatus {

    ACTIVE(0L), BANNED(1L), DELETED(2L);

    private Long numVal;

    AccountStatus(Long numVal) {
        this.numVal = numVal;
    }

    public Long getNumVal() {
        return numVal;
    }

}
