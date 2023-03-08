package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.technology.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technology.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import Kodlama.io.Devs.business.responses.technology.GetTechnologyByIdResponse;

public interface TechnologyService {
    
    List<GetAllTechnologyResponse> getAll();
    GetTechnologyByIdResponse getById(int id) throws Exception; 
    void create (CreateTechnologyRequest createTechnologyRequest) throws Exception;
    void update(int id, UpdateTechnologyRequest updateTechologyRequest) throws Exception;
    void delete(int technologyId);
}
