package net.iliabvf.javaio.repository.io;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.DeleteException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.exceptions.UpdateException;
import net.iliabvf.javaio.model.Skill;
import net.iliabvf.javaio.repository.SkillRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaIOSkillRepositoryImpl extends SkillRepository {
    SkillRepository repo;
    private final String FILE_NAME = "files/skills.txt";
    private final Charset utf8 = StandardCharsets.UTF_8;

    @Override
    public Map getAll() throws ReadingException {
        Map<Long,Skill> map = new HashMap();

        Path path;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());

        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file skills.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file skills.txt");
        }

        try {

            byte[] fileBytes = Files.readAllBytes(path);
            String data = new String(fileBytes);

            String[] lines = data.split("\r\n");

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].equals(""))
                    continue;

                Long id = Long.decode(lines[i].split(":")[0]);
                map.put(id, new Skill(id, lines[i].split(":")[1]));
            }
        }
        catch(IOException ex) {
            throw new ReadingException("Error: IOException reading file skills.txt");
        }

        return map;
    }

    @Override
    public Long create(String name) throws CreationException {

        Path path;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());

        } catch (NullPointerException e){
            throw new CreationException("Error: NullPointerException reading file skills.txt");
        } catch (URISyntaxException e){
            throw new CreationException("Error: URISyntaxException reading file skills.txt");
        };

        List<Skill> list = new ArrayList<>();
        Long maxID = 0L;

        try {

            byte[] fileBytes = Files.readAllBytes(path);
            String data = new String(fileBytes);

            String[] lines = data.split("\r\n");

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].equals(""))
                    continue;

                Long ID = Long.decode(lines[i].split(":")[0]);
                list.add(new Skill(ID, lines[i].split(":")[1]));

                maxID = maxID < ID ? ID : maxID;
            }
        }
        catch(IOException ex) {
            throw new CreationException("Error: IOException reading file skills.txt");
        }

        Long newID = maxID + 1;
        list.add(new Skill(newID, name));

        // writing file
        try {

            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(list.get(i).toString());

            Files.write(path, list1, utf8,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException x) {
            throw new CreationException("Error: IOException reading file skills.txt");
        }

        return newID;
    }

    @Override
    public Long save(Skill skill) {
        return repo.save(skill);
    }

    @Override
    public Skill getById(Long id) throws ReadingException {
        String name = "";

        Path path;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());
        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file skills.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file skills.txt");
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
                    return new Skill(ID, lines[i].split(":")[1]);
            }
        }
        catch(IOException ex) {
            throw new ReadingException("Error: IOException reading file skills.txt");
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

}
