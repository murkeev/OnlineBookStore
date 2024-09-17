package teamchallenge.server.catalog.language.service;

import teamchallenge.server.catalog.author.entity.Author;
import teamchallenge.server.catalog.language.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getLanguages(List<String> languages);

    List<Language> getAllLanguages();

    List<Language> getLanguagesByName(List<String> languageNames);
}
