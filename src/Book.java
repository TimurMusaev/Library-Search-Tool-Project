/**
 * This is the main class that is being used for this database.
 * It contains arraysLists in which we store information about books
 * and some major methods
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    //array lists to store the info
    private final ArrayList<String> titles = new ArrayList<>();
    private final ArrayList<String> authors = new ArrayList<>();
    private final ArrayList<Integer> years = new ArrayList<>();

    //defining methods to be used
    public void setNthTitle(String title)
    {
        titles.add(title);
    }
    public void setNthAuthor(String author)
    {
        authors.add(author);
    }
    public void setNthYear(int year)
    {
        years.add(year);
    }
    public ArrayList<String> returnTitles() {
        return titles;
    }
    public ArrayList<Integer> returnYear() {
        return years;
    }
    public ArrayList<String> returnAuthor() {
        return authors;
    }

    Book() {
        //using exception handling to read a file
        try {
            File fillArray = new File("books.txt");
            Scanner readFile = new Scanner(fillArray);
            while(readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String [] split = line.split(", ");
                titles.add(split[0]);
                authors.add(split[1]);
                years.add(Integer.parseInt(split[2]));
            }
        } catch(FileNotFoundException e){
            System.out.println("Fill file was not found");
            e.printStackTrace();
        }
    }
    //this method allows to add more books
    public void addBook(String title, String author,int year)
    {
        titles.add(title);
        authors.add(author);
        years.add(year);
    }
    //method to delete a book
    public void DeleteTitle(String title)
    {
        if(IsTitle(title))
        {
            for(int i=0;i< titles.size();i++)
            {
                if(title.equals(titles.get(i)))
                {
                    titles.remove(i);
                    authors.remove(i);
                    years.remove(i);
                    System.out.println("Title was deleted");
                    i--;
                }
            }
        }
        else
            System.out.println("This title was not found");
    }

    //just some methods to check if information about books exists
    public boolean IsTitle(String title)
    {
        int count=0;
        for(int i=0;i<titles.size();i++)
        {
            if(title.equals(titles.get(i)))
            {
                count++;
            }
        }
        return count>0;
    }

    public boolean IsAuthor(String author)
    {
        int count=0;
        for(int i=0;i<authors.size();i++)
        {
            if(author.equals(authors.get(i)))
            {
                count++;
            }
        }
        return count>0;
    }
    public boolean IsYear(int year)
    {
        int count=0;
        for(int i=0;i<years.size();i++)
        {
            if(year==years.get(i))
            {
                count++;
            }
        }
        return count>0;
    }

}
