import com.tele2.semaphore.WcDetails
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

        get(":id") {


            def wcDetails = wcStatuses.find { wcDetail ->
                wcDetail.id == pathTokens.id
            }
            render Jackson.json(wcDetails)
        }

        post ("occupy/:id"){
            def wcDetailsToOccupy = wcStatuses.find { wcDetails ->
                wcDetails.id == pathTokens.id
            }
            wcDetailsToOccupy.status = busy

        }

        files { 'public' }
    }

}