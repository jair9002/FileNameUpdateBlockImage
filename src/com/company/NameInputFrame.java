package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInputFrame extends JFrame {
    //생성자
    JfileChooserUtil jfileChooserUtil;

    public NameInputFrame() {
        setSize(300, 800); //크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("이름 입력기");
        Container contentPane = getContentPane();


        contentPane.setLayout(new BorderLayout()); //배치 관리자 설정
        contentPane.add(new JLabel("입력하기"), BorderLayout.NORTH);

        GridLayout indexLayout = new GridLayout(16,1);
        JPanel pW = new JPanel();
        pW.setLayout(indexLayout);
        GridLayout inputLayout = new GridLayout(16,1);
        JPanel pC = new JPanel();
        pC.setLayout(inputLayout);

        for(int i=0;i<16;i++){
            pW.add(new JLabel(Integer.toString(i+1)));
            pC.add(new JTextField());
        }
        JButton filePathButton = new JButton("파일 경로 탐색");



        contentPane.add(new JLabel("블록명 입력"),BorderLayout.NORTH);
        contentPane.add(pW,BorderLayout.WEST);
        contentPane.add(pC,BorderLayout.CENTER);
        contentPane.add(filePathButton,BorderLayout.SOUTH);



        ActionListener filePathListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfileChooserUtil = new JfileChooserUtil();
            }
        };
        filePathButton.addActionListener(filePathListener);

        setVisible(true);
    }
}
