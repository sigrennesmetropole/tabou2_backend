package rm.tabou2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rm.tabou2.service.TypeDocumentService;
import rm.tabou2.service.dto.TypeDocument;
import rm.tabou2.service.exception.AppServiceException;
import rm.tabou2.service.mapper.TypeDocumentMapper;
import rm.tabou2.service.utils.PaginationUtils;
import rm.tabou2.storage.tabou.dao.TypeDocumentCustomDao;
import rm.tabou2.storage.tabou.dao.TypeDocumentDao;
import rm.tabou2.storage.tabou.entity.TypeDocumentEntity;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {

    @Autowired
    private TypeDocumentDao typeDocumentDao;

    @Autowired
    private TypeDocumentCustomDao typeDocumentCustomDao;

    @Autowired
    private TypeDocumentMapper typeDocumentMapper;

    @Override
    public TypeDocument addTypeDocument(TypeDocument typeDocument) throws AppServiceException {

        TypeDocumentEntity typeDocumentEntity = typeDocumentMapper.dtoToEntity(typeDocument);

        try {
            typeDocumentEntity = typeDocumentDao.save(typeDocumentEntity);
        } catch (DataAccessException e) {
            throw new AppServiceException("Impossible d'ajouter un type document ", e);
        }

        return typeDocumentMapper.entityToDto(typeDocumentEntity);

    }

    @Override
    public TypeDocument editTypeDocument(TypeDocument typeDocument) throws AppServiceException {

        TypeDocumentEntity typeDocumentEntity;

        Optional<TypeDocumentEntity> typeDocumentEntityOpt = typeDocumentDao.findById(typeDocument.getId());

        if (typeDocumentEntityOpt.isEmpty()) {
            throw new NoSuchElementException("Le type de document id = " + typeDocument.getId() + " n'existe pas");
        }else{
            typeDocumentEntity = typeDocumentEntityOpt.get();
        }

        typeDocumentEntity.setDateInactif(typeDocument.getDateInactivite());

        typeDocumentEntity.setLibelle(typeDocument.getLibelle());

        // Enregistrement en BDD
        try {
            typeDocumentEntity = typeDocumentDao.save(typeDocumentEntity);
        } catch (DataAccessException e) {
            throw new AppServiceException("Impossible d'ajouter un type document ", e);
        }

        return typeDocumentMapper.entityToDto(typeDocumentEntity);
    }

    @Override
    public TypeDocument inactivateTypeDocument(Long typeDocumentId) throws NoSuchElementException, AppServiceException {

        TypeDocumentEntity typeDocument;

        Optional<TypeDocumentEntity> typeDocumentOpt = typeDocumentDao.findById(typeDocumentId);
        if (typeDocumentOpt.isEmpty()) {
            throw new NoSuchElementException("Le type de document id=" + typeDocumentId + " n'existe pas");
        }else{
            typeDocument = typeDocumentOpt.get();
        }

        typeDocument.setDateInactif(new Date());

        // Enregistrement en BDD
        try {
            typeDocument = typeDocumentDao.save(typeDocument);
        } catch (DataAccessException e) {
            throw new AppServiceException("Impossible d'ajouter un type document ", e);
        }

        return typeDocumentMapper.entityToDto(typeDocument);
    }

    @Override
    public Page<TypeDocument> searchTypeDocument(Long id, String libelle, Date dateInactif, Pageable pageable)  {

        Page<TypeDocumentEntity> typesDocuments = typeDocumentCustomDao.searchTypeDocument(id, libelle, dateInactif, pageable);

        return typeDocumentMapper.entitiesToDto(typesDocuments,pageable);

    }

}
