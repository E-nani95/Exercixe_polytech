import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class practice_StudentScoreManage extends JFrame {
    public practice_StudentScoreManage(){
        setTitle("학생정보관리");
        setSize(610,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel jp_top_left = new JPanel();
        String cols[]={"학번","성명","중간","기말","출석"};
        JLabel jl []= new JLabel[cols.length];
        JTextField txf[]=new JTextField[cols.length];
        for(int i=0; i<cols.length; i++){
            jl[i]=new JLabel(cols[i]);
            txf[i]=new JTextField(10);
            jp_top_left.add(jl[i]);
            jp_top_left.add(txf[i]);
        }

        JPanel jp_top_right = new JPanel();
        Object data[][]={{240001, "학생1", 100, 80, 60}, {240002, "학생2", 70, 90, 100},{240003, "학생3", 100, 100, 100}};
        DefaultTableModel dtm = new DefaultTableModel(data,cols);
        JTable jt= new JTable(dtm);
        JScrollPane jsp=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jp_top_right.add(jsp);
        jsp.setPreferredSize(new Dimension(400,130));


        JPanel jp_bottom_left = new JPanel();
        JButton btn1= new JButton("입력");
        JButton btn2= new JButton("수정");
        JButton btn3= new JButton("삭제");
        jp_bottom_left.add(btn1);
        jp_bottom_left.add(btn2);
        jp_bottom_left.add(btn3);


        JPanel jp_bottom_right = new JPanel();
        String col_bottom_right[]={"총점", "평균", "평점"};
        JLabel jl_bottom_right [] =new JLabel[col_bottom_right.length];
        JTextField txf_bottom_right[]=new JTextField[col_bottom_right.length];
        for(int i=0; i<col_bottom_right.length; i++){
            jl_bottom_right[i]=new JLabel(col_bottom_right[i]);
            txf_bottom_right[i]=new JTextField(5);
            jp_bottom_right.add(jl_bottom_right[i]);
            jp_bottom_right.add(txf_bottom_right[i]);
        }


        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double Sum=0;
                double Avg=0;
                String res="NULL";
                for(int i =0; i<cols.length; i++){
                    txf[i].setText(dtm.getValueAt(jt.getSelectedRow(),i).toString());
                }

                int arr []= new int[3];
                for (int i=0; i<col_bottom_right.length; i++){
                    arr[i]=Integer.parseInt(txf[(i+2)].getText());

                }
                Sum=((arr[0]*0.4)+(arr[1]*0.4)+(arr[2]*0.2));
                Avg=Arrays.stream(arr).average().orElse(0);
                if(Sum>=90){
                    res="A";
                } else if (Sum>=80) {
                    res="B";
                } else if (Sum>=70) {
                    res="C";
                } else if (Sum >=60) {
                    res="D";
                }else{
                    res="F";
                }

                txf_bottom_right[0].setText(Double.toString(Sum));
                txf_bottom_right[1].setText(Double.toString(Avg));
                txf_bottom_right[2].setText(res);

            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj_1[] = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    obj_1[i]=txf[i].getText();
                }
                dtm.addRow(obj_1);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<cols.length; i++){
                    dtm.setValueAt(txf[i].getText().toString(), jt.getSelectedRow(), i);

                }

            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.removeRow(jt.getSelectedRow());
            }
        });


        jp_top_left.setBounds(10,10,150,150);
        jp_top_right.setBounds(180,10,400,150);
        jp_bottom_left.setBounds(10, 160, 200, 200);
        jp_bottom_right.setBounds(240, 165,300, 200);
        add(jp_top_right);
        add(jp_top_left);
        add(jp_bottom_left);
        add(jp_bottom_right);
        setVisible(true);
    }
    public static void main(String[] args) {
        new practice_StudentScoreManage();
    }
}
