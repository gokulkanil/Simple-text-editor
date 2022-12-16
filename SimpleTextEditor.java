import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem newFile;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem printFile;
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem closeEditor;
    SimpleTextEditor(){
        frame=new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,1000);
        jTextArea=new JTextArea("Welcome to the text editor");
        jMenuBar=new JMenuBar();
        File=new JMenu("File");
        Edit=new JMenu("Edit");
        Close=new JMenu("Close");
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);
        openFile=new JMenuItem("Open");
        openFile.addActionListener(this);
        newFile=new JMenuItem("New");
        newFile.addActionListener(this);
        saveFile=new JMenuItem("Save");
        saveFile.addActionListener(this);
        printFile=new JMenuItem("Print");
        printFile.addActionListener(this);
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);
        File.add(printFile);
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        closeEditor=new JMenuItem("Close");
        closeEditor.addActionListener(this);
        Close.add(closeEditor);
        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SimpleTextEditor editor=new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s.equals("Copy")){
            jTextArea.copy();
        }else if(s.equals("Cut")){
            jTextArea.cut();
        }else if(s.equals("Paste")){
            jTextArea.paste();
        }else if(s.equals("Print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if(s.equals("New")){
            jTextArea.setText("");
        }else if(s.equals("Close")){
            System.exit(1);
        }else if(s.equals("Open")){
            JFileChooser jFileChooser=new JFileChooser("C:");

            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s.equals("Save")){
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans= jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
