package Kodlama.io.Devs.business.responses.technology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTechnologyResponse {
    private int id;
    private String name;
    private int languageId;
}
