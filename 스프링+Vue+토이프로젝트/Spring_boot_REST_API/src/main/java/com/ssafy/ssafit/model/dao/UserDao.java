package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /*
     * 유저 테이블을 활용
     */

    // 회원가입
    void insertUser(User user);

    // 회원탈퇴
    void deleteUser(String userId);

    // 회원정보 수정
    void updateUser(User user);

    // 회원정보 하나만 가져오기 (id 활용)
    User seletUserById(String userId);

    //  login검증
    Optional<User> seletUserByIdandPassword(String userId, String password);

    // 조건에 맞는 회원정보 가져오기


    /*
    * 팔로우 테이블을 활용
    */

    // 팔로우
    void insertFollow(long followingSeq, long followedSeq);

    // 언팔로우
    void deleteFollow(long followingSeq, long followedSeq);

    // 유저 테이블 안의 팔로우 수의 변동
    void updateFollowedCount(long UserSeq, long change);

    // 유저 테이블 안의 팔로잉 수의 변동
    void updateFollowingCount(long UserSeq, long change);

    // 내가 팔로우한 모든 유저들 가져오기
    List<User> selectAllFollowersByUserSeq(long userSeq);



}
