package rm.tabou2.service.tabou.operation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rm.tabou2.service.dto.ModeAmenagement;
import rm.tabou2.service.mapper.tabou.operation.ModeAmenagementMapper;
import rm.tabou2.service.tabou.operation.ModeAmenagementService;
import rm.tabou2.storage.tabou.dao.operation.ModeAmenagementCustomDao;
import rm.tabou2.storage.tabou.dao.operation.ModeAmenagementDao;

@Service
public class ModeAmenagementServiceImpl implements ModeAmenagementService {

    @Autowired
    private ModeAmenagementDao modeAmenagementDao;

    @Autowired
    private ModeAmenagementCustomDao modeAmenagementCustomDao;

    @Autowired
    private ModeAmenagementMapper modeAmenagementMapper;

    @Override
    public Page<ModeAmenagement> searchModesAmenagement(Pageable pageable) {
        return modeAmenagementMapper.entitiesToDto(modeAmenagementCustomDao.searchModeAmenagement(pageable), pageable);
    }

}
