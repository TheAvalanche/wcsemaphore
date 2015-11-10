import com.tele2.semaphore.WcDetails
import ratpack.http.Status
import ratpack.jackson.Jackson

import static com.tele2.semaphore.WcDetails.WcStatus.closed
import static com.tele2.semaphore.WcDetails.WcStatus.opened
import static ratpack.groovy.Groovy.ratpack

ratpack {

    def wcStatuses = [
            new WcDetails(id: 'wc1-1', status: opened),
            new WcDetails(id: 'wc1-2', status: opened),
            new WcDetails(id: 'wc1-3', status: opened),
            new WcDetails(id: 'wc1-4', status: opened),
            new WcDetails(id: 'wc1-5', status: opened),
            new WcDetails(id: 'wc1-6', status: opened),
            new WcDetails(id: 'wc1-7', status: opened),
            new WcDetails(id: 'wc1-8', status: opened),
            new WcDetails(id: 'wc1-9', status: opened),
            new WcDetails(id: 'wc1-10', status: opened),
            new WcDetails(id: 'wc1-11', status: opened),
            new WcDetails(id: 'wc1-12', status: opened),
            new WcDetails(id: 'wc1-13', status: opened),
            new WcDetails(id: 'wc2-1', status: opened),
            new WcDetails(id: 'wc2-2', status: opened),
            new WcDetails(id: 'wc2-3', status: opened),
            new WcDetails(id: 'wc2-4', status: opened),
            new WcDetails(id: 'wc2-5', status: opened),
            new WcDetails(id: 'wc2-6', status: opened),
            new WcDetails(id: 'wc2-7', status: opened),
            new WcDetails(id: 'wc2-8', status: opened),
            new WcDetails(id: 'wc2-9', status: opened),
            new WcDetails(id: 'wc3-1', status: opened),
            new WcDetails(id: 'wc3-2', status: opened),
            new WcDetails(id: 'wc3-3', status: opened),
            new WcDetails(id: 'wc3-4', status: opened),
            new WcDetails(id: 'wc3-5', status: opened),
            new WcDetails(id: 'wc3-6', status: opened),
            new WcDetails(id: 'wc3-7', status: opened),
            new WcDetails(id: 'wc3-8', status: opened),
            new WcDetails(id: 'wc3-9', status: opened),
            new WcDetails(id: 'wc3-10', status: opened),
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

        get("wcdetails/") {
            render Jackson.json(wcStatuses)
        }

        post ("occupy/:id"){

            def wcDetailsToOccupy = wcStatuses.find { wcDetails ->
                wcDetails.id == pathTokens.id
            }
            assert wcDetailsToOccupy != null
            wcDetailsToOccupy?.status = closed
            context.response.status(Status.OK).send("Ok")

        }
        post ("release/:id") {
            def wcDetailsToRelease = wcStatuses.find { wcDetails ->
                wcDetails.id == pathTokens.id
            }
            assert wcDetailsToRelease != null
            wcDetailsToRelease?.status = opened
            context.response.status(Status.OK).send("Ok")
        }

        get ("freeOnFloor/:floor") {
            def floorNumber = pathTokens.floor
            assert floorNumber != null
            def freeWcs =  wcStatuses.findAll { wcDetail ->
                wcDetail.id.startsWith("wc$floorNumber") && wcDetail.status == opened
        }
            render ("Total amount of free wc on $floorNumber floor ${freeWcs.size()}")

        }
        files { 'public' }
    }

}