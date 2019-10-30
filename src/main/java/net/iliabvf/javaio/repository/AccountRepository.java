package net.iliabvf.javaio.repository;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.DeleteException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.exceptions.UpdateException;
import net.iliabvf.javaio.model.Account;

import java.util.ArrayList;

public abstract class AccountRepository implements GenericRepository {

    abstract public Long create(Long devID, ArrayList<Long> skillsIDsList) throws CreationException,ReadingException;

    abstract public Long save(Account account);

    abstract public Account getById(Long id) throws ReadingException;

    abstract public void deleteByID(Long accID) throws DeleteException, ReadingException;

    abstract public void update(Long accID, Long devID, ArrayList<Long> skillsIDsList) throws UpdateException, ReadingException;
}


