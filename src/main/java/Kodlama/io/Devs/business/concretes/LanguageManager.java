package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
import Kodlama.io.Devs.business.responses.GetLanguageByIdResponse;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologyResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Devs.entity.concretes.Language;
import Kodlama.io.Devs.entity.concretes.Technology;

@Service
public class LanguageManager implements LanguageService{
	
	private LanguageRepository languageRepository;
	private TechnologyRepository technologyRepository;

	@Autowired
	public LanguageManager(LanguageRepository languageRepository, TechnologyRepository technologyRepository) {
		this.languageRepository = languageRepository;
		this.technologyRepository=technologyRepository;
	}

	@Override
	public List<GetAllLanguageResponse> getAll() {
	    List<Language> languages=this.languageRepository.findAll();
	    List<Technology> technologies=this.technologyRepository.findAll();
	    
	    List<GetAllLanguageResponse> languageResponse=new ArrayList<GetAllLanguageResponse>();
	    
	    for (Language language : languages) {
            GetAllLanguageResponse languageReponse=new GetAllLanguageResponse();
            
            List<GetAllTechnologyResponse> technologyResponses=new ArrayList<GetAllTechnologyResponse>();
            
            for (Technology technology : technologies) {
                
                if (technology.getLanguage().getId()==language.getId()) {
                    
                    GetAllTechnologyResponse technologyResponse= new GetAllTechnologyResponse();
                    technologyResponse.setId(technology.getId());
                    technologyResponse.setName(technology.getName());
                    technologyResponse.setLanguageId(technology.getLanguage().getId());
                    
                    technologyResponses.add(technologyResponse);
                }
            }
            
            languageReponse.setId(language.getId());
            languageReponse.setName(language.getName());
            //languageReponse.setFrameworks(technologyResponses);
            languageResponse.add(languageReponse);
        }
	    
		return languageResponse;
	}

	@Override
	public List<GetAllLanguageResponse> getAllWithQuery() {
		return this.languageRepository.getAllWithQuery();
	}

	@Override
	public GetLanguageByIdResponse getById(int languageId) throws Exception {
	    Optional<Language> findLanguage=languageRepository.findById(languageId);
	    List<Technology> technologies=this.technologyRepository.findAll();
	    
	    GetLanguageByIdResponse responseItem=new GetLanguageByIdResponse();
	   
	    if (findLanguage.isPresent()) {
	        Language getLanguage=findLanguage.get();
	        
	        List<GetAllTechnologyResponse> technologyResponses=new ArrayList<GetAllTechnologyResponse>();
            
            for (Technology technology : technologies) {
                
                if (technology.getLanguage().getId()==getLanguage.getId()) {
                    
                    GetAllTechnologyResponse technologyResponse= new GetAllTechnologyResponse();
                    
                    technologyResponse.setId(technology.getId());
                    technologyResponse.setName(technology.getName());
                    technologyResponse.setLanguageId(technology.getLanguage().getId());
                    
                    technologyResponses.add(technologyResponse);
                }
            }
	        responseItem.setName(getLanguage.getName());
	        responseItem.setId(getLanguage.getId());
	        responseItem.setFrameworks(technologyResponses);
	        return responseItem;
            
        }else {
            throw new Exception("Kayıt Bulunamadı!!! ");
        }
	 
	}


	@Override
	public void create(CreateLanguageRequest createLanguageRequest) throws Exception{
	    Language toSave=new Language();
	
	    if (createLanguageRequest.getName().isEmpty()) {
			throw new Exception("İsim boş bırakılamaz!!!");
		}
	    
		for (Language lng : this.languageRepository.findAll()) {
		    if (createLanguageRequest.getName().toLowerCase().equalsIgnoreCase(lng.getName().toLowerCase())) {
                throw new Exception("Bu kayıt mevcut!!!");
            }
		    
        }
		
		toSave.setName(createLanguageRequest.getName());
		this.languageRepository.save(toSave);
       
	}

	@Override
	public void delete(int languageId) throws Exception {
		languageRepository.deleteById(languageId);
	}

	@Override
	public void update (int id,UpdateLanguageRequest updateLanguageRequest)  throws Exception {
		Optional<Language>language1=this.languageRepository.findById(id);
		if (language1.isPresent()) {
            Language toUpdate=language1.get();
            toUpdate.setName(updateLanguageRequest.getName());
            this.languageRepository.save(toUpdate);
        }else {
            throw new Exception("Kayit Bulunamadi!!!");
        }
	}

}

