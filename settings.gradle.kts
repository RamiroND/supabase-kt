pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include("GoTrue")
include("Postgrest")
include("Storage")
include("Realtime")
include("Functions")
include("bom")

include("test")
include("test-w")

include(":serializers:Moshi")
project(":serializers:Moshi").name = "serializer-moshi"
include(":serializers:Jackson")
project(":serializers:Jackson").name = "serializer-jackson"

include(":plugins:ApolloGraphQL")
include(":plugins:Compose-Auth")
project(":GoTrue").name = "gotrue-kt"
project(":Postgrest").name = "postgrest-kt"
project(":Storage").name = "storage-kt"
project(":Realtime").name = "realtime-kt"
project(":Functions").name = "functions-kt"
project(":plugins:ApolloGraphQL").name = "apollo-graphql"
project(":plugins:Compose-Auth").name = "compose-auth"
rootProject.name = "supabase-kt"

