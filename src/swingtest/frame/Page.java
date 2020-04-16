package swingtest.frame;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Page extends JFrame {

    private Vector<Vector<String>> rowData;
    private JTable table;

    public Page(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane center = new JScrollPane();

        rowData = new Vector<>();
        Vector<String> columnName = new Vector<>();

        rowData.addElement(new Vector<>());
        rowData.firstElement().add("0");

        columnName.add("Number");

        table = new JTable(rowData, columnName);
        center.setViewportView(table);

        getContentPane().add(center, BorderLayout.CENTER);

        setSize(300, 300);
    }

    public void showPage(){
        setVisible(true);

        new Thread(() -> {
            for(int i = 0; i < 100; i++){
                rowData.firstElement().setElementAt( String.valueOf(i), 0);
                table.repaint();

                try{
                    Thread.sleep(300);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
