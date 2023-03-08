package Kodlama.io.Devs.business.responses;

import java.util.List;

import Kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLanguageResponse {
    
    private int id;
    private String name;
    private String frameworkName;
    //private List<GetAllTechnologyResponse> frameworks;
}
