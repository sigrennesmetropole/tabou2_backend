package rm.tabou2.service.impl;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import rm.tabou2.service.AgapeoService;
import rm.tabou2.service.ProgrammeService;
import rm.tabou2.service.dto.Logements;
import rm.tabou2.service.dto.Programme;
import rm.tabou2.service.helper.AuthentificationHelper;
import rm.tabou2.service.mapper.ProgrammeMapper;
import rm.tabou2.storage.tabou.dao.ProgrammeCustomDao;
import rm.tabou2.storage.tabou.dao.ProgrammeDao;
import rm.tabou2.storage.tabou.entity.ProgrammeEntity;
import rm.tabou2.storage.tabou.item.ProgrammeCriteria;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements ProgrammeService {

    @Autowired
    private ProgrammeDao programmeDao;

    @Autowired
    private ProgrammeCustomDao programmeCustomDao;

    @Autowired
    private AgapeoService agapeoService;

    @Autowired
    private ProgrammeMapper programmeMapper;

    @Autowired
    private AuthentificationHelper authentificationHelper;

    @Override
    public Programme addProgramme(Programme programme) {

        ProgrammeEntity programmeEntity = programmeMapper.dtoToEntity(programme);

        //Ajout des dates et infos sur l'utilisateur connecté
        programmeEntity.setCreateDate(new Date());
        programmeEntity.setModifDate(new Date());
        programmeEntity.setCreateUser(authentificationHelper.getConnectedUsername());
        programmeEntity.setModifUser(authentificationHelper.getConnectedUsername());
        programmeEntity.setNumAds(programme.getNumPc());

        programmeEntity = programmeDao.save(programmeEntity);

        return programmeMapper.entityToDto(programmeEntity);

    }

    @Override
    public Programme getProgrammeById(long programmeId) {

        Optional<ProgrammeEntity> programmeEntityOpt = programmeDao.findById(programmeId);

        if (programmeEntityOpt.isEmpty()) {
            throw new NoSuchElementException("Le programme id=" + programmeId + " n'existe pas");
        }

        return programmeMapper.entityToDto(programmeEntityOpt.get());

    }

    @Override
    public Page<Programme> searchProgrammes(ProgrammeCriteria programmeCriteria, Pageable pageable) {
        if (BooleanUtils.isTrue(programmeCriteria.getDiffusionRestreinte()) && !authentificationHelper.hasRestreintAccess()) {
            throw new AccessDeniedException("Accès non autorisé à des programmes d'accès restreint");
        }
        return programmeMapper.entitiesToDto(programmeCustomDao.searchProgrammes(programmeCriteria, pageable), pageable);
    }

    public Logements getLogements() {

        //Faire la comparaison entre le nombre de logements issus de la couche agapeo

        return null;

    }


}
