package net.iliabvf.javaio.service;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.DeleteException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.exceptions.UpdateException;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.repository.io.JavaIOAccountRepositoryImpl;

import java.util.ArrayList;
import java.util.Map;

public class AccountService {

    AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();

    public Long create(Long devID, ArrayList<Long> skillsIDsList) throws CreationException, ReadingException {
        return accountRepository.create(devID, skillsIDsList);
    }

    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        accountRepository.showAll(allDevs, allSkills);
    }

    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        accountRepository.deleteByID(accID);
    }

    public void update(Long accID, Long devID, ArrayList<Long> skillsIDsList) throws UpdateException, ReadingException {
        accountRepository.update(accID, devID, skillsIDsList);
    }

}
