package net.iliabvf.javaio.repository.io;

import net.iliabvf.javaio.exceptions.CreationException;
import net.iliabvf.javaio.exceptions.DeleteException;
import net.iliabvf.javaio.exceptions.ReadingException;
import net.iliabvf.javaio.exceptions.UpdateException;
import net.iliabvf.javaio.model.Account;
import net.iliabvf.javaio.model.AccountStatus;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.model.Skill;
import net.iliabvf.javaio.repository.AccountRepository;
import net.iliabvf.javaio.view.DeveloperView;
import net.iliabvf.javaio.view.SkillView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class JavaIOAccountRepositoryImpl extends AccountRepository {
    AccountRepository repo;

    private final String FILE_NAME = "files/accounts.txt";
    private final Charset utf8 = StandardCharsets.UTF_8;

    @Override
    public Long create(Long devID, ArrayList<Long> skillsIDsList) throws CreationException,ReadingException {

        Path path;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());
        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file accounts.txt");
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file accounts.txt");
        }

        List list = new ArrayList<Developer>();

        Map map = getAll();
        Long maxID = 0L;

        if (map.size() > 0)
            maxID = (Long)map.keySet().stream().max((entry1, entry2) -> (Long)entry1 > (Long)entry2 ? 1 : -1).get();

        Long newID = maxID + 1;
        Account newDev = new Account(AccountStatus.ACTIVE, devID, skillsIDsList, newID);

        map.put(newID, newDev);

        list.addAll(map.values());

        // writing file
        try {

            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(list.get(i).toString());

            Files.write(path, list1, utf8,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException x) {
            throw new CreationException("Error: IOException reading file accounts.txt");
        }

        return newID;
    }

    @Override
    public Long save(Account account) {
        return repo.save(account);
    }

    @Override
    public Account getById(Long id) throws ReadingException {
        return repo.getById(id);
    }

    @Override
    public Map getAll() throws ReadingException{
        Map<Long,Account> map = new HashMap();

        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());
        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file " + path);
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file " + path);
        }

        try {
            byte[] fileBytes = Files.readAllBytes(path);
            if (fileBytes.length != 0) {
                String data = new String(fileBytes);
                String[] lines = data.split("\r\n");

                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].equals(""))
                        continue;

                    Long accountStatus1 = Long.decode(lines[i].split(":")[0]);
                    Long devID1 = Long.decode(lines[i].split(":")[1]);
                    String skillsIDsStr = lines[i].split(":")[2];
                    Long ID1 = Long.decode(lines[i].split(":")[3]);

                    ArrayList<Long> skillsIDsList = new ArrayList<>();

                    String[] skillsIDsArray = skillsIDsStr.split(";");
                    for (String curIDStr: skillsIDsArray) {
                        skillsIDsList.add(Long.decode(curIDStr));
                    }

                    map.put(ID1, new Account(accountStatus1 == 0L ? AccountStatus.ACTIVE : (accountStatus1 == 1L ? AccountStatus.BANNED : AccountStatus.DELETED), devID1, skillsIDsList, ID1));
                }
            }
        }
        catch(IOException ex) {
            throw new ReadingException("Error: IOException reading file " + path);
        }
        catch(NumberFormatException ex) {
            throw new ReadingException("Error: NumberFormatException reading file " + path);
        }

        return map;
    }

//    @Override
//    public void deleteById(Long id) {
//        repo.deleteById(id);
//    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public void showAll(Map allDevs, Map allSkills) throws ReadingException {
        Map<Long,Account> map = getAll();

        map.forEach((Long k, Account v)->{

            Developer dev;

            DeveloperView developerView = new DeveloperView();
            try {
                dev = developerView.getById(v.getDeveloperID());
            } catch (ReadingException e){
                System.out.println(e);
                return;
            }

            ArrayList<Skill> skillsList = new ArrayList<>(); //Skill skill;

            SkillView skillView = new SkillView();

            v.getskillsIDsList().forEach((n) -> {
//                System.out.println(n);

                try {
                    skillsList.add(skillView.getById(n));
                } catch (ReadingException e){
                    System.out.println(e);
                    return;
                }
            });

            System.out.println("Account ID: " + k
                + ", Status: " + v.getStatus()
                + ", Developer: " + dev.getName() + " (ID:" + dev.getID() + ")"
                + ", Skills: " + skillsList
            );
            System.out.println();
        });
    }

    @Override
    public void deleteByID(Long accID) throws DeleteException, ReadingException {
        List list = new ArrayList<Account>();

        // Reading
        Map map = getAll();

        if (!map.containsKey(accID))
            throw new DeleteException("Account with ID: " + accID + ", not found.");

        map.remove(accID);

        list.addAll(map.values());

        // writing file
        Path path = null;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());

        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file " + path);
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file " + path);
        }
        try {

            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(list.get(i).toString());

            Files.write(path, list1, utf8,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException x) {
            throw new DeleteException("Error: IOException writing file " + path);
        }

        System.out.println("Account with ID: " + accID + " successfully deleted.");
        System.out.println();

    }

    @Override
    public void update(Long accID, Long devID, ArrayList<Long> skillsIDsList) throws UpdateException, ReadingException {
        List list = new ArrayList<Account>();

        // Reading
        Map map = getAll();

        if (!map.containsKey(accID))
            throw new UpdateException("Account with ID: " + accID + ", not found.");

        Account newAcc = new Account(AccountStatus.ACTIVE, devID, skillsIDsList, accID);

        map.replace(accID, newAcc);

        list.addAll(map.values());

        // writing file
        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(FILE_NAME).toURI());
        } catch (NullPointerException e){
            throw new ReadingException("Error: NullPointerException reading file " + path);
        } catch (URISyntaxException e){
            throw new ReadingException("Error: URISyntaxException reading file " + path);
        }

        try {
            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(list.get(i).toString());

            Files.write(path, list1, utf8,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException x) {
            throw new UpdateException("Error: IOException writing file " + path);
        }

        System.out.println("Account with ID: " + accID + " successfully updated.");
        System.out.println();

    }

}
