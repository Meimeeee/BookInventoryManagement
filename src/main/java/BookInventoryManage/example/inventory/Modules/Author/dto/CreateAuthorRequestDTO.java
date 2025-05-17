package BookInventoryManage.example.inventory.Modules.Author.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAuthorRequestDTO {
    private String name;
    private LocalDate dob;



}
