package teamchallenge.server.catalog.language.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;
import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.author.entity.AuthorRepository;
import teamchallenge.server.catalog.author.service.AuthorService;
import teamchallenge.server.catalog.language.entity.Language;
import teamchallenge.server.catalog.language.entity.LanguageRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getLanguages(List<String> languages) {
        List<Language> result = new ArrayList<>();

        for (String languageName : languages) {
            Language language = languageRepository.findByName(languageName).orElse(createAndGetLanguage(languageName));
            result.add(language);
        }
        return result;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    private Language createAndGetLanguage(String name) {
        Language language = new Language();
        language.setName(name.toLowerCase());
        languageRepository.save(language);
        return language;
    }

    @Override
    public List<Language> getLanguagesByName(List<String> languageNames) {
        List<Language> languages = new ArrayList<>();
        for (String name : languageNames) {
            Language language = languageRepository.findByName(name).orElseThrow(() -> new RuntimeException("Language not found"));
            if (language != null) {
                languages.add(language);
            }
        }
        return languages;
    }

}
