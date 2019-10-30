package net.iliabvf.javaio.controller;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.service.DeveloperService;

import java.util.Map;

public class DeveloperController {

//    public Developer createDeveloper

//    developerService.save(developer)

    DeveloperService developerService = new DeveloperService();

    public Long create(String name) throws CreationException, ReadingException {
        return developerService.create(name);
    }

    public Map getAll() throws ReadingException {
        return developerService.getAll();
    }

    public Developer getById(Long id) throws ReadingException {
        return developerService.getById(id);
    }

}
