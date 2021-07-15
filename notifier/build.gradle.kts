/*
 * Copyright (C) 2021 Bosch.IO GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

val apacheCommonsEmailVersion: String by project

plugins {
    // Apply core plugins.
    `java-library`
}

dependencies {
    api(project(":model"))

    implementation(project(":utils"))

    implementation("org.apache.commons:commons-email:$apacheCommonsEmailVersion")
    implementation("com.atlassian.jira:jira-rest-java-client-api:5.2.2")
    implementation("com.atlassian.jira:jira-rest-java-client-core:5.2.2")
    implementation("org.glassfish.jersey.core:jersey-common:2.4.1")

    runtimeOnly("com.atlassian.fugue:fugue:2.6.2")

    //implementation("com.google.guava:guava:14.0-rc1")
}

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            maven("https://packages.atlassian.com/maven-external")
        }

        filter {
            includeGroupByRegex("com\\.atlassian\\..*")
        }
    }
}
