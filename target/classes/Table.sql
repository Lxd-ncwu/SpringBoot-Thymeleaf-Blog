


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `appreciation` bit(1) NOT NULL,
                           `commentabled` bit(1) NOT NULL,
                           `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                           `create_time` datetime(0) NULL DEFAULT NULL,
                           `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `published` bit(1) NOT NULL,
                           `recommend` bit(1) NOT NULL,
                           `share_statement` bit(1) NOT NULL,
                           `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `update_time` datetime(0) NULL DEFAULT NULL,
                           `views` int(11) NULL DEFAULT NULL,
                           `type_id` bigint(20) NULL DEFAULT NULL,
                           `user_id` bigint(20) NULL DEFAULT NULL,
                           `comment_count` int(255) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
                           INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE,
                           CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                           CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `create_time` datetime(0) NULL DEFAULT NULL,
                              `blog_id` bigint(20) NULL DEFAULT NULL,
                              `parent_comment_id` bigint(20) NULL DEFAULT NULL,
                              `admin_comment` bit(1) NOT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `blogaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                             `blogname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                             `create_time` datetime(0) NULL DEFAULT NULL,
                             `pictureaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `create_time` datetime(0) NULL DEFAULT NULL,
                              `parent_message_id` bigint(20) NULL DEFAULT NULL,
                              `admin_message` bit(1) NOT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture`  (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `pictureaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `picturedescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `picturename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `picturetime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `create_time` datetime(0) NULL DEFAULT NULL,
                           `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `type` int(11) NULL DEFAULT NULL,
                           `update_time` datetime(0) NULL DEFAULT NULL,
                           `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

insert into t_user(username,password) values ('admin','21232f297a57a5a743894a0e4a801fc3');