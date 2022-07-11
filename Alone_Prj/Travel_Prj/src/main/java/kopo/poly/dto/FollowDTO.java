package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowDTO {

    private int user_seq;
    private int follower;
    private int followee;
    private String user_nickname;
    private String user_introducation;
    private String file_detail_path;
    private String user_file_path;
}
