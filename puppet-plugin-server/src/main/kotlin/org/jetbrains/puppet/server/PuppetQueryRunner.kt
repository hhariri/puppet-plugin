package org.jetbrains.puppet.server

import jetbrains.buildServer.serverSide.PropertiesProcessor
import jetbrains.buildServer.serverSide.RunType
import jetbrains.buildServer.serverSide.RunTypeRegistry
import jetbrains.buildServer.web.openapi.PluginDescriptor
import org.jetbrains.puppet.common.PUPPET_QUERY_RUNNER_JSP_FILE
import org.jetbrains.puppet.common.PUPPET_QUERY_RUNNER_TYPE

public class PuppetQueryRunner(val runTypeRegistry: RunTypeRegistry, val pluginDescriptor: PluginDescriptor) : RunType() {
    init {
        runTypeRegistry.registerRunType(this)
    }
    override fun getViewRunnerParamsJspFilePath(): String? {
        return null
        // read-only description on the params of the runner
    }

    override fun getDefaultRunnerProperties(): MutableMap<String, String>? {
        // provide default values
        return null
    }

    override fun getEditRunnerParamsJspFilePath(): String? {
        return pluginDescriptor.getPluginResourcesPath(PUPPET_QUERY_RUNNER_JSP_FILE)
    }

    override fun getRunnerPropertiesProcessor(): PropertiesProcessor? {
        // validate params here
        return null
    }

    override fun getDisplayName(): String {
        return "Puppet Query"
    }

    override fun getType(): String {
        return PUPPET_QUERY_RUNNER_TYPE
    }

    override fun getDescription(): String {
        return "Run a Puppet Query returning a list of nodes"
    }

}