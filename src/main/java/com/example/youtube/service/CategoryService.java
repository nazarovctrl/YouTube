package com.example.youtube.service;

import com.example.youtube.dto.CategoryDTO;
import com.example.youtube.entity.CategoryEntity;
import com.example.youtube.exp.CategoryNotFoundException;
import com.example.youtube.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;


    public List<CategoryEntity> getAllFromDb(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CategoryEntity> Page;
        if (search != null)
            Page = repo.findByNameLikeIgnoreCase(pageable, search);
        else
            Page = (org.springframework.data.domain.Page<CategoryEntity>) repo.findAll();
        return Page.getContent();
    }


    public CategoryEntity getById(Integer id) {
        Optional<CategoryEntity> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Article Not Found");
        }
        return optional.get();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public void add(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        repo.save(entity);
    }

    public CategoryDTO edit(CategoryDTO dto, Integer id) {
        Optional<CategoryEntity> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category  not found");
        }


        CategoryEntity entity = new CategoryEntity();
        entity.setId(optional.get().getId());
        entity.setCreatedDate(optional.get().getCreatedDate());


        repo.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
}

