package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.SellerDTO;
import ua.logos.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody SellerDTO sellerDTO) {

        SellerDTO sellerDTO1 = sellerService.saveSeller(sellerDTO);
        return new ResponseEntity<>(sellerDTO1, HttpStatus.CREATED);

    }

    @GetMapping("{sellerId:[0-9]{1,5}}")
    public ResponseEntity<?> getSellerById(@PathVariable("sellerId") Long id) {
        SellerDTO sellerDTO = sellerService.findSellerById(id);

        if (sellerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sellerDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllSeller() {
        List<SellerDTO> sellers = sellerService.findAllSeller();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @PutMapping("{sellerId:[0-9]{1,5}}")
    public ResponseEntity<?> updateCar(@PathVariable("sellerId") Long id, @RequestBody SellerDTO sellerToUpdate) {
        SellerDTO seller = sellerService.updateSeller(id, sellerToUpdate);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }


}
