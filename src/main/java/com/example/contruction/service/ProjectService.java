package com.example.contruction.service;

import com.example.contruction.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project createProject(Project project);
    Project getProjectById(Long id);
    List<Project> findByClientId(Long id);
    Project updateProject(Long id, Project updatedProject);
    void deleteProject(Long id);
}
