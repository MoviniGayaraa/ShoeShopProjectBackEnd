package lk.ijse.gdse.Controller;

import lk.ijse.gdse.DTO.SizeDTO;
import lk.ijse.gdse.Entity.Size;
import lk.ijse.gdse.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("/health")
    public String health(){
        return "Size Health Check is working";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean save(@RequestBody SizeDTO sizeDTO){
        return sizeService.saveSize(sizeDTO);
    }

    @GetMapping
    public List<SizeDTO> getAll(){
        return sizeService.getAllSizes();
    }

    @GetMapping("/getItemIds")
    public List<String> getItemIds(){
        return sizeService.getItemIds();
    }

    @DeleteMapping("/delete")
    public boolean delete(
            @RequestPart("item_id") String item_id,
            @RequestPart("size_id") String size_id
    ){
        return sizeService.deleteSize(item_id,size_id);
    }

    @GetMapping("/getDataWithSize/{item_code}/{item_size}")
    public Size getDataWithSizeAndItemID(
            @PathVariable String item_code,
            @PathVariable String item_size
    ){
        return sizeService.getDataWithSizeAndItemID(item_code,item_size);
    }
}

