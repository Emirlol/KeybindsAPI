plugins {
	id("fabric-loom") version "1.8-SNAPSHOT"
}

repositories {
	mavenCentral()
}

val minecraftVersion = property("minecraft_version") as String
val yarnMappings = property("yarn_mappings") as String
val loaderVersion = property("loader_version") as String

val modName = property("mod_name") as String
val modId = property("mod_id") as String
version = property("mod_version") as String
group = property("maven_group") as String

val fabricApiVersion = property("fabric_version") as String

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:$minecraftVersion")
	mappings("net.fabricmc:yarn:$yarnMappings:v2")
	modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
}

tasks {
	processResources {
		filesMatching("fabric.mod.json") {
			expand(
				mapOf(
					"version" to version,
					"minecraft_version" to minecraftVersion,
					"loader_version" to loaderVersion,
					"name" to modName,
					"mod_id" to modId
				)
			)
		}
	}
}
