package org.jetbrains.puppet.executor

import jetbrains.buildServer.serverSide.PropertiesProcessor
import jetbrains.buildServer.serverSide.RunType
import jetbrains.buildServer.serverSide.RunTypeRegistry
import jetbrains.buildServer.web.openapi.PluginDescriptor
import org.jetbrains.PUPPET_EXECUTOR_JSP_FILE
import org.jetbrains.PUPPET_EXECUTOR_TYPE

public class PuppetExecutor(val runTypeRegistry: RunTypeRegistry, val pluginDescriptor: PluginDescriptor) : RunType() {
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
        return pluginDescriptor.getPluginResourcesPath(PUPPET_EXECUTOR_JSP_FILE)
    }

    override fun getRunnerPropertiesProcessor(): PropertiesProcessor? {
        // validate params here
        return null
    }

    override fun getDisplayName(): String {
        return "Puppet Executor"
    }

    override fun getType(): String {
        return PUPPET_EXECUTOR_TYPE
    }

    override fun getDescription(): String {
        return "Execute Puppet Commands given a query"
    }

}