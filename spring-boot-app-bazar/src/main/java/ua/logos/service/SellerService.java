package ua.logos.service;

import ua.logos.domain.SellerDTO;

import java.util.List;

public interface SellerService {
    SellerDTO saveSeller( SellerDTO sellerDTO);

    List< SellerDTO> findAllSeller();

    SellerDTO findSellerById(Long id);

    SellerDTO updateSeller(Long id, SellerDTO sellerDTO);

}
