package org.jetbrains.puppet.agent


import jetbrains.buildServer.agent.AgentBuildRunnerInfo
import jetbrains.buildServer.agent.BuildAgentConfiguration
import jetbrains.buildServer.agent.plugins.beans.AgentPluginInfo
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.CommandLineBuildService
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import org.jetbrains.puppet.common.PUPPET_QUERY_RUNNER_TYPE


public class PuppetQueryFactory : CommandLineBuildServiceFactory {
    override fun getBuildRunnerInfo(): AgentBuildRunnerInfo {
        return object: AgentBuildRunnerInfo {
            override fun canRun(buildConfiguration: BuildAgentConfiguration): Boolean {
                return true
            }

            override fun getType(): String {
                return PUPPET_QUERY_RUNNER_TYPE
            }

        }
    }

    override fun createService(): CommandLineBuildService {
        return PuppetQueryService()
    }
}


