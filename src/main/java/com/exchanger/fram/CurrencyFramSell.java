package com.exchanger.fram;

import com.exchanger.utils.Exchange;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 환율 계산 및 판매 화면을 구성하는 JPanel 클래스
public class CurrencyFramSell extends JPanel {

    private JTextField code;       // 통화 코드 입력 필드
    private JTextField howMuch;    // 판매 금액 입력 필드
    private JButton muchCheck;     // 변환 버튼
    private Exchange exchange;     // 환율 계산을 위한 Exchange 객체

    // 유효한 통화 코드 목록
    private static final String[] VALID_CURRENCY_CODES = {
        "AED", "AUD", "BHD", "BND", "CAD", "CHF", "CNH", "DKK", "EUR", "GBP",
        "HKD", "IDR", "JPY", "KRW", "KWD", "MYR", "NOK", "NZD", "SAR", "SEK", "SGD",
        "THB", "USD"
    };

    // 생성자
    public CurrencyFramSell() {
        // 3x2 그리드 레이아웃 설정
        setLayout(new GridLayout(3, 2));

        // 통화 코드 입력 필드 및 레이블 추가
        code = new JTextField(3);
        add(new JLabel("통화코드 입력:", JLabel.LEFT));
        add(code);

        // 판매 금액 입력 필드 및 레이블 추가
        howMuch = new JTextField(100);
        add(new JLabel("판매금액 입력:", JLabel.LEFT));
        add(howMuch);

        // 변환 버튼 추가
        muchCheck = new JButton("변환");
        muchCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력된 통화 코드 대문자로 변환
                String text = code.getText().toUpperCase();

                try {
                    // 입력된 판매 금액 정수로 파싱
                    int intValue = Integer.parseInt(howMuch.getText());

                    // 입력된 통화 코드가 유효한지 확인
                    if (isValidCurrencyCode(text)) {
                        // 환율 계산 및 결과 메시지 표시
                        String result = exchange.sellCurrency(text, intValue);
                        JOptionPane.showMessageDialog(null, result + " KRW", "판매", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "잘못된 입력", "유효한 통화 코드를 입력하세요.", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "잘못된 입력", "유효한 정수를 입력하세요.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 변환 버튼 추가
        add(muchCheck);

        // Exchange 객체 초기화
        exchange = new Exchange();
    }

    // 입력된 통화 코드가 유효한지 확인하는 메서드
    private boolean isValidCurrencyCode(String code) {
        for (String validCode : VALID_CURRENCY_CODES) {
            if (validCode.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
