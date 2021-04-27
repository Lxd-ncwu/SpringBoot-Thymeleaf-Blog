package com.lxd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : Lxd
 * @date : 18:26 2021/4/5
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname; //昵称
    private String username;
    private String password;
    private String email;
    private String avatar;  //头像
    private Integer type;
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
}
