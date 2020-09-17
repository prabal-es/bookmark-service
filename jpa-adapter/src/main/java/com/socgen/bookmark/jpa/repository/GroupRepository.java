package com.socgen.bookmark.jpa.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socgen.bookmark.jpa.entity.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

	@Query("SELECT ge from GroupEntity ge JOIN ge.adminUsers ue where ge.company.urlContext =:companyContext and ue.urlContext =:userContext ")
	public List<GroupEntity> findAllAdminUserGroups(@Param("companyContext") String companyContext,
			@Param("userContext") String userContext);
	
	@Query("SELECT ge from GroupEntity ge LEFT JOIN ge.adminUsers ue where ge.company.urlContext =:companyContext and (ue IS NULL OR ue.urlContext !=:userContext)")
	public List<GroupEntity> findAllOtherUserGroups(@Param("companyContext") String companyContext,
			@Param("userContext") String userContext);

}
