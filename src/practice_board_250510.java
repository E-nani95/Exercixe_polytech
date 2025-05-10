import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class practice_board_250510 extends JFrame {

    public practice_board_250510(){
            setTitle("사원정보테이블");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800,400);
            setLayout(new BorderLayout());
            String txt_top [] ={"사번", "이름", "근무부서"};
            JLabel lb_top [] = new JLabel[txt_top.length];
            JTextField txf_top []= new JTextField[txt_top.length];
            JButton btn1=new JButton("추가");
            JButton btn2=new JButton("수정");
            JButton btn3=new JButton("삭제");
            JPanel jp_top=new JPanel();

            for (int i=0; i<txt_top.length; i++){
                lb_top[i]=new JLabel(txt_top[i]);
                txf_top[i]= new JTextField(10);
                jp_top.add(lb_top[i]);
                jp_top.add(txf_top[i]);
            }
            jp_top.add(btn1);
            jp_top.add(btn2);
            jp_top.add(btn3);

            JPanel jp_center = new JPanel();
            Object data[][]={{"a","1","A"},{"b","2","B"}};
            DefaultTableModel dtm = new DefaultTableModel(data,txt_top);
            JTable jt =new JTable(dtm);
            JScrollPane jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jp_center.add(jsp);

            jt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    for(int i=0; i<txt_top.length; i++){
                        txf_top[i].setText(dtm.getValueAt(jt.getSelectedRow(),i).toString());
                    }
                }
            });

            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object [] t1 = new Object[jt.getColumnCount()];
                    for(int i=0; i<jt.getColumnCount();i++){
                        t1[i]=txf_top[i].getText();
                    }

//                    Object [] plz={txf_top[0].getText(),txf_top[1].getText(),txf_top[2].getText()};
                    dtm.addRow(t1);
                }
            });

            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<txt_top.length; i++){
                        dtm.setValueAt(txf_top[i].getText(),jt.getSelectedRow(),i);
//                        dtm.setValueAt(txf_top[(i+1)].getText(),jt.getSelectedRow(),(i+1));
//                        dtm.setValueAt(txf_top[(i+2)].getText(),jt.getSelectedRow(),(i+2));
                    }
                }
            });

            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dtm.removeRow(jt.getSelectedRow());
                }
            });


            add(jp_top,BorderLayout.NORTH);
            add(jp_center,BorderLayout.CENTER);
            setVisible(true);
    }

    public static void main(String[] args) {
        new practice_board_250510();
    }
}
