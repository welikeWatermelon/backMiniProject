package ssafy.project07.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ssafy.project07.domain.enums.Gender;
import ssafy.project07.domain.enums.UserRole;
import ssafy.project07.domain.calender.CalendarLog;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "members")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nickname;
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Pharmacist pharmacist;

    @OneToMany(mappedBy = "user")
    private List<SupplementIntake> supplementIntakes;

    @OneToMany(mappedBy = "user") // OneToMany는 기본이 LAZY
    private List<QuestHistory> questHistories;

    @OneToMany(mappedBy = "user")
    private List<CalendarLog> calendarLogs;

    @OneToMany(mappedBy = "user")
    private List<CommunityPost> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<Follow> follows;

    @OneToMany(mappedBy = "user")
    private List<UserBadge> userBadges;
}