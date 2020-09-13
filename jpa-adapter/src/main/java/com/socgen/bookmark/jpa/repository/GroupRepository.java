package com.socgen.bookmark.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.bookmark.jpa.entity.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

}
