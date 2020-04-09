package ba.unsa.etf.si.logserver.models;

import javax.persistence.*;

@Entity
@Table(name = "actions")
public class SimpleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
