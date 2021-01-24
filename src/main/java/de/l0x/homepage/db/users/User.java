package de.l0x.homepage.db.users;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    protected static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @NonNull
    private String userName;

    @Getter
    @NonNull
    private String password;

}
