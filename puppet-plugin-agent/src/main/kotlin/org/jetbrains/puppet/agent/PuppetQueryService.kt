package org.jetbrains.puppet.agent

import jetbrains.buildServer.agent.AgentBuildParameters
import jetbrains.buildServer.agent.AgentRuntimeProperties
import jetbrains.buildServer.agent.plugins.beans.AgentPluginInfo
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.JavaCommandLineBuilder
import jetbrains.buildServer.agent.runner.JavaRunnerUtil
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.runner.JavaRunnerConstants



public class PuppetQueryService: BuildServiceAdapter() {
    override fun makeProgramCommandLine(): ProgramCommandLine {
        val clBuilder = JavaCommandLineBuilder()
        clBuilder.setJavaHome(getRunnerParameters().get(JavaRunnerConstants.TARGET_JDK_HOME))
        clBuilder.setBaseDir(getCheckoutDirectory().getAbsolutePath())
        clBuilder.setWorkingDir(getWorkingDirectory().getAbsolutePath())
        clBuilder.setClassPath("")
        clBuilder.setEnvVariables(getRunnerContext().getBuildParameters().getEnvironmentVariables())
        clBuilder.setJvmArgs(JavaRunnerUtil.extractJvmArgs(getRunnerParameters()))
        //clBuilder.setMainClass("org.jetbrains.puppet.agent.PuppetQuery")
        clBuilder.setMainClass("org.jetbrains.puppet.agent.TC")


        return clBuilder.build()
    }

    override fun beforeProcessStarted() {
        getBuild().getBuildLogger().progressStarted("Starting")
    }
}