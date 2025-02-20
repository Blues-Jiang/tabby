package com.tabbyml.intellijtabby.settings

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class ApplicationConfigurable : Configurable {
  private lateinit var settingsPanel: ApplicationSettingsPanel

  override fun getDisplayName(): String {
    return "Tabby"
  }

  override fun createComponent(): JComponent {
    settingsPanel = ApplicationSettingsPanel()
    return settingsPanel.mainPanel
  }

  override fun isModified(): Boolean {
    val settings = service<ApplicationSettingsState>()
    return settingsPanel.completionTriggerMode != settings.completionTriggerMode
        || settingsPanel.serverEndpoint != settings.serverEndpoint
        || settingsPanel.nodeBinary != settings.nodeBinary
        || settingsPanel.isAnonymousUsageTrackingDisabled != settings.isAnonymousUsageTrackingDisabled
  }

  override fun apply() {
    val settings = service<ApplicationSettingsState>()
    settings.completionTriggerMode = settingsPanel.completionTriggerMode
    settings.serverEndpoint = settingsPanel.serverEndpoint
    settings.nodeBinary = settingsPanel.nodeBinary
    settings.isAnonymousUsageTrackingDisabled = settingsPanel.isAnonymousUsageTrackingDisabled
  }

  override fun reset() {
    val settings = service<ApplicationSettingsState>()
    settingsPanel.completionTriggerMode = settings.completionTriggerMode
    settingsPanel.serverEndpoint = settings.serverEndpoint
    settingsPanel.nodeBinary = settings.nodeBinary
    settingsPanel.isAnonymousUsageTrackingDisabled = settings.isAnonymousUsageTrackingDisabled
  }
}