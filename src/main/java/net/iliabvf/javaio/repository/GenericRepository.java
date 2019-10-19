package net.iliabvf.javaio.repository;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.DeleteException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.UpdateException;

import java.util.Map;

public interface GenericRepository<T,ID> {

    T getById(ID id) throws ReadingException;

    Map<ID,T> getAll() throws ReadingException;

    ID save(T t);

    void deleteById(ID id);

    ID create(String name) throws CreationException, ReadingException;

    ID create(Long devID, Long skillID) throws CreationException, ReadingException;

    void deleteAll();

    void showAll(Map allDevs, Map allSkills) throws ReadingException;

    void showAll() throws ReadingException;

    void deleteByID(Long accID) throws DeleteException, ReadingException;

    void update(Long accID, Long devID, Long skillID) throws UpdateException, ReadingException;
}
