package net.iliabvf.javaio.controller;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.DeleteException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.UpdateException;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.repository.io.JavaIOAccountRepositoryImpl;
import net.iliabvf.javaio.service.AccountService;

import java.util.Map;

public class AccountController {
    AccountService accountService = new AccountService();

    public Long create(Long devID, Long skillID) throws CreationException, ReadingException {
        return accountService.create(devID, skillID);
    }

    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        accountService.showAll(allDevs, allSkills);
    }

    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        accountService.deleteByID(accID);
    }

    public void update(Long accID, Long devID, Long skillID) throws UpdateException, ReadingException {
        accountService.update(accID, devID, skillID);
    }

}