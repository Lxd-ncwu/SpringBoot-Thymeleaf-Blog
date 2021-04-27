package com.lxd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Lxd
 * @date : 21:31 2021/4/5
 * 相册实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
