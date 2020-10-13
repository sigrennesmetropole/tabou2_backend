package rm.tabou2.service.tabou.programme;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rm.tabou2.service.dto.Etape;
import rm.tabou2.service.dto.Programme;
import rm.tabou2.service.validator.ValidProgrammeCreation;
import rm.tabou2.service.validator.ValidProgrammeUpdate;
import rm.tabou2.storage.tabou.item.ProgrammeCriteria;

public interface ProgrammeService {


    /**
     * Ajout d'un programme.
     *
     * @param programme programme à ajouter
     * @return programme ajouté
     */
    Programme addProgramme(@ValidProgrammeCreation Programme programme);

    /**
     * modification d'un programme
     *
     * @param programme programme à modifier
     * @return programme modifié
     */
    Programme editProgramme(@ValidProgrammeUpdate Programme programme);

    /**
     * Modification de l'étape d'avancement d'un programme
     *
     * @param programmeId   identifiant du programme
     * @param etape         nouvelle étape
     * @return              nouveau programme
     */
    Programme editEtapeOfProgramme(long programmeId, Etape etape);

    /**
     * Récupération d'un programme par son identifiant.
     *
     * @param programmeId identifiant du programme
     * @return programme
     */
    Programme getProgrammeById(long programmeId);

    /**
     * Recherche de programme à partir des paramètres.
     *
     * @param programmeCriteria paramètres des programmes
     * @param pageable paramètre lié à la pagination
     * @return Liste des programme correspondant à la recherche
     */
    Page<Programme> searchProgrammes(ProgrammeCriteria programmeCriteria, Pageable pageable);
}
