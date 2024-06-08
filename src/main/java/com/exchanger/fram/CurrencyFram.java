package com.exchanger.fram;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CurrencyFram extends JFrame {
    private JPanel currentPanel;

    public CurrencyFram() {
        setSize(500, 300); // 500 x 300 크기의 창 만들기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 오른쪽 위 닫기 버튼을 눌러서 끌 수 있도록 함
        setTitle("환율계산기"); // 왼쪽 위에 있는 타이틀을 '환율계산기'로 지정

        Container contentPane = getContentPane(); // 프레임에서 컨텐트팬 받아오기
        contentPane.setLayout(null); // null 값 입력시 컴포넌트들을 좌표로 지정 가능

        // 레이어블 생성
        JLabel titleDescription = new JLabel("원하는 작업을 선택하세요");
        // 버튼 생성
        JButton sell = new JButton("판매한다");
        JButton buy = new JButton("구매한다");
        JButton check = new JButton("통화코드 확인");

        // 글꼴 크기 조절
        Font titleFont = new Font("고딕", Font.BOLD, 16); // 고딕 글꼴, 굵게, 크기 16
        titleDescription.setFont(titleFont);
        Font buttonFont = new Font("고딕", Font.PLAIN, 13); // 고딕 글꼴, 표준체, 크기 13
        sell.setFont(buttonFont);
        buy.setFont(buttonFont);
        check.setFont(buttonFont);

        // 레이블 위치 설정
        titleDescription.setBounds(150, 20, 200, 20);

        // 버튼 위치 설정
        sell.setBounds(50, 60, 120, 30);
        buy.setBounds(180, 60, 120, 30);
        check.setBounds(310, 60, 120, 30);

        // 컨텐트팬에 컴포넌트들 추가
        contentPane.add(titleDescription);
        contentPane.add(sell);
        contentPane.add(buy);
        contentPane.add(check);

        // 각 버튼에 대한 ActionListener 설정
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCurrencyFramSell();
            }
        });
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCurrencyFrambuy();
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCurrencyFramcheck();
            }
        });

        setVisible(true);
    }

    // "판매한다" 버튼을 클릭했을 때 호출되는 메서드
    private void showCurrencyFramSell() {
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }

        main.fram.CurrencyFramSell sellPanel = new main.fram.CurrencyFramSell();
        sellPanel.setBounds(50, 100, 400, 150);
        getContentPane().add(sellPanel);
        currentPanel = sellPanel;
        revalidate();
        repaint();
    }

    // "구매한다" 버튼을 클릭했을 때 호출되는 메서드
    private void showCurrencyFrambuy() {
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }

        CurrencyFrambuy buyPanel = new CurrencyFrambuy();
        buyPanel.setBounds(50, 100, 400, 150);
        getContentPane().add(buyPanel);
        currentPanel = buyPanel;
        revalidate();
        repaint();
    }

    // "통화코드 확인" 버튼을 클릭했을 때 호출되는 메서드
    private void showCurrencyFramcheck() {
        new main.fram.CurrencyFrameCheck();
    }
}
