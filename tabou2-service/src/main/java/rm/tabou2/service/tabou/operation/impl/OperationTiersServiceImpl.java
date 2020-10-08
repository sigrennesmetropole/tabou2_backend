package rm.tabou2.service.tabou.operation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import rm.tabou2.service.tabou.operation.OperationService;
import rm.tabou2.service.tabou.operation.OperationTiersService;
import rm.tabou2.service.dto.Operation;
import rm.tabou2.service.exception.AppServiceException;
import rm.tabou2.service.helper.AuthentificationHelper;
import rm.tabou2.storage.tabou.dao.operation.OperationDao;
import rm.tabou2.storage.tabou.dao.operation.OperationTiersDao;
import rm.tabou2.storage.tabou.dao.tiers.TiersDao;
import rm.tabou2.storage.tabou.dao.tiers.TypeTiersDao;
import rm.tabou2.storage.tabou.entity.operation.OperationEntity;
import rm.tabou2.storage.tabou.entity.operation.OperationTiersEntity;
import rm.tabou2.storage.tabou.entity.tiers.TiersEntity;
import rm.tabou2.storage.tabou.entity.tiers.TypeTiersEntity;

import java.util.Date;
import java.util.Optional;

@Service
public class OperationTiersServiceImpl implements OperationTiersService {


    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationDao operationDao;

    @Autowired
    private TypeTiersDao typeTiersDao;

    @Autowired
    private TiersDao tiersDao;

    @Autowired
    private OperationTiersDao operationTiersDao;

    @Autowired
    private AuthentificationHelper authentificationHelper;


    @Override
    public Operation associateTiersToOperation(long operationId, long tiersId, long typeTiersId) throws AppServiceException {

        OperationTiersEntity operationTiersEntity = new OperationTiersEntity();

        // récuperer type tiers
        Optional<TypeTiersEntity> typeTiersEntityOpt = typeTiersDao.findById(typeTiersId);
        if (typeTiersEntityOpt.isEmpty()) {
            throw new AppServiceException("Le typeTiersId = " + typeTiersId + " n'existe pas");
        }
        operationTiersEntity.setTypeTiers(typeTiersEntityOpt.get());


        // récuperer tiers
        Optional<TiersEntity> tiersEntityOpt = tiersDao.findById(tiersId);
        if (tiersEntityOpt.isEmpty()) {
            throw new AppServiceException("Le tiersID = " + tiersId + "n'existe pas");
        }
        operationTiersEntity.setTiers(tiersEntityOpt.get());

        // récuperer opération
        Optional<OperationEntity> operationEntityOpt = operationDao.findById(operationId);
        if (operationEntityOpt.isEmpty()) {
            throw new AppServiceException("L' operationId = " + operationId + "n'existe pas");
        }
        operationTiersEntity.setOperation(operationEntityOpt.get());

        operationTiersEntity.setCreateDate(new Date());
        operationTiersEntity.setCreateUser(authentificationHelper.getConnectedUsername());


        try {
            operationTiersDao.save(operationTiersEntity);
        } catch (DataAccessException e) {
            throw new AppServiceException("Impossible d'ajouter l'opération tiers", e);
        }


        return operationService.getOperationById(operationId);

    }

}
