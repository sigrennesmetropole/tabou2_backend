package rm.tabou2.service.sig;

import rm.tabou2.service.dto.PluiZonage;

import java.util.List;

public interface PluiService {
    List<PluiZonage> searchPlui(String keyword, Integer start, Integer resultsNumber, String orderBy, Boolean asc);
}