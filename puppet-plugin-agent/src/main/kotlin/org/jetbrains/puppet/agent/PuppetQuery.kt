package org.jetbrains.puppet.agent

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.HttpAsyncClient
import org.apache.http.nio.client.methods.HttpAsyncMethods
import rx.apache.http.ObservableHttp
import rx.apache.http.ObservableHttpResponse
import java.net.URL
import java.net.URLEncoder
import kotlin.platform.platformStatic

public object PuppetQuery {
    platformStatic public fun main(args: Array<String>) {

        val a = makeRequest("http://172.16.0.2:8080/v3/nodes?query=","[\"=\", \"name\",\"ubuntu3.vagrant.local\"]")

        println(a)
    }

    private fun makeRequest(url: String, query: String): String {
        val client = HttpAsyncClients.createDefault()
        val queryEncoded = URLEncoder.encode(query, "UTF-8")
        client.start()
        val s = "$url$queryEncoded"
        return ObservableHttp.createRequest(HttpAsyncMethods.createGet(s), client)
                .toObservable()
                .flatMap({ response -> response.getContent().map {
                        byte ->
                        String(byte)
                    }
                })
                .toBlocking()
                .single()
    }

}


