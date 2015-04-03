package org.jetbrains.puppet.executor

import jetbrains.buildServer.agent.AgentBuildRunnerInfo
import jetbrains.buildServer.agent.BuildAgentConfiguration
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.CommandLineBuildService
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import org.jetbrains.PUPPET_EXECUTOR_TYPE

public class PuppetExecutorFactory: CommandLineBuildServiceFactory {
    override fun getBuildRunnerInfo(): AgentBuildRunnerInfo {
        return object: AgentBuildRunnerInfo {
            override fun canRun(p0: BuildAgentConfiguration): Boolean {
                return true
            }

            override fun getType(): String {
                return PUPPET_EXECUTOR_TYPE
            }

        }
    }

    override fun createService(): CommandLineBuildService {
        return object: BuildServiceAdapter() {
            override fun makeProgramCommandLine(): ProgramCommandLine {
                return object: ProgramCommandLine {
                    override fun getExecutablePath(): String {
                        return "/bin/bash"
                    }

                    override fun getWorkingDirectory(): String {
                        return getRunnerContext().getWorkingDirectory().getAbsolutePath()
                    }

                    override fun getEnvironment(): MutableMap<String, String> {
                        return getRunnerContext().getBuildParameters().getEnvironmentVariables()
                    }

                    override fun getArguments(): MutableList<String> {
                        val puppetRootPath = getRunnerContext().getRunnerParameters().get("puppetRootPath")
                        return arrayListOf("-c", "echo $puppetRootPath")
                    }

                }
            }

        }
    }
}