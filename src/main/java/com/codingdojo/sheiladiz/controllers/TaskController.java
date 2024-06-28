package com.codingdojo.sheiladiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.sheiladiz.models.Task;
import com.codingdojo.sheiladiz.models.User;
import com.codingdojo.sheiladiz.services.TaskService;
import com.codingdojo.sheiladiz.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public String dashboard(HttpSession session, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.findUser(userTemp.getId()));
		model.addAttribute("tasks", taskService.allTasks());

		return "dashboard.jsp";
	}

	@GetMapping("/tasks/priority_high_low")
	public String dashboardHighLow(HttpSession session, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.findUser(userTemp.getId()));
		model.addAttribute("tasks", taskService.allTasksHighToLow());

		return "dashboard.jsp";
	}

	@GetMapping("/tasks/priority_low_high")
	public String dashboardLowHigh(HttpSession session, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.findUser(userTemp.getId()));
		model.addAttribute("tasks", taskService.allTasksLowToHigh());

		return "dashboard.jsp";
	}

	@GetMapping("/tasks/{id}")
	public String show(@PathVariable("id") Long id, HttpSession session, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.findUser(userTemp.getId()));
		model.addAttribute("task", taskService.findTask(id));

		return "show.jsp";
	}

	@GetMapping("/tasks/new")
	public String taskNew(HttpSession session, @ModelAttribute("task") Task task, Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}

		model.addAttribute("users", userService.allUsers());

		return "new.jsp";
	}

	@PostMapping("/tasks/create")
	public String taskCreate(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session,
			Model model, RedirectAttributes redirectAttributes) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}

		model.addAttribute("users", userService.allUsers());

		if (result.hasErrors()) {
			return "new.jsp";
		}

		User userTryingToBeAssigned = task.getAssignee();
		List<Task> tasksAssigned = userTryingToBeAssigned.getTasksAssigned().stream().filter((t)-> !t.getStatus().contains("completed")).toList();

		if (tasksAssigned.size() == 3) {
			redirectAttributes.addFlashAttribute("errorAssignee",
					"User " + userTryingToBeAssigned.getName() + " is already assigned to 3 tasks.");
			return "redirect:/tasks/new";
		}

		taskService.saveTask(task);

		return "redirect:/tasks";
	}

	@GetMapping("/tasks/{id}/edit")
	public String taskEdit(@PathVariable("id") Long id, HttpSession session, @ModelAttribute("task") Task task,
			Model model) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}

		Task taskToEdit = taskService.findTask(id);
		List<User> users = userService.allUsers();

		if (userTemp.getId() != taskToEdit.getCreator().getId() || taskToEdit.getStatus().equals("completed")) {
			return "redirect:/tasks";
		}

		model.addAttribute("users", users);
		model.addAttribute("task", taskToEdit);
		return "edit.jsp";
	}

	@PutMapping("/tasks/update")
	public String taskUpdate(HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "edit.jsp";
		}

		taskService.saveTask(task);
		return "redirect:/tasks";
	}

	@DeleteMapping("/tasks/delete/{id}")
	public String taskDelete(@PathVariable("id") Long id, HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}

		Task task = taskService.findTask(id);

		if (task != null && userTemp.getId() == task.getCreator().getId()) {
			taskService.deleteTask(id);
		}

		return "redirect:/tasks";
	}

	@PutMapping("/tasks/completed/{id}")
	public String markTaskAsCompleted(@PathVariable("id") Long id, HttpSession session) {
		User userTemp = (User) session.getAttribute("userInSession");
		if (userTemp == null) {
			return "redirect:/";
		}
		Task task = taskService.findTask(id);

		if (task != null && userTemp.getId() == task.getAssignee().getId()) {
			taskService.markCompletedTask(id);
		}
		return "redirect:/tasks";
	}
}
