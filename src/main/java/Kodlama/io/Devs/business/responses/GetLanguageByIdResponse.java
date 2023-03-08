package Kodlama.io.Devs.business.responses;

import java.util.List;

import Kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLanguageByIdResponse {

    private int id;
    private String name;
    private List<GetAllTechnologyResponse> frameworks;
}
