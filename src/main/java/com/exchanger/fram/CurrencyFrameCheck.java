package com.exchanger.fram;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.utils.CurrencyCodeMap;

import java.util.Map;
import java.util.TreeMap;

public class CurrencyFrameCheck extends JFrame {
    private static Map<String, String> codeMap = getCodeMap();

    public CurrencyFrameCheck() {
        setTitle("통화코드");

        StringBuilder message = new StringBuilder("<html>");

        // 통화 코드와 국가명을 출력하는 부분
        if (codeMap != null) {
            codeMap.forEach((code, countryName) ->
                    message.append("통화코드: ").append(code).append(", 국가명: ").append(countryName).append("<br>"));
        } else {
            // 통화 코드 정보를 가져오는 데 실패한 경우의 메시지
            message.append("통화코드 정보를 가져오는 데 실패했습니다.");
        }

        JLabel label = new JLabel(message.toString());
        add(label);

        setSize(400, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static TreeMap<String, String> getCodeMap() {
        // 통화 코드 맵을 TreeMap으로 변환하여 정렬
        return new TreeMap<>(CurrencyCodeMap.getCodeMap());
    }
}
