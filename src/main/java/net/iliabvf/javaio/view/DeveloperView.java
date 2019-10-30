package net.iliabvf.javaio.view;

import net.iliabvf.javaio.AppRunner;
import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.controller.DeveloperController;
import net.iliabvf.javaio.model.Developer;

import java.util.Map;

public class DeveloperView {
    DeveloperController developerController = new DeveloperController();

    public Long create(String name) throws CreationException, ReadingException {
        if (name.equals(""))
            throw new CreationException(AppRunner.ERROR_CREATING_EMPTY_NAME);

        Long devID = developerController.create(name);
        System.out.println("Developer " + name + " succesfully created with ID " + devID);
        return devID;
    }

    public void showAll(){

    }

    public Map getAll() throws ReadingException {
        return developerController.getAll();
    }

    public void update(){

    }

    public void write(){

    }

    public Developer getById(Long id) throws ReadingException {
        return developerController.getById(id);
    }


}
