package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.SellerDTO;
import ua.logos.entity.SellerEntity;
import ua.logos.repository.SellerRepository;
import ua.logos.service.SellerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public SellerDTO saveSeller(SellerDTO sellerDTO) {
        SellerEntity sellerEntity = dtoToEntity(sellerDTO);
        sellerRepository.save(sellerEntity);
        sellerDTO.setId(sellerEntity.getId());
        return sellerDTO;
    }

    @Override
    public List<SellerDTO> findAllSeller() {
        List<SellerEntity> sellerEntityList = sellerRepository.findAll();
        List<SellerDTO> sellerDTOS = new ArrayList<>();
        for (SellerEntity entity : sellerEntityList) {
            SellerDTO sellerDTO = entityToDTOMapper(entity);
            sellerDTOS.add(sellerDTO);
        }
        return sellerDTOS;
    }

    @Override
    public SellerDTO findSellerById(Long id) {
        boolean exists = sellerRepository.existsById(id);
        if (!exists) {
            return null;
        }
        SellerEntity sellerEntity = sellerRepository.findById(id).get();
        SellerDTO sellerDTO = entityToDTOMapper(sellerEntity);
        return sellerDTO;

    }

    @Override
    public SellerDTO updateSeller(Long id, SellerDTO sellerToUpdate) {
        boolean exists = sellerRepository.existsById(id);
        if (!exists) {
            return null;
        }
        SellerEntity sellerFromDB = sellerRepository.findById(id).get();
        sellerFromDB.setFirstName(sellerToUpdate.getFirstName());
        sellerFromDB.setLastName(sellerToUpdate.getLastName());
        sellerFromDB.setEmail(sellerToUpdate.getEmail());
        sellerFromDB.setPhoneNumber(sellerToUpdate.getPhoneNumber());
        SellerDTO sellerDTO1 = entityToDTOMapper(sellerFromDB);
        sellerRepository.save(sellerFromDB);
        sellerDTO1.setId(sellerFromDB.getId());
        return sellerDTO1;
    }

    private SellerDTO entityToDTOMapper(SellerEntity sellerEntity) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(sellerEntity.getId());
        sellerDTO.setFirstName(sellerEntity.getFirstName());
        sellerDTO.setLastName(sellerEntity.getLastName());
        sellerDTO.setEmail(sellerEntity.getEmail());
        sellerDTO.setPhoneNumber(sellerEntity.getPhoneNumber());

        return sellerDTO;

    }

    private SellerEntity dtoToEntity(SellerDTO sellerDTO) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(sellerDTO.getId());
        sellerEntity.setFirstName(sellerDTO.getFirstName());
        sellerEntity.setLastName(sellerDTO.getLastName());
        sellerEntity.setEmail(sellerDTO.getEmail());
        sellerEntity.setPhoneNumber(sellerDTO.getPhoneNumber());
        return sellerEntity;

    }
}
