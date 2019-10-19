package net.iliabvf.javaio.service;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.DeleteException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.UpdateException;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.repository.io.JavaIOAccountRepositoryImpl;

import java.util.Map;

public class AccountService {

    AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();

    public Long create(Long devID, Long skillID) throws CreationException, ReadingException {
        return accountRepository.create(devID, skillID);
    }

    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        accountRepository.showAll(allDevs, allSkills);
    }

    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        accountRepository.deleteByID(accID);
    }

    public void update(Long accID, Long devID, Long skillID) throws UpdateException, ReadingException {
        accountRepository.update(accID, devID, skillID);
    }

}
