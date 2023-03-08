package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
import Kodlama.io.Devs.business.responses.GetLanguageByIdResponse;

public interface LanguageService {
	
	List<GetAllLanguageResponse> getAll();
	List<GetAllLanguageResponse> getAllWithQuery();
	GetLanguageByIdResponse getById(int languageId) throws Exception;
	void create(CreateLanguageRequest createLanguageRequest) throws Exception ;
	void delete (int languageId) throws Exception ;
	void update (int id, UpdateLanguageRequest updateLanguageRequest) throws Exception;

}
