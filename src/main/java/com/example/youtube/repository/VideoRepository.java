package com.example.youtube.repository;

import com.example.youtube.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, String>, PagingAndSortingRepository<VideoEntity, String> {

}
