package net.iliabvf.javaio.repository.io;

import net.iliabvf.javaio.CreationException;
import net.iliabvf.javaio.DeleteException;
import net.iliabvf.javaio.ReadingException;
import net.iliabvf.javaio.UpdateException;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.repository.DeveloperRepository;
import net.iliabvf.javaio.repository.SkillRepository;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {

    private final String FILE_NAME = "files/developers.txt";

    @Override
    public Long create(Long devID, Long skillID) {
        return null;
    }

    public Map getAll() throws ReadingException{
        Map<Long,Developer> map = new HashMap();

        Path path;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());

        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file developers.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file developers.txt");
        }

        try {

            byte[] fileBytes = Files.readAllBytes(path);
            String data = new String(fileBytes);

            String[] lines = data.split("\r\n");

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].equals(""))
                    continue;

                Long ID = Long.decode(lines[i].split(":")[0]);
                map.put(ID, new Developer(ID, lines[i].split(":")[1]));
            }
        }
        catch(IOException ex) {
            throw new ReadingException("Error: IOException reading file developers.txt");
        }

        return map;
    }

    @Override
    public Long create(String name) throws CreationException, ReadingException {

        List<Developer> list = new ArrayList<Developer>();
        Long maxID = 0L;

        // Reading
        Map map = getAll();

        list.addAll(map.values());

        maxID = (Long)map.keySet().stream().max((entry1, entry2) -> (Long)entry1 > (Long)entry2 ? 1 : -1).get();

        Long newID = maxID + 1;

        Developer newDev = new Developer(newID, name);
        list.add(newDev);
        map.put(newID, newDev);

        // writing file
        Path path;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());

        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file developers.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file developers.txt");
        }
        Charset utf8 = StandardCharsets.UTF_8;
        try {

            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(list.get(i).toString());

            Files.write(path, list1, utf8,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException x) {
            throw new CreationException("Error: IOException reading file developers.txt");
        }

        return newID;
    }

    @Override
    public Long save(Developer developer) {
        return null;
    }

    @Override
    public Developer getById(Long id) throws ReadingException {
        String name = "";

        Path path;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());
        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file developers.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file developers.txt");
        }

        try {
            byte[] fileBytes = Files.readAllBytes(path);
            String data = new String(fileBytes);

            String[] lines = data.split("\r\n");

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].equals(""))
                    continue;

                Long ID = Long.decode(lines[i].split(":")[0]);

                if (ID == id)
                    return new Developer(ID, lines[i].split(":")[1]);
            }
        }
        catch(IOException ex) {
            throw new ReadingException("Error: IOException reading file developers.txt");
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void showAll() throws ReadingException {

    }

    @Override
    public void showAll(Map allDevs, Map allSkills) throws ReadingException {

    }

    @Override
    public void deleteByID(Long accID) throws DeleteException, ReadingException {

    }

    @Override
    public void update(Long accID, Long devID, Long skillID) throws UpdateException, ReadingException {

    }
}
