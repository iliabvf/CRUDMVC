package net.iliabvf.javaio.controller;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.DeleteException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.UpdateException;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.repository.io.JavaIOAccountRepositoryImpl;
import net.iliabvf.javaio.service.AccountService;

import java.util.ArrayList;
import java.util.Map;

public class AccountController {
    AccountService accountService = new AccountService();

    public Long create(Long devID, ArrayList<Long> skillsIDsList) throws CreationException, ReadingException {
        return accountService.create(devID, skillsIDsList);
    }

    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        accountService.showAll(allDevs, allSkills);
    }

    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        accountService.deleteByID(accID);
    }

    public void update(Long accID, Long devID, ArrayList<Long> skillsIDsList) throws UpdateException, ReadingException {
        accountService.update(accID, devID, skillsIDsList);
    }

}
