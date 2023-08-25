package com.example.home.Repository;

import com.example.home.Entity.Home;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HomeRepository extends CrudRepository<Home,Integer> {
    @Query("select * from home where id = :id and psw = :psw")
    Optional<Home> selectOneByHomeId(@Param("id")String id,@Param("psw") String psw);

}
