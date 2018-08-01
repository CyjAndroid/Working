package com.work.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.work.plugin.AssembleTask


class WorkPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        String taskNames = project.gradle.startParameter.taskNames.toString()
        System.out.println("taskNames is " + taskNames)
        String module = project.path.replace(":", "")
        System.out.println("current module is " + module)

        AssembleTask assembleTask = getTaskInfo(project.gradle.startParameter.taskNames)
        if (assembleTask.isAssemble) {
            assembleTask.println()
            compileComponents(assembleTask,project)
        }

    }

    private void compileComponents(AssembleTask assembleTask, Project project) {
        String components
        if (assembleTask.isDebug) {
            components = project.properties.get("debugComponent")
        } else {
            components = project.properties.get("compileComponent")
        }

        if (components == null || components.length() == 0) {
            System.out.println("there is no add dependencies")
            return
        }

        if(components.startsWith(":")){
            components=components.substring(1)
        }

        if(Utils.isMavenArtifact(components)){
            project.dependencies.add("compile",components)
        }else {
            project.dependencies.add("compile",project.project(':'+components))
        }

    }

    private AssembleTask getTaskInfo(List<String> taskNames) {
        AssembleTask assembleTask = new AssembleTask()
        for (String task : taskNames) {
            if (task.toUpperCase().contains("ASSEMBLE")
                    || task.contains("aR")
                    || task.contains("asR")
                    || task.contains("asD")
                    || task.toUpperCase().contains("TINKER")
                    || task.toUpperCase().contains("INSTALL")
                    || task.toUpperCase().contains("RESGUARD")) {
                if (task.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                }
                assembleTask.isAssemble = true
                System.out.println("debug assembleTask info:" + task)
                break
            }
        }
        return assembleTask
    }


}