/**
 * Graphical Interface is in here.
 * That's where the boundaries and functionality
 * of interface are being set
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryJFrame extends JFrame implements ActionListener {
    //definition of interface elements
    JEditorPane bookList;
    JButton searchBtn, sortBtn, dltBtn, addBtn;
    JLabel authorLbl, titleLbl, yearLbl, image, appTitle, errorLbl;
    JTextPane authorTxt, titleTxt, yearTxt;
    JScrollPane tablePane;
    JPanel jPanel;
    Book b = new Book();



    LibraryJFrame() {
        //describing the interface
        super("Library Book Search");
        this.setSize(1050,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.decode("#3c3f41"));
        setResizable(false);

        //creating the place for user input
        authorTxt = new JTextPane();
        authorTxt.setBounds(300, 50, 400, 20);
        authorTxt.setCaretColor(Color.decode("#b9b8b6"));
        authorTxt.setBackground(Color.decode("#2b2b2b"));
        authorTxt.setForeground(Color.decode("#b9b8b6"));
        jPanel.add(authorTxt);

        //creating the place for user input
        yearTxt = new JTextPane();
        yearTxt.setBounds(725, 50, 75, 20);
        yearTxt.setCaretColor(Color.decode("#b9b8b6"));
        yearTxt.setBackground(Color.decode("#2b2b2b"));
        yearTxt.setForeground(Color.decode("#b9b8b6"));
        jPanel.add(yearTxt);

        //creating the place for user input
        titleTxt = new JTextPane();
        titleTxt.setBounds(300, 100, 400, 20);
        titleTxt.setCaretColor(Color.decode("#b9b8b6"));
        titleTxt.setBackground(Color.decode("#2b2b2b"));
        titleTxt.setForeground(Color.decode("#b9b8b6"));
        jPanel.add(titleTxt);

        authorLbl = new JLabel("Author:");
        authorLbl.setBounds(300, 30, 275, 20);
        authorLbl.setForeground(Color.decode("#FFFFFF"));
        jPanel.add(authorLbl);

        yearLbl = new JLabel("Year:");
        yearLbl.setBounds(725, 30, 275, 20);
        yearLbl.setForeground(Color.decode("#FFFFFF"));
        jPanel.add(yearLbl);

        titleLbl = new JLabel("Title:");
        titleLbl.setBounds(300, 80, 600, 20);
        titleLbl.setForeground(Color.decode("#FFFFFF"));
        jPanel.add(titleLbl);

        bookList = new JEditorPane();
        bookList.setEditable(false);
        bookList.setBackground(Color.decode("#4d4d4d"));
        bookList.setForeground(Color.decode("#b9b8b6"));

        //creating output window
        tablePane = new JScrollPane(bookList, tablePane.VERTICAL_SCROLLBAR_ALWAYS, tablePane.HORIZONTAL_SCROLLBAR_NEVER);
        tablePane.setBounds(300,150,700,500);
        tablePane.getViewport().setBackground(Color.decode("#2b2b2b"));
        jPanel.add(tablePane);

        //setting up buttons
        searchBtn = new JButton("Search");
        searchBtn.setBounds(725, 100, 75, 20);
        searchBtn.setBackground(Color.decode("#2b2b2b"));
        searchBtn.setForeground(Color.decode("#b9b8b6"));
        searchBtn.addActionListener(this);
        jPanel.add(searchBtn);

        sortBtn = new JButton("Sort");
        sortBtn.setBounds(725, 75, 75, 20);
        sortBtn.setBackground(Color.decode("#2b2b2b"));
        sortBtn.setForeground(Color.decode("#b9b8b6"));
        sortBtn.addActionListener(this);
        jPanel.add(sortBtn);

        addBtn = new JButton("Add Title");
        addBtn.setBounds(825, 50, 175, 20);
        addBtn.setBackground(Color.decode("#2b2b2b"));
        addBtn.setForeground(Color.decode("#b9b8b6"));
        addBtn.addActionListener(this);
        jPanel.add(addBtn);

        dltBtn = new JButton("Delete Title");
        dltBtn.setBounds(825, 100, 175, 20);
        dltBtn.setBackground(Color.decode("#2b2b2b"));
        dltBtn.setForeground(Color.decode("#b9b8b6"));
        dltBtn.addActionListener(this);
        jPanel.add(dltBtn);

        errorLbl = new JLabel();
        errorLbl.setBounds(825, 120, 200, 20);
        errorLbl.setForeground(Color.decode("#b02e2e"));
        jPanel.add(errorLbl);
        //adding a picture and title to interface

        image = new JLabel(new ImageIcon("books.jpg"));
        image.setBounds(40, 200, 220, 391);
        jPanel.add(image);

        appTitle = new JLabel(new ImageIcon("booksearch.png"));
        appTitle.setBounds(40,40,220,28);
        jPanel.add(appTitle);

        this.add(jPanel);
    }
    /*That's where interface meets the class code.*/
    @Override
    public void actionPerformed(ActionEvent e) {
        errorLbl.setVisible(false);
        errorLbl.setForeground(Color.decode("#b02e2e"));
        //assigning actions to different input options
        switch (e.getActionCommand()) {
            case "Search":
                if (!authorTxt.getText().equals("")) {
                    bookList.setText("");
                    AuthorSearch(authorTxt.getText(), b.returnTitles(), b.returnAuthor(), b.returnYear());
                } else if (!titleTxt.getText().equals("")) {
                    bookList.setText("");
                    TitleSearch(titleTxt.getText(), b.returnTitles(), b.returnAuthor(), b.returnYear());
                } else if (!yearTxt.getText().equals("")) {
                    bookList.setText("");
                    YearSearch(Integer.parseInt(yearTxt.getText()), b.returnTitles(), b.returnAuthor(), b.returnYear(), b.IsYear(Integer.parseInt(yearTxt.getText())));
                }
                break;
            case "Sort":
                bookList.setText("");
                AZSortedList(b.returnTitles(), b.returnAuthor(), b.returnYear());
                break;
            case "Add Title":
                if (!authorTxt.getText().equals("") && !titleTxt.getText().equals("") && !yearTxt.getText().equals("")) {
                    b.addBook(titleTxt.getText(), authorTxt.getText(), Integer.parseInt(yearTxt.getText()));
                    errorLbl.setText("Title Added");
                    errorLbl.setForeground(Color.decode("#46ad2c"));
                } else {
                    errorLbl.setText("Please fill all three fields.");
                }
                errorLbl.setVisible(true);
                break;
            case "Delete Title":
                if (!titleTxt.getText().equals("")) {
                    b.DeleteTitle(titleTxt.getText());
                    errorLbl.setText("Title Deleted");
                    errorLbl.setForeground(Color.decode("#46ad2c"));
                } else {
                    errorLbl.setText("Please fill the title to be deleted.");
                }
                errorLbl.setVisible(true);
                break;
        }
    }
