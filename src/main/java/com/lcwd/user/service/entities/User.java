package com.lcwd.user.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME" , length = 25)
    private String name;

    @Column(name = "EmailId" , length = 50)
    private String email;

    @Column(name = "MobileNo")
    private int mobile;

    @Column(name = "ABOUT" , length = 120)
    private String about;

    @Transient          // doest need to save in the database
    private List<Rating> ratings = new ArrayList<>();

}
