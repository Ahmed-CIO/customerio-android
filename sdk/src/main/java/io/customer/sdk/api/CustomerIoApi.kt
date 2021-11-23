package io.customer.sdk.api

import io.customer.base.comunication.Action
import io.customer.sdk.data.request.MetricEvent

/**
 * Apis exposed to clients
 */
internal interface CustomerIoApi {
    fun identify(identifier: String, attributes: Map<String, Any>): Action<Unit>
    fun track(name: String, attributes: Map<String, Any>): Action<Unit>
    fun clearIdentify()
    fun registerDeviceToken(deviceToken: String): Action<Unit>
    fun deleteDeviceToken(): Action<Unit>
    fun trackMetric(deliveryID: String, event: MetricEvent, deviceToken: String): Action<Unit>
}