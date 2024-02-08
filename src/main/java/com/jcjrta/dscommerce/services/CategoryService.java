package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.dto.CategoryDTO;
import com.jcjrta.dscommerce.entities.Category;
import com.jcjrta.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findall(){
        List<Category> cat = categoryRepository.findAll();
        return cat.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
