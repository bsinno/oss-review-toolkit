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

package org.ossreviewtoolkit.notifier.modules

import com.atlassian.jira.rest.client.api.JiraRestClient
import com.atlassian.jira.rest.client.api.domain.BasicIssue
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory

import java.net.URI

import org.ossreviewtoolkit.model.org.ossreviewtoolkit.model.config.JiraConfiguration

class JiraNotifier(config: JiraConfiguration,
                   val restClient: JiraRestClient = AsynchronousJiraRestClientFactory()
                           .createWithBasicHttpAuthentication(URI(config.host), config.username, config.password)) {

    fun createIssue(projectKey: String, summary: String, description: String, assignee: String, issueType: String, avoidDuplicates: Boolean = true): BasicIssue? {
        val issueInput = IssueInputBuilder()
            .setProjectKey(projectKey)
            .setSummary(summary)
            .setDescription(description)
            .setAssigneeName(assignee)
            .build()
        println("$issueType")
        if (avoidDuplicates) {
            // TODO: Check Jira, if issue exists. If yes, return the issue.
            restClient.searchClient.searchJql("project=$projectKey, fields: [summary:$summary]")
        }

        return restClient.issueClient.createIssue(issueInput).claim().also {
            println("Issue ${it.key} created.")
        }
    }
}
