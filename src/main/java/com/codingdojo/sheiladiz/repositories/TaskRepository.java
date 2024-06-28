package com.codingdojo.sheiladiz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.sheiladiz.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	//List<Task> findAll();
	
	List<Task> findByStatusNotContaining(String text);
	
	List<Task> findByStatusNotContainingOrderByPriorityDesc(String text);

	List<Task> findByStatusNotContainingOrderByPriorityAsc(String text);
	
}
