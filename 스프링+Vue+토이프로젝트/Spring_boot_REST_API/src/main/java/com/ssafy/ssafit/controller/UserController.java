package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ApiOperation(value = "회원가입 절차를 실행한다.")
    public ResponseEntity<?> registUser(@RequestBody User user) {
        try {
        userService.singupUser(user);
        return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{userId}")
    @ApiOperation(value = "{userId}의 회원탈퇴 절차를 실행한다.")
    public ResponseEntity<?> quitUser(@PathVariable String userId) {
        try {
        userService.withdrawalUser(userId);
        return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    @ApiOperation(value = "회원 정보를 수정한다.")
    public ResponseEntity<?> changeUser(@RequestBody User user) {
        try {
        userService.updateUserInfo(user);
        return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인 검증 실행")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
        Optional<User> info = userService.checkUserForLogin(user.getUserId(), user.getPassword());
        if(info.isPresent()){
            Cookie cookie = new Cookie("loginInfo",info.get().getUserId());
            return new ResponseEntity<Cookie>(cookie, HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/following")
    @ApiOperation(value = "팔로우 실행")
    public ResponseEntity<?> addFollow(@RequestParam long followingSeq, @RequestParam long followedSeq) {
        try {
        userService.addFollow(followingSeq, followedSeq);
        userService.changeFollowedCount(followedSeq, 1);
        userService.changeFollowingCount(followingSeq, 1);
        return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/unfollowing")
    @ApiOperation(value = "언팔로우 실행")
    public ResponseEntity<?> unFollow(@RequestParam long followingSeq, @RequestParam long followedSeq) {
        try {
        userService.unFollow(followingSeq, followedSeq);
        userService.changeFollowedCount(followedSeq, -1);
        userService.changeFollowingCount(followingSeq, -1);
        return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/followlist/{userSeq}")
    @ApiOperation(value = "내가 팔로우한 명단 가져오기")
    public ResponseEntity<?> getFollowlist(@PathVariable long userSeq) {
        try {
        List<User> followlist = userService.getAllFollowersByUserSeq(userSeq);
        return new ResponseEntity<List<User>>(followlist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
