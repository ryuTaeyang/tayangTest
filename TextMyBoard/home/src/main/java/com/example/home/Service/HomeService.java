package com.example.home.Service;

import com.example.home.Entity.Home;

import java.util.Optional;

public interface HomeService {
    Iterable<Home> selectAll();

    Optional<Home> selectOneById(Integer num);
    Optional<Home> selectOneByHomeId(String id, String psw);
    Boolean check(Integer num,String psw);
    Boolean checkLogin(String id, String psw);

    void insertMember(Home home);
    void updateMember(Home home);
    void deleteMember(Integer num);
}
