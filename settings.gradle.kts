pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/") {
			name = "Fabric"
		}
		gradlePluginPortal()
	}
}
rootProject.name = "KeyMappingEvents"

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			// region Versions
			// https://fabricmc.net/develop/
			version("minecraft", "26.2")
			version("fabricLoader", "0.20.1")
			version("fabricApi", "0.161.0+26.2")

			version("loom", "1.19-SNAPSHOT") // https://github.com/FabricMC/fabric-loom
			// endregion

			//region Libraries
			library("minecraft", "com.mojang", "minecraft").versionRef("minecraft")
			library("fabricLoader", "net.fabricmc", "fabric-loader").versionRef("fabricLoader")
			library("fabricApi", "net.fabricmc.fabric-api", "fabric-api").versionRef("fabricApi")
			//endregion

			//region Plugins
			plugin("loom", "net.fabricmc.fabric-loom").versionRef("loom")
			//endregion
		}
	}
}