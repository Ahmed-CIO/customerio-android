package io.customer.sdk

import io.customer.sdk.data.model.Region

class CustomerIOConfig(
    val siteId: String,
    val apiKey: String,
    val region: Region,
    val timeout: Long
)
