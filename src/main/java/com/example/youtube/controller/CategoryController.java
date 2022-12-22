package com.example.youtube.controller;

import com.example.youtube.api.ApiResponse;
import com.example.youtube.dto.CategoryDTO;
import com.example.youtube.entity.CategoryEntity;
import com.example.youtube.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryService service;


    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN','VIEW_CATEGORY')")
    @GetMapping
    public HttpEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "1") int size,
                                @RequestParam(name = "search", required = false)  String search,
                                Model model){
        List<CategoryEntity> allStudentFromDb = service.getAllFromDb(page, size, search);

        return ResponseEntity.ok(new ApiResponse("",true,allStudentFromDb));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ADD_CATEGORY')")
    @PostMapping()
    public HttpEntity<?> add(@Valid @RequestBody CategoryDTO dto){
        service.add(dto);
        return ResponseEntity.ok(new ApiResponse("",true,null));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','EDIT_CATEGORY')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody CategoryDTO  dto,@PathVariable Integer id){
        service.edit(dto,id);
        return ResponseEntity.ok(new ApiResponse("",true,null));
    }



    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','GET_BY_CATEGORY_ID')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        CategoryEntity continentById =service.getById(id);
        return ResponseEntity.ok(new ApiResponse("",true,continentById));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','DELETE_BY_CATEGORY_ID')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("",true,null));
    }
}
