package rm.tabou2.facade.controller.tabou.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import rm.tabou2.facade.api.DecisionsApi;
import rm.tabou2.service.dto.Decision;
import rm.tabou2.service.dto.PageResult;
import rm.tabou2.service.tabou.operation.DecisionService;
import rm.tabou2.service.utils.PaginationUtils;
import rm.tabou2.storage.tabou.entity.operation.DecisionEntity;

import javax.validation.Valid;

@Controller
public class DecisionApiController implements DecisionsApi {

    @Autowired
    private DecisionService decisionService;


    @Override
    public ResponseEntity<PageResult> getDecisions(@Valid Integer start, @Valid Integer resultsNumber, @Valid String orderBy, @Valid Boolean asc) throws Exception {

        Pageable pageable = PaginationUtils.buildPageable(start, resultsNumber, orderBy, asc, DecisionEntity.class);

        Page<Decision> page = decisionService.searchDecisions(pageable);

        return new ResponseEntity<>(PaginationUtils.buildPageResult(page), HttpStatus.OK);

    }
}
