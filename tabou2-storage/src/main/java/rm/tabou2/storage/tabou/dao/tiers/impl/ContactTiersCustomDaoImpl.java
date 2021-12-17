package rm.tabou2.storage.tabou.dao.tiers.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import rm.tabou2.storage.common.impl.AbstractCustomDaoImpl;
import rm.tabou2.storage.tabou.dao.tiers.ContactTiersCustomDao;
import rm.tabou2.storage.tabou.entity.tiers.ContactTiersEntity;
import rm.tabou2.storage.tabou.item.ContactTiersCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactTiersCustomDaoImpl extends AbstractCustomDaoImpl implements ContactTiersCustomDao {
    private static final String FIELD_NOM = "nom";
    private static final String FIELD_INACTIF = "inactif";
    private static final String FIELD_TIERS = "idTiers";


    @PersistenceContext(unitName = "tabouPU")
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<ContactTiersEntity> searchContactTiers(ContactTiersCriteria contactTiersCriteria, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<ContactTiersEntity> countRoot = countQuery.from(ContactTiersEntity.class);
        buildQuery(contactTiersCriteria, builder, countQuery, countRoot);
        countQuery.select(builder.countDistinct(countRoot));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        //Si aucun résultat
        if(totalCount == 0){
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }

        //Recherche
        CriteriaQuery<ContactTiersEntity> searchQuery = builder.createQuery(ContactTiersEntity.class);
        Root<ContactTiersEntity> searchRoot = searchQuery.from(ContactTiersEntity.class);
        buildQuery(contactTiersCriteria, builder, searchQuery, searchRoot);

        searchQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), searchRoot, builder));
        TypedQuery<ContactTiersEntity> typedQuery = entityManager.createQuery(searchQuery);

        List<ContactTiersEntity> results = typedQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        return new PageImpl<>(results, pageable, totalCount);
    }

    private void buildQuery(ContactTiersCriteria contactTiersCriteria, CriteriaBuilder builder,
                            CriteriaQuery<?> criteriaQuery, Root<ContactTiersEntity> root
    ) {

        if (contactTiersCriteria != null) {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(contactTiersCriteria.getNom())){
                predicateStringCriteria(contactTiersCriteria.getNom(), FIELD_NOM, predicates, builder, root);
            }
            //inactif
            if (contactTiersCriteria.getInactif() != null) {
                predicateCriteriaNullOrNot(!contactTiersCriteria.getInactif(), FIELD_INACTIF, predicates, builder, root);
            }
            if (contactTiersCriteria.getIdTiers() != null){
                predicateLongCriteria(contactTiersCriteria.getIdTiers(), FIELD_TIERS, predicates, builder, root);
            }

            //Définition de la clause Where
            if (CollectionUtils.isNotEmpty(predicates)) {
                criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            }


        }
    }
}
