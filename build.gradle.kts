plugins {
	alias(libs.plugins.loom)
	`maven-publish`
}

repositories {
	mavenCentral()
}

val modName = property("mod_name") as String
val modId = property("mod_id") as String
group = property("maven_group") as String
version = property("version") as String

dependencies {
	minecraft(libs.minecraft)
	mappings(variantOf(libs.yarn) { classifier("v2") })
	modImplementation(libs.fabricLoader)

	modImplementation(libs.fabricApi)
}

tasks {
	processResources {
		val props = mapOf(
			"version" to version,
			"minecraft_version" to libs.versions.minecraft.get(),
			"loader_version" to libs.versions.fabricLoader.get(),
		)
		inputs.properties(props)
		filesMatching("fabric.mod.json") {
			expand(props)
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
		maven("https://ancientri.me/maven/releases") {
			name = "AncientRime"
			credentials(PasswordCredentials::class)
			authentication {
				create<BasicAuthentication>("basic")
			}
		}
	}
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = modId
			version = project.version as String
			groupId = project.group as String
			from(components["java"])
		}
	}
}


