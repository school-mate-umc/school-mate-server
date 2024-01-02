package challenge.schoolMate.web.dto;

import challenge.schoolMate.domain.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long userId;
    private String userEmail;
    private String userName;

    public UserDto(User user) {
        this.userId = user.getUser_id();
        this.userEmail = user.getEmail();
        this.userName = user.getUser_name();
    }
}
