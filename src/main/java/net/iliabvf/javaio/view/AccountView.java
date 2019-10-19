package net.iliabvf.javaio.view;

import net.iliabvf.javaio.*;
import net.iliabvf.javaio.controller.AccountController;
import net.iliabvf.javaio.model.Account;

import java.util.Map;

public class AccountView {

    AccountController accountController = new AccountController();

    public Long create(Long devID, Long skillID) throws CreationException, ReadingException {
        if (devID == 0)
            throw new CreationException(AppRunner.ERROR_CREATING_EMPTY_ID);

        Long accountID = accountController.create(devID, skillID);

        System.out.println("Account " + accountID + " succesfully created.");
        return accountID;

    }


    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        accountController.showAll(allDevs, allSkills);
    }

    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        accountController.deleteByID(accID);
    }

    public void update(Long accID, Long devID, Long skillID) throws UpdateException, ReadingException {
        accountController.update(accID, devID, skillID);
    }

    public void showByID(Long id){

    }

    public void showByID(Account id){

    }
}
