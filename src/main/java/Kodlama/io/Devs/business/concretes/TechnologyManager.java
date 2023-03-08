package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.requests.technology.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technology.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import Kodlama.io.Devs.business.responses.technology.GetTechnologyByIdResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Devs.entity.concretes.Language;
import Kodlama.io.Devs.entity.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService{
    private TechnologyRepository technologyRepository;
    private LanguageRepository languageRepository;
    
    @Autowired
    public TechnologyManager(TechnologyRepository technologyRepository,LanguageRepository languageRepository) {
        this.technologyRepository=technologyRepository;
        this.languageRepository=languageRepository;
    }

    @Override
    public List<GetAllTechnologyResponse> getAll() {
        List<Technology> technologies=this.technologyRepository.findAll();
        List<GetAllTechnologyResponse> techResponse =new ArrayList<GetAllTechnologyResponse>();
        
        for (Technology technology : technologies) {
            GetAllTechnologyResponse response = new GetAllTechnologyResponse();
            response.setName(technology.getName());
            response.setId(technology.getId());
            response.setLanguageId(technology.getLanguage().getId());
            techResponse.add(response);
        }
        
        return techResponse;
        
    }

    @Override
    public void create(CreateTechnologyRequest createTechnologyRequest)throws Exception {
       Technology toSave=new Technology();
       Language lng=this.languageRepository.findById(createTechnologyRequest.getLanguageId()).get();
       
           if (createTechnologyRequest.getName().isEmpty()) {
            throw new Exception("İsim boş bırakılamaz!!! ");
        }
           if (lng != null) {
            toSave.setLanguage(lng);
          }else {
               throw new Exception("Üst sınıf bulunamadı");
          }
           for (Technology technology : this.technologyRepository.findAll()) {
            if(createTechnologyRequest.getName().equalsIgnoreCase(technology.getName())) {
                throw new Exception("Kayıt Mevcut!!! ");
            }
        }
           toSave.setName(createTechnologyRequest.getName());
           this.technologyRepository.save(toSave);
        
    }

    @Override
    public void update(int id, UpdateTechnologyRequest updateTechologyRequest) throws Exception {
        Optional<Technology> findTech=this.technologyRepository.findById(id);
        Language lng=this.languageRepository.findById(updateTechologyRequest.getLanguageId()).get();
        if (lng==null) {
            throw new Exception("Üst Sınıf Bulunamadı!!!");
        }
        if (updateTechologyRequest.getName().isEmpty()) {
            throw new Exception("İsim boş bırakılamaz!!! ");
        }
        if (findTech.isPresent()) {
            Technology toUpdate=findTech.get();
            toUpdate.setName(updateTechologyRequest.getName());
            toUpdate.setLanguage(lng);
            this.technologyRepository.save(toUpdate);
        }else {
            throw new Exception("Kayıt Mevcut Değil!!! ");
        }
        
    }

    @Override
    public void delete(int technologyId) {
       this.technologyRepository.deleteById(technologyId);
        
    }

    @Override
    public GetTechnologyByIdResponse getById(int id) throws Exception {
        Technology findTech=this.technologyRepository.findById(id).get();
        GetTechnologyByIdResponse getTechById= new GetTechnologyByIdResponse();
        
        getTechById.setId(findTech.getId());
        getTechById.setLanguageId(findTech.getLanguage().getId());
        getTechById.setName(findTech.getName());
        
        return getTechById;
    }

}
