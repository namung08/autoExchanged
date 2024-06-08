package com.exchanger.fram;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.utils.Exchange;

public class CurrencyFrambuy extends JPanel {

    private JTextField code, howMuch;
    private JButton muchCheck;
    private Exchange c = new Exchange();

    // 유효한 통화 코드 목록
    private static final String[] VALID_CURRENCY_CODES = {
            "AED", "AUD", "BHD", "BND", "CAD", "CHF", "CNH", "DKK", "EUR", "GBP",
            "HKD", "IDR", "JPY", "KRW", "KWD", "MYR", "NOK", "NZD", "SAR", "SEK", "SGD",
            "THB", "USD"
    };

    public CurrencyFrambuy() {
        setLayout(new GridLayout(3, 2));
        code = new JTextField(3);
        howMuch = new JTextField(100);

        add(new JLabel("통화코드 입력:", JLabel.LEFT));
        add(code);
        add(new JLabel("구매금액 입력:", JLabel.LEFT));
        add(howMuch);

        muchCheck = new JButton("변환");

        muchCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = code.getText().toUpperCase(); // 대문자로 변환하여 비교
                try {
                    int intValue = Integer.parseInt(howMuch.getText());

                    // 입력된 통화 코드가 유효한지 확인
                    if (isValidCurrencyCode(text)) {
                        c.sellCurrency(text, intValue);
                        // 판매금액을 구하는 메서드인 sellCurrency 대신 buyCurrency를 호출해야 올바른 결과를 얻습니다.
                        JOptionPane.showMessageDialog(null, c.buyCurrency(text, intValue) + " " + text, "구매", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "잘못된 입력", "유효한 통화 코드를 입력하세요.", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "잘못된 입력", "유효한 정수를 입력하세요.", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(muchCheck);
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
