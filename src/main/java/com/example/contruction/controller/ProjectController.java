package com.example.contruction.controller;

import com.example.contruction.entity.Project;
import com.example.contruction.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /*
        get     -->         List<Project> getAllProjects();
        post    -->         Project createProject(Project project);
        get     -->         Project getProjectById(Long id);
        get     -->         List<Project> findByClientId(Long id);
        put     -->         Project updateProject(Long id, Project updatedProject);
        delete  -->         void deleteProject(Long id);
    */

    @GetMapping("/")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/client/{id}")
    public List<Project> findByClientId(@PathVariable Long id) {
        return projectService.findByClientId(id);
    }

    @PutMapping("/update/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }



}
