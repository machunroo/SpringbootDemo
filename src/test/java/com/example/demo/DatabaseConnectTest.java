package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
public class DatabaseConnectTest {
    // MySQL Connector 의 클래스. DB 연결 드라이버 정의
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    // DB 경로
    private static final String URL = "jdbc:mariadb://61.100.2.75:3306/tcmsDev?defaultAutoCommit=false&allowMasterDownConnections=true&allowMultiQueries=true";
    private static final String USER = "tcmsDev";
    private static final String PASSWORD = "tcmsDev@1234";

    @Test
    public void testConnection() throws Exception {
        // DBMS에게 DB 연결 드라이버의 위치를 알려주기 위한 메소드
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
