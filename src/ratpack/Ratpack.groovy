import com.tele2.semaphore.WcDetails
import ratpack.http.Status
import ratpack.jackson.Jackson

import static com.tele2.semaphore.WcDetails.WcStatus.busy
import static com.tele2.semaphore.WcDetails.WcStatus.free
import static ratpack.groovy.Groovy.ratpack

ratpack {

    def wcStatuses = [new WcDetails(id: 'wc1-1', status: free), new WcDetails(id: 'wc2-1', status: free), new WcDetails(id: 'wc3-1', status: free)]
    handlers {

        get {
            render "Hello"
        }

        get("wcdetails/:id") {

            def wcDetails = wcStatuses.find { wcDetail ->
                wcDetail.id == pathTokens.id
            }
            render Jackson.json(wcDetails)
        }

        post ("occupy/:id"){

            def wcDetailsToOccupy = wcStatuses.find { wcDetails ->
                wcDetails.id == pathTokens.id
            }
            assert wcDetailsToOccupy != null
            wcDetailsToOccupy?.status = busy
            context.response.status(Status.OK).send("Ok")

        }
        post ("release/:id") {
            def wcDetailsToRelease = wcStatuses.find { wcDetails ->
                wcDetails.id == pathTokens.id
            }
            assert wcDetailsToRelease != null
            wcDetailsToRelease?.status = free
            context.response.status(Status.OK).send("Ok")
        }

        files { 'public' }
    }

}