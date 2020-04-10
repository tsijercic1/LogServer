package ba.unsa.etf.si.logserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "actions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String object;
    private String description;
}
