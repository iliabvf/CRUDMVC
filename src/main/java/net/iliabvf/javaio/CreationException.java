package net.iliabvf.javaio;

public class CreationException extends Exception {
    String str1;

    public CreationException(String str2) {
        str1 = str2;
    }
    public String toString(){
        return ("Creation exception occurred: " + str1) ;
    }
}
