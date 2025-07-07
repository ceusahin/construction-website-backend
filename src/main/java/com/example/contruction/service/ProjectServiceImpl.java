package com.example.contruction.service;

import com.example.contruction.entity.Project;
import com.example.contruction.exception.NotFoundException;
import com.example.contruction.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proje bulunamadı: " + id));
    }

    @Override
    public List<Project> findByClientId(Long clientId) {
        return projectRepository.findByClientId(clientId);
    }

    @Override
    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proje bulunamadı: " + id));

        existingProject.setClientId(updatedProject.getClientId());
        existingProject.setConstructionType(updatedProject.getConstructionType());
        existingProject.setLocation(updatedProject.getLocation());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setEndDate(updatedProject.getEndDate());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setBudget(updatedProject.getBudget());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setUpdatedAt(updatedProject.getUpdatedAt());

        existingProject.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Silinecek proje bulunamadı: " + id);
        }
        projectRepository.deleteById(id);
    }
}
