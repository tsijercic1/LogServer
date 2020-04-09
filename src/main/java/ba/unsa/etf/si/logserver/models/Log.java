package ba.unsa.etf.si.logserver.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private LocalDateTime timestamp;

    @OneToOne
    private SimpleAction action;
}
