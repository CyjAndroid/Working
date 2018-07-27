package com.work.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class WorkPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "This is work plugin"
    }
}