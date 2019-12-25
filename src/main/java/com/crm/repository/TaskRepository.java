package com.crm.repository;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.dto.TaskDTO;
import com.crm.dto.TaskStatusDTO;
import com.crm.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>{
	
	Task findByName(String name);
	
	@Query("SELECT new com.crm.dto.TaskDTO(t.id, t.name, t.endDate, t.groupId, g.name, t.userId, u.fullname, t.statusId, s.name) "
			+ " FROM Task t"
			+ " JOIN Group g on t.groupId = g.id"
			+ " JOIN User u on t.userId = u.id"
			+ " JOIN Status s on t.statusId = s.id")
	List<TaskDTO> findTaskDTO();
	
	@Query("SELECT new com.crm.dto.TaskStatusDTO(count(t.id), t.statusId)"
			+ " From Task t"
			+ " GROUP BY statusId")
	List<TaskStatusDTO> findTaskStatusDTO();
	int countByUserId(String id);
	
	int countByUserIdAndStatusId(String userId, String statusId);
	
	List<Task> findByUserIdAndStatusId(String userId,String statusId);

}
