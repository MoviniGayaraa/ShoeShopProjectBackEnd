package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.controller;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.SupplierDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/health")
    public String health(){
        return "OK";
    }

    @PostMapping("/save")
    public boolean save(@RequestBody SupplierDTO supplierDTO){
        supplierDTO.setSupplier_id(supplierService.generateNextId());
        return supplierService.saveSupplier(supplierDTO);
    }

    @GetMapping
    public List<SupplierDTO> getAll(){
        return supplierService.getAllSuppliers();
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean update(@RequestBody SupplierDTO supplierDTO) throws NotFoundException {
        return supplierService.updateSupplierById(supplierDTO.getSupplier_id(),supplierDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestPart("email") String email) throws NotFoundException {
        return supplierService.deleteSupplierByEmail(email);
    }

    @GetMapping("/selectSupplier")
    public SupplierDTO selectSupplier(String email) throws NotFoundException {
        return supplierService.selectSupplierByEmail(email);
    }

    @GetMapping("/getSupplierIds")
    public List<String> getSupplierIds(){
        return supplierService.getSupplierIds();
    }
}
