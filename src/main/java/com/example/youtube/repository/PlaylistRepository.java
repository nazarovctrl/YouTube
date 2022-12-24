package com.example.youtube.repository;

import com.example.youtube.entity.PlaylistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<PlaylistEntity, Integer> {
    @Query(value = "select count(*) from playlist where channel_id=?1", nativeQuery = true)
    Integer findDistinctFirstByOrderNum(String channelId);

    Page<PlaylistEntity> findAll(Pageable pageable);

    List<PlaylistEntity> getById(Integer id);
    List<PlaylistEntity> getByChannelIdOrderByOrderNumDesc(String id);
}
