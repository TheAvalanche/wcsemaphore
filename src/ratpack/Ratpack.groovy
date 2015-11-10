import com.tele2.semaphore.WcDetails
import ratpack.http.Status
import ratpack.jackson.Jackson

import static com.tele2.semaphore.WcDetails.WcStatus.busy
import static com.tele2.semaphore.WcDetails.WcStatus.free
import static ratpack.groovy.Groovy.ratpack

ratpack {

    def wcStatuses = [
            new WcDetails(id: 'wc1-1', status: free),
            new WcDetails(id: 'wc1-2', status: free),
            new WcDetails(id: 'wc1-3', status: free),
            new WcDetails(id: 'wc1-4', status: free),
            new WcDetails(id: 'wc1-5', status: free),
            new WcDetails(id: 'wc1-6', status: free),
            new WcDetails(id: 'wc1-7', status: free),
            new WcDetails(id: 'wc1-8', status: free),
            new WcDetails(id: 'wc1-9', status: free),
            new WcDetails(id: 'wc1-10', status: free),
            new WcDetails(id: 'wc1-11', status: free),
            new WcDetails(id: 'wc1-12', status: free),
            new WcDetails(id: 'wc1-13', status: free),
            new WcDetails(id: 'wc2-1', status: free),
            new WcDetails(id: 'wc2-2', status: free),
            new WcDetails(id: 'wc2-3', status: free),
            new WcDetails(id: 'wc2-4', status: free),
            new WcDetails(id: 'wc2-5', status: free),
            new WcDetails(id: 'wc2-6', status: free),
            new WcDetails(id: 'wc2-7', status: free),
            new WcDetails(id: 'wc2-8', status: free),
            new WcDetails(id: 'wc2-9', status: free),
            new WcDetails(id: 'wc3-1', status: free),
            new WcDetails(id: 'wc3-2', status: free),
            new WcDetails(id: 'wc3-3', status: free),
            new WcDetails(id: 'wc3-4', status: free),
            new WcDetails(id: 'wc3-5', status: free),
            new WcDetails(id: 'wc3-6', status: free),
            new WcDetails(id: 'wc3-7', status: free),
            new WcDetails(id: 'wc3-8', status: free),
            new WcDetails(id: 'wc3-9', status: free),
            new WcDetails(id: 'wc3-10', status: free),
    ]
    handlers {

        get {
            redirect('public/index.html')
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