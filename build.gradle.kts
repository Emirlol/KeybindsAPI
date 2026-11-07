plugins {
	alias(libs.plugins.loom)
	`maven-publish`
}

repositories {
	mavenCentral()
}

object ModProperties {
	const val MOD_ID = "keymapping-events"
	const val MOD_NAME = "Keymapping Events"
	const val MAVEN_GROUP = "org.rimeorreason"
	const val VERSION = "1.1.0"
}

group = ModProperties.MAVEN_GROUP
version = ModProperties.VERSION

dependencies {
	minecraft(libs.minecraft)
	implementation(libs.fabricLoader)
	implementation(libs.fabricApi)
}

tasks {
	processResources {
		val properties = mapOf(
			"mod_id" to ModProperties.MOD_ID,
			"mod_name" to ModProperties.MOD_NAME,
			"version" to project.version,
			"loader_version" to libs.versions.fabricLoader.get(),
			"minecraft_version" to libs.versions.minecraft.get()
		)
		inputs.properties(properties)
		filesMatching("fabric.mod.json") {
			expand(properties)
		}
	}
	jar {
		from("LICENSE") {
			rename { "${it}_${base.archivesName.get()}" }
		}
	}
}

java {
	withSourcesJar()
}

publishing {
	repositories {
		maven("https://maven.rimeorreason.org/releases") {
			name = "RimeOrReasonReleases"
			credentials(PasswordCredentials::class)
			authentication {
				create<BasicAuthentication>("basic")
			}
		}
	}
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = ModProperties.MOD_ID
			version = project.version as String
			groupId = project.group as String
			from(components["java"])
		}
	}
}


