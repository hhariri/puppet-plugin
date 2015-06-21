package org.jetbrains.puppet.agent

import jetbrains.buildServer.agent.AgentBuildParameters
import jetbrains.buildServer.agent.AgentRuntimeProperties
import jetbrains.buildServer.agent.plugins.beans.AgentPluginInfo
import jetbrains.buildServer.agent.plugins.beans.PluginDescriptor
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.JavaCommandLineBuilder
import jetbrains.buildServer.agent.runner.JavaRunnerUtil
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.runner.JavaRunnerConstants
import java.io.File


public class PuppetQueryService(pd: PluginDescriptor) : BuildServiceAdapter() {
    val pluginDescriptor = pd;

    override fun makeProgramCommandLine(): ProgramCommandLine {
        val clBuilder = JavaCommandLineBuilder()
        clBuilder.setJavaHome(getRunnerParameters().get(JavaRunnerConstants.TARGET_JDK_HOME))
        clBuilder.setBaseDir(getCheckoutDirectory().getAbsolutePath())
        clBuilder.setWorkingDir(getWorkingDirectory().getAbsolutePath())
        clBuilder.setClassPath(getClasspath());
        clBuilder.setEnvVariables(getRunnerContext().getBuildParameters().getEnvironmentVariables())
        clBuilder.setJvmArgs(JavaRunnerUtil.extractJvmArgs(getRunnerParameters()))
        clBuilder.setMainClass("org.jetbrains.puppet.agent.PuppetQuery")

        return clBuilder.build()
    }

    private fun getClasspath(): String {
        val classpath = StringBuilder();
        val pluginRoot = this.pluginDescriptor.getPluginRoot();
        val pluginLibs = File(pluginRoot, "lib");
        val separator = File.pathSeparator;
        val libs = pluginLibs.listFiles();
        if (libs != null) {
            for (lib in libs) {
                classpath.append(lib.getAbsolutePath());
                classpath.append(separator);
            }
        }
        return classpath.toString();
    }

    override fun beforeProcessStarted() {
        getBuild().getBuildLogger().progressStarted("Starting")
    }
}