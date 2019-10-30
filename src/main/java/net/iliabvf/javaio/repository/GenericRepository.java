package net.iliabvf.javaio.repository;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.DeleteException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.exceptions.UpdateException;
import net.iliabvf.javaio.model.Account;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.model.Skill;

import java.util.ArrayList;
import java.util.Map;

public interface GenericRepository<T,ID> {

    Map<ID,T> getAll() throws ReadingException;

    default void deleteAll(){

    }

    default void showAll(Map allDevs, Map allSkills) throws ReadingException{

    }

    default ID create(String name) throws CreationException, ReadingException{
        return null;
    }

    default T getById(ID id) throws ReadingException{
        return null;
    }

    default ID save(T skill){
        return null;
    }

    default void deleteById(ID id){

    }

}
