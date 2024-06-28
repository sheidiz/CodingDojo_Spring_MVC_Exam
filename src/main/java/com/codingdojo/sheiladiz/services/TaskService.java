package com.codingdojo.sheiladiz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.sheiladiz.models.Task;
import com.codingdojo.sheiladiz.repositories.TaskRepository;
import com.codingdojo.sheiladiz.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository tr;

	@Autowired
	private UserRepository ur;

	public Task saveTask(Task newTask) {
		return tr.save(newTask);
	}

	public List<Task> allTasks() {
		return tr.findByStatusNotContaining("completed");
	}

	public List<Task> allTasksHighToLow() {
		return tr.findByStatusNotContainingOrderByPriorityAsc("completed");
	}

	public List<Task> allTasksLowToHigh() {
		return tr.findByStatusNotContainingOrderByPriorityDesc("completed");
	}

	public Task findTask(Long id) {
		return tr.findById(id).orElse(null);
	}

	public void deleteTask(Long id) {
		tr.deleteById(id);
	}

	public void markCompletedTask(Long id) {
		Task taskToComplete = findTask(id);

		taskToComplete.setStatus("completed");

		tr.save(taskToComplete);
	}
}