//this method shows all books written by the searched author
    public void AuthorSearch(String title, ArrayList<String> titles, ArrayList<String> authors, ArrayList<Integer> years)
    {
        int count=0;
        for(int i=0;i<titles.size();i++)
        {
            if(title.equals(titles.get(i)))
            {
                count++;
                bookList.setText(bookList.getText() + "Title: " + titles.get(i) + "\n\tAuthor: " + authors.get(i) + "\n\tYear: " + years.get(i) + "\n\n");
                System.out.println(years.get(i) + ", " + titles.get(i));
            }
        }
        if(count<1)
            System.out.println("This title was not found");
    }
    //This method shows all books written in the searched year
    public void YearSearch(int year, ArrayList<String> titles, ArrayList<String> authors, ArrayList<Integer> years, boolean IsYear)
    {
        if(IsYear)
        {
            System.out.println("Books written in "+year);
            for(int i=0;i<years.size();i++)
            {
                if(year==years.get(i))
                {
                    bookList.setText(bookList.getText() + "Title: " + titles.get(i) + "\n\tAuthor: " + authors.get(i) + "\n\tYear: " + years.get(i) + "\n\n");
                    System.out.println(titles.get(i) + ", " + authors.get(i));
                }
            }
        }
        else
            System.out.println("This year was not found");

    }
    //This method prints information about the searched title
    public void TitleSearch(String title,ArrayList<String> titles, ArrayList<String> authors, ArrayList<Integer> years)
    {
        int count=0;
        for(int i=0;i<titles.size();i++)
        {
            if(title.equals(titles.get(i)))
            {
                count++;
                bookList.setText(bookList.getText() + "Title: " + titles.get(i) + "\n\tAuthor: " + authors.get(i) + "\n\tYear: " + years.get(i) + "\n\n");
                System.out.println(years.get(i) + ", " + titles.get(i));
            }
        }
        if(count<1)
            System.out.println("This title was not found");
    }
    //This method sorts and prints the whole list
    public void AZSortedList(ArrayList<String> titles, ArrayList<String> authors, ArrayList<Integer> years)
    {

        for (int i = 0; i < titles.size() - 1; i++) {
            for (int j = i + 1; j < titles.size(); j++) {
                if (titles.get(i).compareTo(titles.get(j)) > 0) {
                    String tempTitle = titles.get(i);
                    String tempAuthor = authors.get(i);
                    int tempYears = years.get(i);
                    titles.set(i, titles.get(j));
                    authors.set(i, authors.get(j));
                    years.set(i, years.get(j));
                    titles.set(j, tempTitle);
                    authors.set(j, tempAuthor);
                    years.set(j, tempYears);
                }
            }
        }
        for (int i = 0; i < titles.size(); i++)
        {
            bookList.setText(bookList.getText() + "Title: " + titles.get(i) + "\n\tAuthor: " + authors.get(i) + "\n\tYear: " + years.get(i) + "\n\n");
            System.out.println("Title: " + titles.get(i) + " Author: " + authors.get(i) + " Year: " + years.get(i));
        }
    }
}
