import java.util.Scanner;

public class ReverseString {

     public static void main(String[] args) {

        String str= "Java", str1="";
        char ch;
         
        for (int i=0; i<str.length(); i++)
        {
            ch= str.charAt(i); //extracts each character
            str1= ch + str1; //adds each character in front of the existing string
        }
        System.out.println("Reversed word: "+ str1);

    }
}
