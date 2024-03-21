package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.model.Category;
import ru.media.accounting.repository.CategoryRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Категория с title = " + title + " не найдена"));
    }
}
