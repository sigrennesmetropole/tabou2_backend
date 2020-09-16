package rm.tabou2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rm.tabou2.service.TypeDocumentService;
import rm.tabou2.service.dto.TypeDocument;
import rm.tabou2.service.mapper.TypeDocumentMapper;
import rm.tabou2.service.utils.PaginationUtils;
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
    private TypeDocumentMapper typeDocumentMapper;

    @Override
    public TypeDocument addTypeDocument(TypeDocument typeDocument) {

        TypeDocumentEntity typeDocumentEntity = typeDocumentMapper.dtoToEntity(typeDocument);

        typeDocumentEntity = typeDocumentDao.save(typeDocumentEntity);

        return typeDocumentMapper.entityToDto(typeDocumentEntity);

    }

    @Override
    public void inactivateTypeDocument(Long typeDocumentId) {

        Optional<TypeDocumentEntity> typeDocumentOpt = typeDocumentDao.findById(typeDocumentId);
        if (typeDocumentOpt.isEmpty()) {
           throw new NoSuchElementException("Le type de document demandé n'existe pas, id=" + typeDocumentId);
        }

        TypeDocumentEntity typeDocument = typeDocumentOpt.get();
        typeDocument.setDateInactif(new Date());

        typeDocumentDao.save(typeDocument);

        //TODO : voir si on doit retourner qqch

    }

    @Override
    public List<TypeDocument> searchTiers(String keyword, Integer start, Boolean onlyActive, Integer resultsNumber, String orderBy, Boolean asc) throws Exception {

        List<TypeDocumentEntity> typesDocuments = null;

        if (Boolean.TRUE.equals(onlyActive)) {
            typesDocuments = typeDocumentDao.findOnlyActiveByKeyword(keyword, PaginationUtils.buildPageable(start, resultsNumber, orderBy, asc));
        } else {
            typesDocuments = typeDocumentDao.findByKeyword(keyword, PaginationUtils.buildPageable(start, resultsNumber, orderBy, asc));
        }

        return typeDocumentMapper.entitiesToDto(typesDocuments);

    }

}
