package rm.tabou2.storage.sig.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "instructeur_secteur", schema = "urba_foncier")
public class SecteurDdsEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "secteur")
    private String secteur;


}
