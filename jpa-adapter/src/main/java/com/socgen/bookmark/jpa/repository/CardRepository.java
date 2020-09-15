package com.socgen.bookmark.jpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.bookmark.jpa.entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

}
