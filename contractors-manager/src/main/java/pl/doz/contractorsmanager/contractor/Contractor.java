package pl.doz.contractorsmanager.contractor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "contractors")
@Data
public class Contractor {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "contractor_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "3"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    public Contractor(String name) {
        this.name = name;
    }

    public Contractor() {
    }

    public Contractor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
