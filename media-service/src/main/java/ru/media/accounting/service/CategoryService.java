package ru.media.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.media.accounting.dto.category.CategoryRequest;
import ru.media.accounting.exception.ElementAlreadyExistsException;
import ru.media.accounting.model.Category;
import ru.media.accounting.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Получить категорию по названию.
     * @param title название категории. Например: "Указатель"
     * @return {@link Category} категорию.
     */
    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Категория с title = " + title + " не найдена"));
    }

    /**
     * Получить все категории.
     * @return список категорий.
     */
    public List<Category> findAll(){
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            throw new NoSuchElementException("Категории не найдены");
        }
        return categoryList;
    }

    /**
     * Создать новую категорию.
     * @param categoryRequest запрос категории.
     * @return {@link Category} категорию.
     */
    public Category save(CategoryRequest categoryRequest){
        if(categoryRepository.findByTitle(categoryRequest.getTitle()).isPresent()) {
            throw new ElementAlreadyExistsException(
                    "Категория с именем " + categoryRequest.getTitle() + " уже существует");
        }

        Category category = new Category();
        category.setTitle(categoryRequest.getTitle());

        return categoryRepository.save(category);
    }

    /**
     * Обновить категорию.
     * @param categoryRequest запрос категории.
     * @return {@link Category} категорию.
     */
    public Category update(CategoryRequest categoryRequest) {
        Category category = findByTitle(categoryRequest.getTitle());
        category.setTitle(categoryRequest.getTitle());
        return categoryRepository.save(category);
    }

    /**
     * Удалить категорию.
     * @param categoryRequest запрос категории.
     */
    public void delete(CategoryRequest categoryRequest) {
        Category category = findByTitle(categoryRequest.getTitle());
        categoryRepository.delete(category);
    }
}
