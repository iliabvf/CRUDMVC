package net.iliabvf.javaio.service;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.model.Skill;
import net.iliabvf.javaio.repository.SkillRepository;
import net.iliabvf.javaio.repository.io.JavaIOSkillRepositoryImpl;

import java.util.Map;

public class SkillService {

    public SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public Long create(String name) throws CreationException, ReadingException {
        return skillRepository.create(name);
//        return null;
    }

    public Map getAll() throws ReadingException {
        return skillRepository.getAll();
    }

    public Skill getById(Long id) throws ReadingException {
        return skillRepository.getById(id);
    }

//    Skill save(Skill skill){
//        repo.save();
//    }
}
