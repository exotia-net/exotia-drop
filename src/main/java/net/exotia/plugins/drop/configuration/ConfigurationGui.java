package net.exotia.plugins.drop.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import net.exotia.plugins.drop.configuration.section.SectionDrop;

@Getter
public class ConfigurationGui extends OkaeriConfig {
    @Comment("Drop GUI")
    private SectionDrop guiDrop = new SectionDrop();
}