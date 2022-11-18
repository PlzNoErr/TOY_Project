package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
    private final UserService userService;

    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
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
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            Optional<User> info = userService.checkUserForLogin(user.getUserId(), user.getPassword());
            if (info.isPresent()) {
                result.put("access-token", jwtUtil.createToken("userId", info.get().getUserId()));
                result.put("userSeq", info.get().getUserSeq());
                result.put("userId", info.get().getUserId());
                result.put("nickName", info.get().getNickName());
                status = HttpStatus.ACCEPTED;
            } else {
                result.put("message", "FAIL");
                status = HttpStatus.NO_CONTENT;
            }
        } catch (Exception e) {
            result.put("message", "FAIL");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/checksignup")
    @ApiOperation(value = "회원가입의 중복 여부를 확인한다.")
    public ResponseEntity<?> checkReduplication(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<User> infoId = userService.checkForReduplicationCheckById(user.getUserId());
            Optional<User> infoNickname = userService.checkForReduplicationCheckByNickname(user.getNickName());
            if (infoId.isPresent()) {
                result.put("userId", infoId.get().getUserId());
            } else {
                result.put("userId", "");
            }
            if (infoNickname.isPresent()) {
                result.put("nickName", infoNickname.get().getNickName());
            } else {
                result.put("nickName", "");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
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
