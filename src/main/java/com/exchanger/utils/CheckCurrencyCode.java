package com.exchanger.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CheckCurrencyCode {
    // API 연결 객체
    private static HttpURLConnection connection;
    // 통화코드 리스트를 저장할 배열
    private String[][] currencyCodeList;

    // 생성자 이름을 수정하였습니다.
    public void checkCurrencyCode() {
        // JSON 데이터 파싱을 위한 객체 생성
        JSONParser parser = new JSONParser();
        // API 호출을 위한 인증 키, 조회 날짜, 데이터 타입, 그리고 결과로 받아올 환율 값
        String authKey = "Wjt0pUxzkxWKI1rZzXbnWGYhmqgzIO9C";
        String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String dataType = "AP01";

        try {
            URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authKey
                    + "&searchdate=" + searchDate + "&data=" + dataType);

            connection = (HttpURLConnection) url.openConnection();

            // API 호출을 위한 초기 설정
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // API 응답 상태 코드 확인
            int status = connection.getResponseCode();

            // HTTP 상태 코드에 대한 주석 추가
            /*
             * HTTP 상태 코드 정리 1xx(정보) : 요청을 받았으며 프로세스를 계속 진행합니다. 2xx(성공) : 요청을 성공적으로 받았으며
             * 인식했고 수용하였습니다. 3xx(리다이렉션) : 요청 완료를 위해 추가 작업 조치가 필요합니다. 4xx(클라이언트 오류) : 요청의 문법이
             * 잘못되었거나 요청을 처리할 수 없습니다. 5xx(서버 오류) : 서버가 명백히 유효한 요청에 대한 충족을 실패했습니다.
             */
            if (status > 299) {
                // API 호출 실패 시 예외 처리
                throw new RuntimeException("API 호출 실패. 응답 코드: " + status);
            } else {
                // API 호출 성공 시 응답 내용을 읽어와서 JSON 파싱

                // BufferedReader는 Java에서 데이터를 읽기 위한 클래스
                // Reader 클래스의 한 종류
                // BufferedReader는 데이터를 버퍼에 읽어와서 성능을 향상시키기 위해 사용 됨

                // InputStreamReader는 Java의 입출력 클래스로서,
                // 바이트 기반의 InputStream을 문자 기반의 Reader로 변환해주는 역할을 합니다
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                // StringBuffer는 Java에서 문자열을 변경할 수 있는 가변(mutable)한 클래스
                // 문자열을 여러 번 수정해야 할 경우에 사용
                StringBuffer responseContent = new StringBuffer();
                // 이 코드는 BufferedReader를 사용하여 서버 응답을 행 단위로
                // 읽어와서 responseContent에 추가하는 부분입니다
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

                // JSON 응답 파싱
                /*
                 * responseContent.toString()은 서버 응답을 문자열로 변환합니다. JSONParser 객체인 parser를 사용하여 해당
                 * 문자열을 JSON 형식으로 파싱합니다. 파싱된 결과를 JSONArray 객체인 exchangeRateInfoList에 할당합니다.
                 */
                JSONArray codeList = (JSONArray) parser.parse(responseContent.toString());
                // JSON에 등록된 국가 코드 정보 조회
                // codeList에 있는 각 JSON 객체를 for 문을 사용하여 순회 시킴
                int size = codeList.size();
                currencyCodeList = new String[size][2];

                for (int i = 0; i < size; i++) {
                    // 현재 순회 중인 JSON 객체를 JSONObject로 캐스팅,
                    // countryCode에 저장
                    JSONObject countryCode = (JSONObject) codeList.get(i);
                    currencyCodeList[i][0] = countryCode.get("cur_unit").toString();
                    currencyCodeList[i][1] = countryCode.get("cur_nm").toString();
                    System.out.printf("통화코드 : %3s, 국가명 : %3s\n", currencyCodeList[i][0],
                            currencyCodeList[i][1]);
                }
            }
        } catch (MalformedURLException e) {
            // URL 형식이 잘못되었을 때 예외 처리
            System.out.println("[ERROR] URL형식이 잘못 되었습니다. 관리자에게 문의하세요.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            // 입출력 예외 처리
            System.out.println("[ERROR] 입출력 오류가 발생했습니다. 다시 시도하세요.");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            // JSON 파싱 예외 처리
            System.out.println("[ERROR] JSON 파싱 중 오류가 발생했습니다. 서버 응답 형식을 확인하세요.");
            throw new RuntimeException(e);
        } finally {
            // 연결 종료
            connection.disconnect();
        }

        if (currencyCodeList == null) {
            System.out.println("코드가 잘못됨 다시 지정");
        }
    }

    public String[][] getCurrencyCodeList() {
        return currencyCodeList;
    }
}
