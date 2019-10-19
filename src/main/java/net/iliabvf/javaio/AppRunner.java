package net.iliabvf.javaio;

import net.iliabvf.javaio.controller.DeveloperController;
import net.iliabvf.javaio.model.Developer;
import net.iliabvf.javaio.model.Skill;
import net.iliabvf.javaio.view.AccountView;
import net.iliabvf.javaio.view.DeveloperView;
import net.iliabvf.javaio.view.SkillView;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AppRunner {

    private final static String WELCOME_STRING1 =  "Welcome to Developers accounts CRUD MVC Java app v.0.1 (by Budeanu Vasile).";
    private final static String WELCOME_STRING3 =  "1.Create an account";
    private final static String WELCOME_STRING4 =  "2.Read all accounts";
    private final static String WELCOME_STRING5 =  "3.Update an account";
    private final static String WELCOME_STRING6 =  "4.Delete an account";
    private final static String WELCOME_STRING7 =  "5.Quit";
    private final static String WELCOME_STRING2 =  "Please choose option:";

    private final static String CLOSING_STRING =  "Have a nice day.";
    private final static String ERROR_NUM_DIGITS_STRING =  "Please input 1 digit";
    private final static String ERROR_RANGE_STRING =  "Error: Please enter from 1 to 5.";

    private final static String CREATE_DEV_STRING1 =  "Please enter developer name: ";
    private final static String CREATE_DEV_STRING2 =  "Please enter developer skill: ";

    public final static String ERROR_CREATING_EMPTY_NAME =  "Error: name is empty";
    public final static String ERROR_CREATING_EMPTY_ID =  "Error creating account, developer or skill id is not set";

    public final static String DELETE_ACCOUNT_STRING = "Please input account ID to delete: ";
    public final static String UPDATE_ACCOUNT_STRING = "Please input account ID to update: ";

    private static Scanner scanner;

    public static Scanner getScanner() {
        return scanner;
    }

    public static String userInput(String message){
        System.out.println();
        System.out.print(message);
        try {
            return scanner.nextLine();
        } catch (Exception e)
        {
            return "";
        }
    }

    private static byte validateInput(String input){
        if (input.toUpperCase().equals("5")){
            System.out.println();
            System.out.println(CLOSING_STRING);
            return 5;
        }

        if (input.length() != 1) {
            System.out.println(ERROR_NUM_DIGITS_STRING);
            return 0;
        }

        if (input.charAt(0) < 49 || input.charAt(0) > 53){
            System.out.println(ERROR_RANGE_STRING);
            return 0;
        }

        return (byte)Character.getNumericValue(input.charAt(0));
    }

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        DeveloperView developerView = new DeveloperView();
        SkillView skillView = new SkillView();
        AccountView accountView = new AccountView();

        Long devID;
        Long skillID;
        Long accountID;
        Long accID;

        byte input;

        // Main cycle
        while (true) {

            while (true) {
                System.out.println(WELCOME_STRING1);
                System.out.println(WELCOME_STRING3);
                System.out.println(WELCOME_STRING4);
                System.out.println(WELCOME_STRING5);
                System.out.println(WELCOME_STRING6);
                System.out.println(WELCOME_STRING7);

                input = validateInput(userInput(WELCOME_STRING2));
                if ( input == 0) {
                    continue;
                } else if (input == 5) {
                    return;
                }

                switch(input) {
                    case 1:

                        try {
                            String devName = userInput(CREATE_DEV_STRING1);
                            devID = developerView.create(devName);
                        } catch (CreationException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            String devSkillName = userInput(CREATE_DEV_STRING2);
                            skillID = skillView.create(devSkillName);
                        } catch (CreationException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            accountID = accountView.create(devID, skillID);
                        } catch (CreationException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        break;

                    case 2:

                        Map allDevs;
                        Map allSkills;

                        try {
                            allDevs = developerView.getAll();
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            allSkills = skillView.getAll();
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            accountView.showAll(allDevs, allSkills);
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        break;

                    case 3: // Update

                        accID = 0L;

                        try {
                            accID = (Long)Long.decode(userInput(UPDATE_ACCOUNT_STRING));
                        } catch (NumberFormatException e){
                            System.out.println(e);
                            continue;
                        }

                        if (accID == 0)
                            break;

                        try {
                            String devName = userInput(CREATE_DEV_STRING1);
                            devID = developerView.create(devName);
                        } catch (CreationException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            String devSkillName = userInput(CREATE_DEV_STRING2);
                            skillID = skillView.create(devSkillName);
                        } catch (CreationException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        try {
                            accountView.update(accID, devID, skillID);
                        } catch (UpdateException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }
                        break;

                    case 4:

                        accID = 0L;

                        try {
                            accID = (Long) Long.decode(userInput(DELETE_ACCOUNT_STRING));
                        } catch (NumberFormatException e){
                            System.out.println(e);
                            continue;
                        }

                        if (accID == 0)
                            break;

                        try {
                            accountView.deleteByID(accID);
                        } catch (DeleteException e){
                            System.out.println(e);
                            continue;
                        } catch (ReadingException e){
                            System.out.println(e);
                            continue;
                        }

                        break;

                    default:
                        // code block
                }


                break;
            }

        }

    }
}
