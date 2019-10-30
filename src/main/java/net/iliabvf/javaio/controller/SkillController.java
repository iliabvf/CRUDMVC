package net.iliabvf.javaio.controller;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.model.Skill;
import net.iliabvf.javaio.service.SkillService;

import java.util.Map;

public class SkillController {

    SkillService skillService = new SkillService();

    public Long create(String name) throws CreationException, ReadingException {
        return skillService.create(name);
    }

    public Map getAll() throws ReadingException {
        return skillService.getAll();
    }

    public Skill getById(Long id) throws ReadingException {
        return skillService.getById(id);
    }
}
