import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class p2 extends Frame {
    
    TextArea ta;
    MenuBar m;
    Menu fileMenu, editMenu, viewMenu, fonts, style, TextSize;
    MenuItem i1,i2,i3,i4,i5,f1,f2,f3,f4,f5,s1,s2,s3,s4,s5,s6,s7,v1,v2;
    CheckboxMenuItem c1,c2,c3;
    String directory, file;
    int fileOpened = 0;
    int ArrStyle[] = {1,0,0};
    String font[] = {"Serif", "SansSerif", "Monospaced", "Dialog", "Arial"};
    int[] currFont = {4};
    int[] fontSize = {20};

    public p2() {
        setTitle("Notepad");
        setSize(700, 400);
        setLayout(new GridLayout(1,1,0,0));

        // Basic text Area
        ta = new TextArea("", 300, 500, TextArea.SCROLLBARS_BOTH);
        ta.setFont(new Font("Arial", Font.PLAIN, 20));
        add(ta);

        // (this) --> refers to frame
        createMenu(this);

        // fileMenu
        // clear all
        i1.addActionListener(e -> {
            if(!ta.getText().equals("")) {
                ta.setText("");
            }
        });

        // exit
        i5.addActionListener(e -> dispose());

        // open 
        i2.addActionListener(e -> {
            FileDialog fd = new FileDialog(this, "Select a file", FileDialog.LOAD);
            fd.setVisible(true);

            directory = fd.getDirectory();
            file = fd.getFile();

            System.out.println("File opened : " + directory+file);
            fileOpened = 1;

            // getting content of the file
            try{
                File f = new File(directory+file);
                Scanner sc = new Scanner(f);

                ta.setText("");
                String content = "";
                while(sc.hasNextLine()) {
                    content += sc.nextLine();
                    content += "\n";
                }
                ta.setText(content);
                setTitle("Notepad - file Opened (" + directory + file + ")");

                sc.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        
        // save 
        i3.addActionListener(e -> {
            if(fileOpened == 1) {
                try {
                    FileWriter writer = new FileWriter(directory+file);
                    writer.write(ta.getText());
                    writer.close();
                    Dialog d = new Dialog(this,"Done !");
                    d.setVisible(true);
                    d.setLocation(300,200);
                    d.setSize(200,100);
                    d.add(new Label("File Saved !"));
                    d.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            d.dispose();
                        }
                    });
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                saveAs(this);
            }
        });
        
        //save as
        i4.addActionListener(e -> {
            saveAs(this);
        });

        // editMenu - fonts
        f1.addActionListener(e -> {
            currFont[0] = 0;
            checkStyle();
        });
        f2.addActionListener(e -> {
            currFont[0] = 1;
            checkStyle();
        });
        f3.addActionListener(e -> {
            currFont[0] = 2;
            checkStyle();
        });
        f4.addActionListener(e -> {
            currFont[0] = 3;
            checkStyle();
        });
        f5.addActionListener(e -> {
            currFont[0] = 4;
            checkStyle();
        });

        // editMenu - size 
        s1.addActionListener(e -> {
            int size = Integer.parseInt(s1.getLabel());
            fontSize[0] = size;
            checkStyle();
        });
        s2.addActionListener(e -> {
            int size = Integer.parseInt(s2.getLabel());
            fontSize[0] = size;
            checkStyle();
        });
        s3.addActionListener(e -> {
            int size = Integer.parseInt(s3.getLabel());
            fontSize[0] = size;
            checkStyle();
            
        });
        s4.addActionListener(e -> {
            int size = Integer.parseInt(s4.getLabel());
            fontSize[0] = size;
            checkStyle();
        });
        s5.addActionListener(e -> {
            int size = Integer.parseInt(s5.getLabel());
            fontSize[0] = size;
            checkStyle();
        });
        s6.addActionListener(e -> {
            int size = Integer.parseInt(s6.getLabel());
            fontSize[0] = size;
            checkStyle();
        });
        s7.addActionListener(e -> {
            int size = Integer.parseInt(s7.getLabel());
            fontSize[0] = size;
            checkStyle();
        });

        // editMenu - style
        c2.addItemListener(e -> {
            if(c2.getState() == true) {
                ArrStyle[1] = 1; 
            }
            else {
                ArrStyle[1] = 0; 
            }
            checkStyle();
        });
        c3.addItemListener(e -> {
            if(c2.getState() == true) {
                ArrStyle[1] = 1; 
            }
            else {
                ArrStyle[1] = 0; 
            }
            checkStyle();
        });

        // ViewStyle 
        v1.addActionListener(e -> {
            if(fontSize[0] > 64) {}
            else {
                fontSize[0] += 4;
                checkStyle();
            }
        });
        v2.addActionListener(e -> {
            if(fontSize[0] < 12) {}
            else {
                fontSize[0] -= 4;
                checkStyle();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    void saveAs(Frame f) {
            FileDialog fd = new FileDialog(f, "Select a location", FileDialog.SAVE);
            fd.setVisible(true);

            directory = fd.getDirectory();
            file = fd.getFile();

            try {
                FileWriter writer = new FileWriter(directory+file);
                writer.write(ta.getText());
                writer.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    void checkStyle() {
        // ArrStyle[0] -> plain
        // ArrStyle[1] -> bold
        // ArrStyle[2] -> italic
        if(ArrStyle[1] == 1 && ArrStyle[2] == 1) {
            ta.setFont(new Font(font[currFont[0]], Font.BOLD | Font.ITALIC, fontSize[0]));
        }
        else if(ArrStyle[1] == 1 && ArrStyle[2] == 0) {
            ta.setFont(new Font(font[currFont[0]], Font.BOLD, fontSize[0]));
        }
        else if(ArrStyle[1] == 0 && ArrStyle[2] == 1) {
            ta.setFont(new Font(font[currFont[0]], Font.ITALIC, fontSize[0]));
        }
        else {
            ta.setFont(new Font(font[currFont[0]], Font.PLAIN, fontSize[0]));
        }
    }

    void createMenu(Frame f) {
        m = new MenuBar();
        
        // File Menu
        fileMenu = new Menu("File"); 
        i1 = new MenuItem("Clear All", new MenuShortcut(KeyEvent.VK_K));
        i2 = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
        i3 = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_S));
        i4 = new MenuItem("Save as", new MenuShortcut(KeyEvent.VK_S, true));
        i5 = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_E));
        fileMenu.add(i1);
        fileMenu.add(i2);
        fileMenu.add(i3);
        fileMenu.add(i4);
        fileMenu.addSeparator();
        fileMenu.add(i5);
        
        // Edit Menu
        editMenu = new Menu("Edit"); 
        fonts = new Menu("Fonts");
        f1 = new MenuItem("Serif");
        f2 = new MenuItem("SansSerif");
        f3 = new MenuItem("Monospaced");
        f4 = new MenuItem("Dialog");
        f5 = new MenuItem("Arial");
        fonts.add(f1);
        fonts.add(f2);
        fonts.add(f3);
        fonts.add(f4);
        fonts.add(f5);
        TextSize = new Menu("Text Size");
        s1 = new MenuItem("16");
        s2 = new MenuItem("18");
        s3 = new MenuItem("20");
        s4 = new MenuItem("22");
        s5 = new MenuItem("24");
        s6 = new MenuItem("26");
        s7 = new MenuItem("28");
        TextSize.add(s1);
        TextSize.add(s2);
        TextSize.add(s3);
        TextSize.add(s4);
        TextSize.add(s5);
        TextSize.add(s6);
        TextSize.add(s7);
        style = new Menu("Style");
        c1 = new CheckboxMenuItem("Plain", true);
        c1.setEnabled(false);
        c2 = new CheckboxMenuItem("Bold", false);
        c3 = new CheckboxMenuItem("Italic", false);
        style.add(c1);
        style.add(c2);
        style.add(c3);
        
        editMenu.add(fonts);
        editMenu.add(TextSize);
        editMenu.add(style);
        
        // View Menu
        viewMenu = new Menu("View");
        v1 = new MenuItem("Zoom in", new MenuShortcut(KeyEvent.VK_Z));
        v2 = new MenuItem("Zoom out", new MenuShortcut(KeyEvent.VK_Z, true));
        viewMenu.add(v1);
        viewMenu.add(v2);
        
        i1.addActionListener(e -> {
            ta.setText("");
        });

        // menu bar
        m.add(fileMenu);
        m.add(editMenu);
        m.add(viewMenu);
        f.setMenuBar(m);

    }

    public static void main(String[] args) {
        new p2();
    }
}