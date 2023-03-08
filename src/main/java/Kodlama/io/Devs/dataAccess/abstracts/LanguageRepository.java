package Kodlama.io.Devs.dataAccess.abstracts;

import Kodlama.io.Devs.business.responses.GetAllLanguageResponse;
import Kodlama.io.Devs.business.responses.GetLanguageByIdResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import Kodlama.io.Devs.entity.concretes.Language;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer>{

    @Query("select new Kodlama.io.Devs.business.responses.GetAllLanguageResponse(l.id,l.name,t.name) from Language l " +
            "join l.technologies t ")
    List<GetAllLanguageResponse> getAllWithQuery();
	
}
