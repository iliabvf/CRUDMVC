package net.iliabvf.javaio.view;

import net.iliabvf.javaio.AppRunner;
import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.controller.SkillController;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.model.Skill;

import java.util.Map;

public class SkillView {
    SkillController skillController = new SkillController();

    public Long create(String name) throws CreationException, ReadingException{
        if (name.equals(""))
            throw new CreationException(AppRunner.ERROR_CREATING_EMPTY_NAME);

        Long skillID = skillController.create(name);

        System.out.println("Skill " + name + " succesfully created with ID " + skillID);
        return skillID;
    }

    public void showAll(){

    }

    public Skill getById(Long id) throws ReadingException {
        return skillController.getById(id);
    }

    public void showByID(Long id){

    }

    public void showByID(Skill id){

    }

    public Map getAll() throws ReadingException {
        return skillController.getAll();
    }

    // принимает данные с консоли
    // skill.txt -> Skill -> SkillRepo -> SkillsService -> SkilLController -> SkillView



}
