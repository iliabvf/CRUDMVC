package net.iliabvf.javaio.service;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.repository.DeveloperRepository;
import net.iliabvf.javaio.repository.io.JavaIODeveloperRepositoryImpl;

import java.util.Map;
import java.util.Set;

public class DeveloperService {

    public DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();

    public Long create(String name) throws CreationException, ReadingException {
        return developerRepository.create(name);
    }

    public Map getAll() throws ReadingException {
        return developerRepository.getAll();
    }

    private void saveDeveloper(){
//        this.developerRepo.save(developer);
    }

    public Developer getById(Long id) throws ReadingException {
        return developerRepository.getById(id);
    }

}
