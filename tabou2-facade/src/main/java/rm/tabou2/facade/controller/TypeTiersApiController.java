package rm.tabou2.facade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import rm.tabou2.facade.api.TypesTiersApi;
import rm.tabou2.service.TypeTiersService;
import rm.tabou2.service.dto.PageResult;
import rm.tabou2.service.dto.TypeTiers;
import rm.tabou2.service.utils.PaginationUtils;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TypeTiersApiController implements TypesTiersApi {

    @Autowired
    private TypeTiersService typeTiersService;

    @Override
    public ResponseEntity<TypeTiers> addTypeTiers(@Valid TypeTiers typeTiers) throws Exception {
        return new ResponseEntity<>(typeTiersService.addTypeTiers(typeTiers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeTiers> editTypeTiers(@Valid TypeTiers typeTiers) throws Exception {
        return new ResponseEntity<>(typeTiersService.editTypeTiers(typeTiers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageResult> getTypeTiers(@Valid String libelle, @Valid Date dateInactivite, @Valid Integer start, @Valid Boolean onlyActive, @Valid Integer resultsNumber, @Valid String orderBy, @Valid Boolean asc) throws Exception {

        if (null == orderBy) {
            orderBy = "libelle";
        }

        Pageable pageable = PaginationUtils.buildPageable(start, resultsNumber, orderBy, asc);

        Page<TypeTiers> page = typeTiersService.searchTypeTiers(libelle, dateInactivite, pageable);

        return new ResponseEntity<>(PaginationUtils.buildPageResult(page), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeTiers> getTypeTiersById(Long typeTiersId) throws Exception {
        return new ResponseEntity<>(typeTiersService.getById(typeTiersId), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<TypeTiers> inactivateTypeTiers(Long typeTiersId) throws Exception {
        return new ResponseEntity<>(typeTiersService.inactivateTypeTiers(typeTiersId), HttpStatus.OK);
    }
}
